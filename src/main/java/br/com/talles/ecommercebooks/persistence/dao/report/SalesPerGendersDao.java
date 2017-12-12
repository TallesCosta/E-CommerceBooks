package br.com.talles.ecommercebooks.persistence.dao.report;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Gender;
import br.com.talles.ecommercebooks.domain.report.ResultQuery;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.CountOfGenders;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.IntervalDates;
import br.com.talles.ecommercebooks.domain.report.salesPerGenders.SalesPerGenders;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SalesPerGendersDao extends AbstractDao {

    @Override
    public List<Entity> select(boolean enabled, Entity entity) {
        SalesPerGenders salesPerGendersReport = (SalesPerGenders) entity;

        String sql = "select count(c.GENDER) as countSale, c.GENDER as gender, "
                        + "EXTRACT(MONTH FROM s.DATE) as month, EXTRACT(YEAR FROM s.DATE) as year "
                + "from sales s "
                + "inner join customers c on s.ID_CUSTOMER = c.id "
                + "where s.DATE > ? AND s.DATE < ? "
                + "group by c.GENDER, EXTRACT(MONTH FROM s.DATE), EXTRACT(YEAR FROM s.DATE) "
                + "order by year, month, gender";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            try {
                statement.setDate(1, new java.sql.Date(
                        ((IntervalDates) salesPerGendersReport.getSpecificationQuery()).getInicialDate().getTime()));
                statement.setDate(2, new java.sql.Date(
                        ((IntervalDates) salesPerGendersReport.getSpecificationQuery()).getFinalDate().getTime()));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                CountOfGenders countG = new CountOfGenders();
                countG.setSalesAmmount(result.getInt("countSale"));
                countG.setGender(new Gender(result.getString("gender")));
                countG.setMonth(result.getInt("month"));
                countG.setYear(result.getInt("year"));

                salesPerGendersReport.getResultsQuery().add(countG);
            }

            result.close();
            statement.close();

            List<Entity> report = new ArrayList<>();
            report.add(salesPerGendersReport);

            return report;
        } catch (SQLException e) {
            throw new RuntimeException (e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean save(Entity entity) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public Entity find(Entity entity) {
        return null;
    }

    @Override
    public boolean update(Entity entity, String operation) {
        return false;
    }

    @Override
    public Entity findLast() {
        return null;
    }

}

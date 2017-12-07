package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Exchange;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeDao extends AbstractDao {

    @Override
    public List<Entity> select(boolean enabled, Entity entity) {
        return null;
    }

    @Override
    public boolean save(Entity entity) {
        Exchange exchange = (Exchange) entity;

        String sql = "";
        if (exchange.isAccepted() != null)
            sql = "INSERT INTO Exchanges (enabled, justification, id_sale, accepted) "
                    + "VALUES(?, ?, ?, ?)";
        else
            sql = "INSERT INTO Exchanges (enabled, justification, id_sale) "
                    + "VALUES(?, ?, ?)";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, exchange.isEnabled());
            statement.setString(2, exchange.getJustification());
            statement.setLong(3, exchange.getOrder().getId());

            if (exchange.isAccepted() != null)
                statement.setBoolean(4, exchange.isAccepted());

            statement.execute();
            statement.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public Entity find(Entity entity) {
        Exchange exchange = (Exchange) entity;

        String sql = "SELECT e.* " +
                "FROM Exchanges e " +
                "WHERE e.id_sale = ?;";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, exchange.getOrder().getId());

            ResultSet result = statement.executeQuery();

            if(result.first()){
                // Exchange datas
                exchange.setId(result.getLong("exchanges.id"));
                exchange.setEnabled(result.getBoolean("exchanges.enabled"));
                exchange.setAccepted(result.getBoolean("exchanges.accepted"));
                exchange.setJustification(result.getString("exchanges.justification"));
            }

            result.close();
            statement.close();

            return exchange;

        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Entity entity, String operation) {
        Exchange exchange = (Exchange) entity;

        String sql = "UPDATE Exchanges "
                + "SET enabled = ?, accepted = ? "
                + "WHERE id_sale = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, exchange.isEnabled());
            statement.setBoolean(2, exchange.isAccepted());

            statement.setLong(3, exchange.getId());

            statement.execute();
            statement.close();

            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Entity findLast() {
        return null;
    }

}

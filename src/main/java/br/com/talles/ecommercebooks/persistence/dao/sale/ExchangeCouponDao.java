package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeCouponDao extends AbstractDao {

    @Override
    public List<Entity> select(boolean enabled, Entity entity) {
        ExchangeCoupon exchangeCoupon = (ExchangeCoupon) entity;
        List<Entity> exchangeCoupons = new ArrayList();

        String sql = "SELECT ec.* "
                + "FROM ExchangeCoupons ec "
                + "INNER JOIN Customers c on ec.id_customer = c.id "
                + "WHERE ec.enabled = ? AND c.id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, enabled);
            statement.setLong(2, exchangeCoupon.getCustomer().getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ExchangeCoupon ec = new ExchangeCoupon();

                ec.setId(result.getLong("exchangeCoupons.id"));
                ec.setEnabled(result.getBoolean("exchangeCoupons.enabled"));
                ec.setCode(result.getString("exchangeCoupons.code"));
                ec.setValue(result.getDouble("exchangeCoupons.value"));

                exchangeCoupons.add(ec);
            }

            result.close();
            statement.close();

            return  exchangeCoupons;
        } catch (SQLException e) {
            throw new RuntimeException (e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean save(Entity entity) {
        ExchangeCoupon exchangeCoupon = (ExchangeCoupon) entity;

        String sql = "INSERT INTO ExchangeCoupons (enabled, code, value, id_customer) "
                + "VALUES(?, ?, ?, ?)";
        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, exchangeCoupon.isEnabled());
            statement.setString(2, exchangeCoupon.getCode());
            statement.setDouble(3, exchangeCoupon.getValue());

            statement.setLong(4, exchangeCoupon.getCustomer().getId());

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
        return null;
    }

    @Override
    public boolean update(Entity entity, String operation) {
        ExchangeCoupon exchangeCoupon = (ExchangeCoupon) entity;

        String sql = "UPDATE ExchangeCoupons "
                + "SET enabled = ?, id_sale = ? "
                + "WHERE id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, exchangeCoupon.isEnabled());
            statement.setLong(2, exchangeCoupon.getCustomer().getSale(0).getId());

            statement.setLong(3, exchangeCoupon.getId());

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

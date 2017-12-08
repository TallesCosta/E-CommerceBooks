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
        List<Entity> exchangeCoupons = new ArrayList();

        String sql = "SELECT ec.* "
                + "FROM ExchangeCoupons ec "
                + "WHERE ec.enabled = ? ";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, enabled);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ExchangeCoupon exchangeCoupon = new ExchangeCoupon();

                exchangeCoupon.setId(result.getLong("exchangeCoupons.id"));
                exchangeCoupon.setEnabled(result.getBoolean("exchangeCoupons.enabled"));
                exchangeCoupon.setCode(result.getString("exchangeCoupons.code"));
                exchangeCoupon.setValue(result.getDouble("exchangeCoupons.value"));

                exchangeCoupons.add(exchangeCoupon);
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
                + "SET enabled = ?, code = ?, value = ? "
                + "WHERE id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, exchangeCoupon.isEnabled());
            statement.setString(2, exchangeCoupon.getCode());
            statement.setDouble(3, exchangeCoupon.getValue());

            statement.setLong(4, exchangeCoupon.getId());

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

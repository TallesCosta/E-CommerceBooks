package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.PromotionalCoupon;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionalCouponDao extends AbstractDao {

    @Override
    public List<Entity> select(boolean enabled, Entity entity) {
        List<Entity> promotionalCoupons = new ArrayList();

        String sql = "SELECT pc.* "
                + "FROM PromotionalCoupons pc "
                + "WHERE pc.enabled = ? ";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, enabled);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                PromotionalCoupon promotionalCoupon = new PromotionalCoupon();

                promotionalCoupon.setId(result.getLong("promotionalCoupons.id"));
                promotionalCoupon.setEnabled(result.getBoolean("promotionalCoupons.enabled"));
                promotionalCoupon.setCode(result.getString("promotionalCoupons.code"));
                promotionalCoupon.setValue(result.getDouble("promotionalCoupons.value"));

                promotionalCoupons.add(promotionalCoupon);
            }

            result.close();
            statement.close();

            return  promotionalCoupons;
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
        return entity;
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

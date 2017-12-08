package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Delivery;
import br.com.talles.ecommercebooks.domain.sale.ShippingCost;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseShippingCostDao extends AbstractDao {

    @Override
    public List<Entity> select(boolean enabled, Entity entity) {
        return null;
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
        Delivery delivery = (Delivery) entity;

        String sql = "SELECT bsc.*\n" +
                "FROM DeliveryAddresses da\n" +
                "INNER JOIN States s on da.id_state = s.id\n" +
                "INNER JOIN BaseShippingCosts bsc on s.id = bsc.id_state\n" +
                "WHERE da.id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, delivery.getDeliveryAddress().getId());

            ResultSet result = statement.executeQuery();

            if (result.first()) {
                delivery.setId(result.getLong("baseShippingCosts.id"));
                delivery.setEnabled(result.getBoolean("baseShippingCosts.enabled"));
                delivery.setShippingCost(new ShippingCost(
                        result.getDouble("baseShippingCosts.baseValue"),
                        result.getDouble("baseShippingCosts.baseAdditionValue")));
            }

            result.close();
            statement.close();

            return delivery;
        } catch (SQLException e) {
            throw new RuntimeException (e);
        } finally {
            closeConnection();
        }
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

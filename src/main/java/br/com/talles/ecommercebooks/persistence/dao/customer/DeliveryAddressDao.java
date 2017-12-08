package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.*;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryAddressDao extends AbstractDao {

    @Override
    public List<Entity> select(boolean enabled, Entity entity) {
        DeliveryAddress deliveryAddress = (DeliveryAddress) entity;
        List<Entity> deliveryAddresses = new ArrayList();

        String sql = "SELECT da.*, s.*, c.* "
                    + "FROM DeliveryAddresses da "
                    + "INNER JOIN Customers cus on da.id_customer = cus.id "
                    + "INNER JOIN States s on da.id_state = s.id "
                    + "INNER JOIN Countries c on s.id_country = c.id "
                    + "WHERE da.enabled = ? AND cus.id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, enabled);
            statement.setLong(2, deliveryAddress.getCustomer().getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Address da = new DeliveryAddress();
                da.setId(result.getLong("deliveryAddresses.id"));
                da.setEnabled(result.getBoolean("deliveryAddresses.enabled"));
                da.setAlias(result.getString("deliveryAddresses.alias"));
                da.setCity(result.getString("deliveryAddresses.city"));
                da.setDistrict(result.getString("deliveryAddresses.district"));
                da.setHomeType(result.getString("deliveryAddresses.homeType"));
                da.setNumber(result.getString("deliveryAddresses.number"));
                da.setObservation(result.getString("deliveryAddresses.observation"));
                da.setPostalCode(result.getString("deliveryAddresses.postalCode"));
                da.setPublicPlace(result.getString("deliveryAddresses.publicPlace"));
                da.setPublicPlaceType(result.getString("deliveryAddresses.publicPlaceType"));
                da.setState(new State(result.getString("states.name"),
                        new Country(result.getString("countries.name"))));

                deliveryAddresses.add(da);
            }

            result.close();
            statement.close();

            return deliveryAddresses;
        } catch (SQLException e) {
            throw new RuntimeException (e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean save(Entity entity) {
        DeliveryAddress deliveryAddress = (DeliveryAddress) entity;

        String sql = "INSERT INTO DeliveryAddresses (enabled, alias, observation, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Find the last customer register to get its id
        IDao custumerDao = new CustomerDao();
        Customer lastCustomer = (Customer) custumerDao.findLast();

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, deliveryAddress.isEnabled());
            statement.setString(2, deliveryAddress.getAlias());
            statement.setString(3, deliveryAddress.getObservation());
            statement.setString(4, deliveryAddress.getPublicPlaceType());
            statement.setString(5, deliveryAddress.getPublicPlace());
            statement.setString(6, deliveryAddress.getNumber());
            statement.setString(7, deliveryAddress.getDistrict());
            statement.setString(8, deliveryAddress.getPostalCode());
            statement.setString(9, deliveryAddress.getHomeType());
            statement.setString(10, deliveryAddress.getCity());

            statement.setLong(11, deliveryAddress.getState().getId());
            statement.setLong(12, lastCustomer.getId());

            statement.execute();
            statement.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CreditCardDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }

    @Override
    public Entity findLast() {
        return null;
    }

}

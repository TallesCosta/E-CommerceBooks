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

public class AddressDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> addresses = new ArrayList();

		String sql = "";
		if (entity instanceof ChargeAddress)
			sql = "SELECT ca.*, s.*, c.* "
				+ "FROM ChargeAddresses ca "
				+ "INNER JOIN States s on ca.id_state = s.id "
				+ "INNER JOIN Countries c on s.id_country = c.id "
				+ "WHERE ca.enabled = ? ";
		else
			sql = "SELECT da.*, s.*, c.* "
					+ "FROM DeliveryAddresses da "
					+ "INNER JOIN States s on da.id_state = s.id "
					+ "INNER JOIN Countries c on s.id_country = c.id "
					+ "WHERE da.enabled = ? ";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Address a;

				if (entity instanceof ChargeAddress) {
					a = new ChargeAddress();

					a.setId(result.getLong("chargeAddresses.id"));
					a.setEnabled(result.getBoolean("chargeAddresses.enabled"));
					a.setAlias(result.getString("chargeAddresses.alias"));
					a.setCity(result.getString("chargeAddresses.city"));
					a.setDistrict(result.getString("chargeAddresses.district"));
					a.setHomeType(result.getString("chargeAddresses.homeType"));
					a.setNumber(result.getString("chargeAddresses.number"));
					a.setObservation(result.getString("chargeAddresses.observation"));
					a.setPostalCode(result.getString("chargeAddresses.postalCode"));
					a.setPublicPlace(result.getString("chargeAddresses.publicPlace"));
					a.setPublicPlaceType(result.getString("chargeAddresses.publicPlaceType"));
					a.setState(new State(result.getString("states.name"),
							new Country(result.getString("contries.name"))));
				}
				else {
					a = new DeliveryAddress();
					a.setId(result.getLong("deliveryAddress.id"));
					a.setEnabled(result.getBoolean("deliveryAddress.enabled"));
					a.setAlias(result.getString("deliveryAddress.alias"));
					a.setCity(result.getString("deliveryAddress.city"));
					a.setDistrict(result.getString("deliveryAddress.district"));
					a.setHomeType(result.getString("deliveryAddress.homeType"));
					a.setNumber(result.getString("deliveryAddress.number"));
					a.setObservation(result.getString("deliveryAddress.observation"));
					a.setPostalCode(result.getString("deliveryAddress.postalCode"));
					a.setPublicPlace(result.getString("deliveryAddress.publicPlace"));
					a.setPublicPlaceType(result.getString("deliveryAddress.publicPlaceType"));
					a.setState(new State(result.getString("states.name"),
							new Country(result.getString("contries.name"))));
				}

				addresses.add(a);
			}

			result.close();
			statement.close();

			return addresses;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
		Address address = (Address) entity;

		String sql = "";
		if (address instanceof ChargeAddress)
			sql = "INSERT INTO ChargeAddresses (enabled, alias, observation, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		else
			sql = "INSERT INTO DeliveryAddresses (enabled, alias, observation, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// Find the last customer register to get its id
		IDao custumerDao = new CustomerDao();
		Customer lastCustomer = (Customer) custumerDao.findLast();
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, address.isEnabled());
			statement.setString(2, address.getAlias());
			statement.setString(3, address.getObservation());
			statement.setString(4, address.getPublicPlaceType());
			statement.setString(5, address.getPublicPlace());
			statement.setString(6, address.getNumber());
			statement.setString(7, address.getDistrict());
			statement.setString(8, address.getPostalCode());
			statement.setString(9, address.getHomeType());
			statement.setString(10, address.getCity());
			
			statement.setLong(11, address.getState().getId());
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity find(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean update(Entity entity, String operation) {
		/*Address address = (Address) entity;

		String sql = "UPDATE Addresses "
				+ "SET enabled = ?, alias = ?, observation = ?, publicPlaceType = ?, publicPlace = ?, "
				+ "number = ?, district = ?, postalCode = ?, homeType = ?, id_city = ? "
				+ "WHERE id = ?";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, address.isEnabled());
			statement.setString(2, address.getAlias());
			statement.setString(3, address.getObservation());
			statement.setString(4, address.getPublicPlaceType());
			statement.setString(5, address.getPublicPlace());
			statement.setString(6, address.getNumber());
			statement.setString(7, address.getDistrict());
			statement.setString(8, address.getPostalCode());
			statement.setString(9, address.getHomeType());
			statement.setLong(10, address.getCity().getId());
			statement.setLong(11, address.getId());

			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			closeConnection();
		}*/
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity findLast() {
		/*Address address = new Address();

		String query = "SELECT * FROM Addresses WHERE enabled = true ORDER BY ID DESC LIMIT 1";

		try {
			openConnection();

			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;

			result = stmt.executeQuery();
			result.first();

			address.setId(result.getLong("id"));
			address.setEnabled(result.getBoolean("enabled"));
			address.setAlias(result.getString("alias"));
			address.setObservation(result.getString("observation"));
			address.setPublicPlaceType(result.getString("publicPlaceType"));
			address.setPublicPlace(result.getString("publicPlace"));
			address.setNumber(result.getString("number"));
			address.setDistrict(result.getString("district"));
			address.setPostalCode(result.getString("postalCode"));
			address.setHomeType(result.getString("homeType"));

			address.setCity(new City(result.getLong("id_city")));

			stmt.close();

			return address;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}*/
		return null;
	}
	
}

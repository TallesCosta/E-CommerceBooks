package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Address;
import br.com.talles.ecommercebooks.domain.customer.ChargeAddress;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

		/*Address address = (Address) entity;
		String sql = "INSERT INTO Addresses (enabled,  alias, observation, publicPlaceType, publicPlace, number, district, postalCode, "
				+ "homeType, id_city)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CreditCardDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection();
		}*/
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

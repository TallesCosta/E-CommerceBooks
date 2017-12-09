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

public class ChargeAddressDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		ChargeAddress chargeAddress = (ChargeAddress) entity;
		List<Entity> chargeAddresses = new ArrayList();

		String sql = "SELECT ca.*, s.*, c.* "
				+ "FROM ChargeAddresses ca "
				+ "INNER JOIN Customers cus on ca.id_customer = cus.id "
				+ "INNER JOIN States s on ca.id_state = s.id "
				+ "INNER JOIN Countries c on s.id_country = c.id "
				+ "WHERE ca.enabled = ? AND cus.id = ?";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			statement.setLong(2, chargeAddress.getCustomer().getId());


			ResultSet result = statement.executeQuery();

			while (result.next()) {
				ChargeAddress ca = new ChargeAddress();

				ca.setId(result.getLong("chargeAddresses.id"));
				ca.setEnabled(result.getBoolean("chargeAddresses.enabled"));
				ca.setAlias(result.getString("chargeAddresses.alias"));
				ca.setCity(result.getString("chargeAddresses.city"));
				ca.setDistrict(result.getString("chargeAddresses.district"));
				ca.setHomeType(result.getString("chargeAddresses.homeType"));
				ca.setNumber(result.getString("chargeAddresses.number"));
				ca.setObservation(result.getString("chargeAddresses.observation"));
				ca.setPostalCode(result.getString("chargeAddresses.postalCode"));
				ca.setPublicPlace(result.getString("chargeAddresses.publicPlace"));
				ca.setPublicPlaceType(result.getString("chargeAddresses.publicPlaceType"));
				ca.setState(new State(result.getString("states.name"),
						new Country(result.getString("countries.name"))));

				chargeAddresses.add(ca);
			}

			result.close();
			statement.close();

			return chargeAddresses;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
		ChargeAddress chargeAddress = (ChargeAddress) entity;

		String sql = "INSERT INTO ChargeAddresses (enabled, alias, observation, publicPlaceType, publicPlace, number, district, postalCode, homeType, city, id_state, id_customer)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, chargeAddress.isEnabled());
			statement.setString(2, chargeAddress.getAlias());
			statement.setString(3, chargeAddress.getObservation());
			statement.setString(4, chargeAddress.getPublicPlaceType());
			statement.setString(5, chargeAddress.getPublicPlace());
			statement.setString(6, chargeAddress.getNumber());
			statement.setString(7, chargeAddress.getDistrict());
			statement.setString(8, chargeAddress.getPostalCode());
			statement.setString(9, chargeAddress.getHomeType());
			statement.setString(10, chargeAddress.getCity());
			
			statement.setLong(11, chargeAddress.getState().getId());
			statement.setLong(12, chargeAddress.getCustomer().getId());
						
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

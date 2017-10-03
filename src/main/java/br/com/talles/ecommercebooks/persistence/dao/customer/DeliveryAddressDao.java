package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryAddressDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		DeliveryAddress deliveryAddress = (DeliveryAddress) entity;
		String sql = "INSERT INTO DeliveryAddresses (enabled, preferential, alias, observation, publicPlaceType, publicPlace, number, district, postalCode, homeType, id_city, id_customer)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// Find the last book register to get its id
		IDao custumerDao = new CustomerDao();
		Customer lastCustomer = (Customer) custumerDao.findLast();
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, deliveryAddress.isEnabled());
			statement.setBoolean(2, deliveryAddress.isPreferential());
			statement.setString(3, deliveryAddress.getAlias());
			statement.setString(4, deliveryAddress.getObservation());
			statement.setString(5, deliveryAddress.getPublicPlaceType());
			statement.setString(6, deliveryAddress.getPublicPlace());
			statement.setString(7, deliveryAddress.getNumber());
			statement.setString(8, deliveryAddress.getDistrict());
			statement.setString(9, deliveryAddress.getPostalCode());
			statement.setString(10, deliveryAddress.getHomeType());
			
			statement.setLong(11, deliveryAddress.getCity().getId());
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
	public boolean update(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity findLast() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean disable(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean enable(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}

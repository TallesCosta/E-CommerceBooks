package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.Phone;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		Customer customer = (Customer) entity;
        
        // Persists the Phone
        IDao phoneDao = new PhoneDao();
        if(!phoneDao.save(customer.getPhone())){
            return false;
        }		
		customer.setPhone((Phone) phoneDao.findLast());
        
		// Persists the User
        IDao userDao = new UserDao();
        if(!userDao.save(customer.getUser())){
            return false;
        }		
		customer.setUser((User) userDao.findLast());
		
		// SQL query
        String sql = "INSERT INTO Customers (enabled, registry, name, birthDate, gender, "
				+ "id_phone, id_user) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, customer.isEnabled());
            statement.setString(2, customer.getRegistry());
            statement.setString(3, customer.getName());
            statement.setDate(4, new java.sql.Date(customer.getBirthDate().getTime()));
            statement.setString(5, customer.getGender().getName());
            
			statement.setLong(6, customer.getPhone().getId());
			statement.setLong(7, customer.getUser().getId());
            
            statement.execute();
			statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
			closeConnection();
		}
		
		// Persists the DeliveryAddress
		IDao creditCardDao = new CreditCardDao();
		if (!creditCardDao.save(customer.getCreditCard(0)))
			return false;
		
		// Persists the ChargeAddress
		IDao deliveryAddressDao = new DeliveryAddressDao();
		if (!deliveryAddressDao.save(customer.getDeliveryAddress(0)))
			return false;
		
		// Persists the CreditCard
		IDao chargeAddressDao = new ChargeAddressDao();
		return chargeAddressDao.save(customer.getChargeAddress(0));		// If insert association is true, insert customer is true.
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
		Customer customer = new Customer();
		
		String query = "SELECT * FROM Customers WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			customer.setId(result.getLong("id"));
			
			stmt.close();

			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
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

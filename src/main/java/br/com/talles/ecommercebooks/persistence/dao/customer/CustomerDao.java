package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.*;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> customers = new ArrayList();
		String where = queryBuilder(entity);

        String sql = "SELECT c.*, p.*, u.*, "
				+ "GROUP_CONCAT(ca.alias SEPARATOR '-') AS caAlias, GROUP_CONCAT(ca.id SEPARATOR '-') as caId, "
				+ "GROUP_CONCAT(da.alias SEPARATOR '-') AS daAlias, GROUP_CONCAT(da.id SEPARATOR '-') as daId, "
				+ "GROUP_CONCAT(cc.number SEPARATOR '-') AS ccNumber, GROUP_CONCAT(cc.id SEPARATOR '-') as ccId "
				+ "FROM Customers c "
				+ "INNER JOIN Phones p ON c.id_phone = p.id "
				+ "INNER JOIN Users u ON c.id_user = u.id "
				+ "INNER JOIN ChargeAddresses ca ON c.id = ca.id_customer "
				+ "INNER JOIN DeliveryAddresses da ON c.id = da.id_customer "
				+ "INNER JOIN CreditCards cc ON c.id = cc.id_customer "
				+ "WHERE c.enabled = ? " + where
				+ "GROUP BY da.id_customer, cc.id_customer";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            			
            while (result.next()) {
				Customer customer = new Customer();
				
				customer.setId(result.getLong("customers.id"));
				customer.setEnabled(result.getBoolean("customers.enabled"));
				customer.setRegistry(result.getString("customers.registry"));
				customer.setName(result.getString("customers.name"));
				customer.setBirthDate(result.getDate("customers.birthDate"));
				customer.setGender(new Gender(result.getString("customers.gender")));
				// Phone
				customer.setPhone(new Phone(result.getString("phones.ddd"), result.getString("phones.number"), 
						result.getString("phones.phoneType"), result.getLong("phones.id")));
				// User
				customer.setUser(new User(result.getString("users.email"), result.getString("users.password"),
						result.getString("users.password"), result.getLong("users.id")));
				// Charge Address
				String[] chargeAddresses = result.getString("caAlias").split("-");
				String[] chargeAddressesId = result.getString("caId").split("-");
				for(int i = 0; i < chargeAddresses.length; i++){
					customer.addChargeAddress(new ChargeAddress(chargeAddresses[i], new Long(chargeAddressesId[i])));
				}
				// Delivery Address
				String[] deliveryAddresses = result.getString("daAlias").split("-");
				String[] deliveryAddressesId = result.getString("daId").split("-");
				for(int i = 0; i < deliveryAddresses.length; i++){
					customer.addDeliveryAddress(new DeliveryAddress(deliveryAddresses[i], new Long(deliveryAddressesId[i])));
				}
				// Credit Card
				String[] creditCards = result.getString("ccNumber").split("-");
				String[] creditCardsId = result.getString("ccId").split("-");
				for(int i = 0; i < creditCards.length; i++){
					customer.addCreditCard(new CreditCard(creditCards[i], new Long(creditCardsId[i])));
				}
				
				customers.add(customer);
            }
            
            result.close();
            statement.close();
            
            return  customers;
        } catch (SQLException e) {
            throw new RuntimeException (e);   
        } finally {
			closeConnection();
		}
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
		
		// Find the last customer register to get its id
		Customer lastCustomer = (Customer) findLast();
		customer.setId(lastCustomer.getId());
		customer.getDeliveryAddress(0).setCustomer(customer);
		customer.getChargeAddress(0).setCustomer(customer);
		customer.getCreditCard(0).setCustomer(customer);
		
		// Persists the DeliveryAddress
		IDao deliveryAddressDao = new DeliveryAddressDao();
		if (!deliveryAddressDao.save(customer.getDeliveryAddress(0)))
			return false;

		// Persists the ChargeAddress
		IDao chargeAddressDao = new ChargeAddressDao();
		if (!chargeAddressDao.save(customer.getChargeAddress(0)))
			return false;
		
		// Persists the CreditCard
		IDao creditCardDao = new CreditCardDao();
		return creditCardDao.save(customer.getCreditCard(0));		// If insert association is true, insert customer is true.
	}

	@Override
	public boolean delete(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity find(Entity entity) {
		Customer customer = (Customer) entity;
		
		/*String query = "SELECT c.*, p.*, u.*,\n" +
				"ca.alias as caAlias, ca.observation as caObservation, ca.publicPlaceType as caPublicPlaceType,\n" +
				"ca.publicPlace as caPublicPlace, ca.number as caNumber, ca.district as caDistrict,\n" +
				"ca.postalCode as caPostalCode, ca.homeType as caHomeType, ca.city as caCity, ca.id as caId,\n" +
				"caState.id as caState, caCountry.id as caCountry,\n" +
				"da.alias as daAlias, da.observation as dabservation, da.publicPlaceType as daPublicPlaceType,\n" +
				"da.publicPlace as daPublicPlace, da.number as daNumber, da.district as daDistrict,\n" +
				"da.postalCode as daPostalCode, da.homeType as daHomeType, da.city as daCity, da.id as daId,\n" +
				"caState.id as caState, caCountry.id as caCountry\n" +
				"FROM Customers c\n" +
				"INNER JOIN Phones p ON c.id_phone = p.id\n" +
				"INNER JOIN Users u ON c.id_user = u.id\n" +
				"INNER JOIN ChargeAddresses ca ON c.id = ca.id_customer\n" +
				"  INNER JOIN States caState ON ca.id_state = caState.id\n" +
				"  INNER JOIN Countries caCountry ON caState.id_country = caCountry.id\n" +
				"INNER JOIN DeliveryAddresses da ON c.id = da.id_customer\n" +
				"  INNER JOIN States daState ON da.id_state = daState.id\n" +
				"  INNER JOIN Countries daCountry ON daState.id_country = daCountry.id\n" +
				"INNER JOIN CreditCards cc ON c.id = cc.id_customer\n" +
				"WHERE c.id = ?";*/

		String query = "SELECT c.*, p.*, u.*\n" +
				"FROM Customers c\n" +
				"INNER JOIN Phones p ON c.id_phone = p.id\n" +
				"INNER JOIN Users u ON c.id_user = u.id\n" +
				"WHERE c.id = ?";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, customer.getId());
			
			ResultSet result = stmt.executeQuery();
			
			if(result.first()){
				customer.setId(result.getLong("customers.id"));
				customer.setRegistry(result.getString("customers.registry"));
				customer.setName(result.getString("customers.name"));
				customer.setBirthDate(result.getDate("customers.birthDate"));
				customer.setGender(new Gender(result.getString("customers.gender")));
				customer.setPhone(new Phone(result.getString("phones.ddd"), result.getString("phones.number"), 
						result.getString("phones.phoneType"), result.getLong("phones.id")));
				customer.setUser(new User(result.getString("users.email"), result.getString("users.password"),
						result.getString("users.password"), result.getLong("users.id")));
			}
			
			stmt.close();

			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean update(Entity entity, String operation) {
		Customer customer = (Customer) entity;
        
		if (operation.equals("UPDATE")) {
			// Updates the Phone
			PhoneDao phoneDao = new PhoneDao();
			if(!phoneDao.update(customer.getPhone(), operation)){
				return false;
			}

			// Updates the User
			UserDao userDao = new UserDao();
			if(!userDao.update(customer.getUser(), operation)){
				return false;
			}
		}
		
        String sql = "UPDATE Customers "
                + "SET enabled = ?, registry = ?, name = ?, birthDate = ?, gender = ? "
                + "WHERE id = ?";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, customer.isEnabled());
            statement.setString(2, customer.getRegistry());
            statement.setString(3, customer.getName());
            statement.setDate(4, new java.sql.Date(customer.getBirthDate().getTime()));
            statement.setString(5, customer.getGender().getName());
            statement.setLong(6, customer.getId());
            
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
		Customer customer = new Customer();
		
		String query = "SELECT * FROM Customers WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			ResultSet result = stmt.executeQuery();
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
	
	public String queryBuilder(Entity entity) {
		Customer customer = (Customer) entity;
		String where = "";
		
		if (customer.getName() != null && !customer.getName().equals(""))
			where += "AND c.name = '" + customer.getName() + "' ";
		if (customer.getRegistry() != null && !customer.getRegistry().equals(""))
			where += "AND c.registry = '" + customer.getRegistry() + "' ";
		if (customer.getBirthDate().getTime() != new Date(0L).getTime())
			where += "AND c.birthDate = '" + new java.sql.Date(customer.getBirthDate().getTime()) + "' ";
		if (customer.getGender().getName() != null && !customer.getGender().getName().equals(""))
			where += "AND c.gender = '" + customer.getGender().getName() + "' ";
		// Phone
		if (customer.getPhone().getDdd() != null && !customer.getPhone().getDdd().equals(""))
			where += "AND p.ddd = '" + customer.getPhone().getDdd() + "' ";
		if (customer.getPhone().getNumber() != null && !customer.getPhone().getNumber().equals(""))
			where += "AND p.number = '" + customer.getPhone().getNumber() + "' ";
		if (customer.getPhone().getPhoneType() != null && !customer.getPhone().getPhoneType().equals(""))
			where += "AND p.phoneType = '" + customer.getPhone().getPhoneType() + "' ";
		// User
		if (customer.getUser().getEmail() != null && !customer.getUser().getEmail().equals(""))
			where += "AND u.email = '" + customer.getUser().getEmail() + "' ";
		if (customer.getUser().getPassword() != null && !customer.getUser().getPassword().equals(""))
			where += "AND u.password = '" + customer.getUser().getPassword() + "' ";

        /*
        Addresses
		if (customer.getDeliveryAddress(0).getAlias() != null && !customer.getDeliveryAddress(0).getAlias().equals(""))
			where += "AND haAlias = '" + customer.getDeliveryAddress(0).getAlias() + "' "
					+ "OR caAlias = '" + customer.getDeliveryAddress(0).getAlias() + "' "
					+ "OR daAlias = '" + customer.getDeliveryAddress(0).getAlias() + "' ";
		if (customer.getDeliveryAddress(0).getObservation() != null && !customer.getDeliveryAddress(0).getObservation().equals(""))
			where += "AND ha.observation = '" + customer.getDeliveryAddress(0).getObservation() + "' ";
		if (customer.getDeliveryAddress(0).getPublicPlaceType() != null && !customer.getDeliveryAddress(0).getPublicPlaceType().equals(""))
			where += "AND ha.publicPlaceType = '" + customer.getDeliveryAddress(0).getPublicPlaceType() + "' ";
		if (customer.getDeliveryAddress(0).getPublicPlace() != null && !customer.getDeliveryAddress(0).getPublicPlace().equals(""))
			where += "AND ha.publicPlace = '" + customer.getDeliveryAddress(0).getPublicPlace() + "' ";
		if (customer.getDeliveryAddress(0).getNumber() != null && !customer.getDeliveryAddress(0).getNumber().equals(""))
			where += "AND ha.number = '" + customer.getDeliveryAddress(0).getNumber() + "' ";
		if (customer.getDeliveryAddress(0).getDistrict() != null && !customer.getDeliveryAddress(0).getDistrict().equals(""))
			where += "AND ha.district = '" + customer.getDeliveryAddress(0).getDistrict() + "' ";
		if (customer.getDeliveryAddress(0).getPostalCode() != null && !customer.getDeliveryAddress(0).getPostalCode().equals(""))
			where += "AND ha.postalCode = '" + customer.getDeliveryAddress(0).getPostalCode() + "' ";
		if (customer.getDeliveryAddress(0).getHomeType() != null && !customer.getDeliveryAddress(0).getHomeType().equals(""))
			where += "AND ha.homeType = '" + customer.getDeliveryAddress(0).getHomeType() + "' ";
        */
		
		return where;
	}
	
}

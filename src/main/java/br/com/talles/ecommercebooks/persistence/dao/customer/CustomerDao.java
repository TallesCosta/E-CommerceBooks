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
				+ "ha.alias as haAlias, ha.id as haId, ca.alias as caAlias, ca.id as caId, "
				+ "GROUP_CONCAT(da.alias SEPARATOR '-') AS daAlias, GROUP_CONCAT(da.id SEPARATOR '-') as daId, "
				+ "GROUP_CONCAT(cc.number SEPARATOR '-') AS ccNumber, GROUP_CONCAT(cc.id SEPARATOR '-') as ccId "
				+ "FROM Customers c "
				+ "INNER JOIN Phones p ON c.id_phone = p.id "
				+ "INNER JOIN Users u ON c.id_user = u.id "
				+ "INNER JOIN Addresses ha ON c.id_homeAddress = ha.id "
				+ "INNER JOIN Addresses ca ON c.id_chargeAddress = ca.id "
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
				// Home Address
				customer.setHomeAddress(new Address(result.getString("haAlias"), result.getLong("haId")));
				// Charge Address
				customer.setChargeAddress(new Address(result.getString("caAlias"), result.getLong("caId")));
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
		
		// Persists the Home Address
		IDao addressDao = new AddressDao();
        if(!addressDao.save(customer.getHomeAddress())){
            return false;
        }		
		customer.setHomeAddress((Address) addressDao.findLast());
		
		// Persists the Charge Address
        if(!addressDao.save(customer.getChargeAddress())){
            return false;
        }		
		customer.setChargeAddress((Address) addressDao.findLast());
		
		// SQL query
        String sql = "INSERT INTO Customers (enabled, registry, name, birthDate, gender, "
				+ "id_phone, id_user, id_homeAddress, id_chargeAddress) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
			statement.setLong(8, customer.getHomeAddress().getId());
			statement.setLong(9, customer.getChargeAddress().getId());
            
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
		
		// Persists the DeliveryAddress
		IDao deliveryAddressDao = new DeliveryAddressDao();
		if (!deliveryAddressDao.save(customer.getDeliveryAddress(0)))
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
		
		String query = "SELECT c.*, p.*, u.*, "
				+ "ha.alias as haAlias, ha.observation as haObservation, ha.publicPlaceType as haPublicPlaceType, "
				+ "ha.publicPlace as haPublicPlace, ha.number as haNumber, ha.district as haDistrict, "
				+ "ha.postalCode as haPostalCode, ha.homeType as haHomeType, ha.id_city as haId_city, ha.id as haId, "
				+ "haCity.id as haCity, haState.id as haState, haCountry.id as haCountry, "
				+ "ca.alias as caAlias, ca.observation as caObservation, ca.publicPlaceType as caPublicPlaceType, "
				+ "ca.publicPlace as caPublicPlace, ca.number as caNumber, ca.district as caDistrict, "
				+ "ca.postalCode as caPostalCode, ca.homeType as caHomeType, ca.id_city as caId_city, ca.id as caId, "
				+ "caCity.id as caCity, caState.id as caState, caCountry.id as caCountry "
				+ "FROM Customers c "
				+ "INNER JOIN Phones p ON c.id_phone = p.id "
				+ "INNER JOIN Users u ON c.id_user = u.id "
				+ "INNER JOIN Addresses ha ON c.id_homeAddress = ha.id "
				+ "INNER JOIN Cities haCity ON ha.id_city = haCity.id "
				+ "INNER JOIN States haState ON haCity.id_state = haState.id "
				+ "INNER JOIN Countries haCountry ON haState.id_country = haCountry.id "
				+ "INNER JOIN Addresses ca ON c.id_chargeAddress = ca.id "
				+ "INNER JOIN Cities caCity ON ca.id_city = caCity.id "
				+ "INNER JOIN States caState ON caCity.id_state = caState.id "
				+ "INNER JOIN Countries caCountry ON caState.id_country = caCountry.id "
				+ "WHERE c.id = ?";
		
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
				customer.setHomeAddress(new Address(result.getString("haAlias"), result.getString("haObservation"),
						result.getString("haPublicPlaceType"), result.getString("haPublicPlace"), result.getString("haNumber"), 
						result.getString("haDistrict"), result.getString("haPostalCode"), result.getString("haHomeType"), 
						new City(result.getLong("haCity"), 
								new State(result.getLong("haState"), 
										new Country(result.getLong("haCountry")))), result.getLong("haId")));
				customer.setChargeAddress(new Address(result.getString("caAlias"), result.getString("caObservation"),
						result.getString("caPublicPlaceType"), result.getString("caPublicPlace"), result.getString("caNumber"), 
						result.getString("caDistrict"), result.getString("caPostalCode"), result.getString("caHomeType"), 
						new City(result.getLong("haCity"), 
								new State(result.getLong("haState"), 
										new Country(result.getLong("haCountry")))), result.getLong("caId")));
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

			AddressDao addressDao = new AddressDao();

			// Updates the Home Address
			if(!addressDao.update(customer.getHomeAddress(), operation)){
				return false;
			}

			// Updates the Charge Address
			if(!addressDao.update(customer.getChargeAddress(), operation)){
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
		// Home Address
		if (customer.getHomeAddress().getAlias() != null && !customer.getHomeAddress().getAlias().equals(""))
			where += "AND haAlias = '" + customer.getHomeAddress().getAlias() + "' "
					+ "OR caAlias = '" + customer.getHomeAddress().getAlias() + "' "
					+ "OR daAlias = '" + customer.getHomeAddress().getAlias() + "' ";
		if (customer.getHomeAddress().getObservation() != null && !customer.getHomeAddress().getObservation().equals(""))
			where += "AND ha.observation = '" + customer.getHomeAddress().getObservation() + "' ";
		if (customer.getHomeAddress().getPublicPlaceType() != null && !customer.getHomeAddress().getPublicPlaceType().equals(""))
			where += "AND ha.publicPlaceType = '" + customer.getHomeAddress().getPublicPlaceType() + "' ";
		if (customer.getHomeAddress().getPublicPlace() != null && !customer.getHomeAddress().getPublicPlace().equals(""))
			where += "AND ha.publicPlace = '" + customer.getHomeAddress().getPublicPlace() + "' ";
		if (customer.getHomeAddress().getNumber() != null && !customer.getHomeAddress().getNumber().equals(""))
			where += "AND ha.number = '" + customer.getHomeAddress().getNumber() + "' ";
		if (customer.getHomeAddress().getDistrict() != null && !customer.getHomeAddress().getDistrict().equals(""))
			where += "AND ha.district = '" + customer.getHomeAddress().getDistrict() + "' ";
		if (customer.getHomeAddress().getPostalCode() != null && !customer.getHomeAddress().getPostalCode().equals(""))
			where += "AND ha.postalCode = '" + customer.getHomeAddress().getPostalCode() + "' ";
		if (customer.getHomeAddress().getHomeType() != null && !customer.getHomeAddress().getHomeType().equals(""))
			where += "AND ha.homeType = '" + customer.getHomeAddress().getHomeType() + "' ";
//		// Charge Address
//		if (customer.getChargeAddress().getAlias() != null && !customer.getChargeAddress().getAlias().equals(""))
//			where += "AND caAlias = " + customer.getChargeAddress().getAlias() + " ";
//		if (customer.getChargeAddress().getObservation() != null && !customer.getChargeAddress().getObservation().equals(""))
//			where += "AND ca.observation = " + customer.getChargeAddress().getObservation() + " ";
//		if (customer.getChargeAddress().getPublicPlaceType() != null && !customer.getChargeAddress().getPublicPlaceType().equals(""))
//			where += "AND ca.publicPlaceType = " + customer.getChargeAddress().getPublicPlaceType() + " ";
//		if (customer.getChargeAddress().getPublicPlace() != null && !customer.getChargeAddress().getPublicPlace().equals(""))
//			where += "AND ca.publicPlace = " + customer.getChargeAddress().getPublicPlace() + " ";
//		if (customer.getChargeAddress().getNumber() != null && !customer.getChargeAddress().getNumber().equals(""))
//			where += "AND ca.number = " + customer.getChargeAddress().getNumber() + " ";
//		if (customer.getChargeAddress().getDistrict() != null && !customer.getChargeAddress().getDistrict().equals(""))
//			where += "AND ca.district = " + customer.getChargeAddress().getDistrict() + " ";
//		if (customer.getChargeAddress().getPostalCode() != null && !customer.getChargeAddress().getPostalCode().equals(""))
//			where += "AND ca.postalCode = " + customer.getChargeAddress().getPostalCode() + " ";
//		if (customer.getChargeAddress().getHomeType() != null && !customer.getChargeAddress().getHomeType().equals(""))
//			where += "AND ca.homeType = " + customer.getChargeAddress().getHomeType() + " ";
		
		return where;
	}
	
}

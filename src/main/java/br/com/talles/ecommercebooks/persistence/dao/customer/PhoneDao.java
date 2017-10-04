package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Phone;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		Phone phone = (Phone) entity;
		String sql = "INSERT INTO Phones (enabled, ddd, number, phoneType)"
				+ "VALUES(?, ?, ?, ?)";

		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, phone.isEnabled());
			statement.setString(2, phone.getDdd());
			statement.setString(3, phone.getNumber());
			statement.setString(4, phone.getPhoneType());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(PhoneDao.class.getName()).log(Level.SEVERE, null, ex);
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
		Phone phone = (Phone) entity;
		
        String sql = "UPDATE Phones "
                + "SET enabled = ?, ddd = ?, number = ?, phoneType = ? "
                + "WHERE id = ?";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, phone.isEnabled());
            statement.setString(2, phone.getDdd());
            statement.setString(3, phone.getNumber());
            statement.setString(4, phone.getPhoneType());
            statement.setLong(5, phone.getId());
            
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
		Phone phone = new Phone();
		
		String query = "SELECT * FROM Phones WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			phone.setId(result.getLong("id"));
			phone.setEnabled(result.getBoolean("enabled"));
			phone.setDdd(result.getString("ddd"));
			phone.setNumber(result.getString("number"));
			phone.setPhoneType(result.getString("phoneType"));
			
			stmt.close();

			return phone;
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

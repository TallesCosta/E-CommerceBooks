package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		openConnection();

		User user = (User) entity;
		String sql = "INSERT INTO Users (enabled, email, password)"
				+ "VALUES(?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, user.isEnabled());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
		User user = (User) entity;
		
        String sql = "UPDATE Users "
                + "SET enabled = ?, email = ?, password = ? "
                + "WHERE id = ?";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, user.isEnabled());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            
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
		User user = new User();
		
		String query = "SELECT * FROM Users WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			user.setId(result.getLong("id"));
			user.setEnabled(result.getBoolean("enabled"));
			user.setEmail(result.getString("email"));
			user.setPassword(result.getString("password"));
			
			stmt.close();

			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}
	
	public String queryBuilder(Entity entity) {
		User user = (User) entity;
		String where = "";
		
		if (user.getEmail() != null && !user.getEmail().equals(""))
			where += "AND c.email = '" + user.getEmail() + "' ";
		if (user.getPassword() != null && !user.getPassword().equals(""))
			where += "AND c.password = '" + user.getPassword()+ "' ";
		
		return where;
	}
	
}

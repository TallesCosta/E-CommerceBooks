package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Dimension;
import br.com.talles.ecommercebooks.domain.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DimensionDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		openConnection();

		Dimension dimension = (Dimension) entity;
		String sql = "INSERT INTO dimensions(enabled, height, widht, weight, depth)"
				+ "VALUES(?, ?, ?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, dimension.isEnabled());
			statement.setDouble(2, dimension.getHeight());
			statement.setDouble(3, dimension.getWidht());
			statement.setDouble(4, dimension.getWeight());
			statement.setDouble(5, dimension.getDepth());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(DimensionDao.class.getName()).log(Level.SEVERE, null, ex);
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
	public Entity findLast(){
		Dimension dimension = new Dimension();
		
		String query = "SELECT * FROM Dimensions WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			dimension.setId(result.getLong("id"));
			dimension.setEnabled(result.getBoolean("enabled"));
			dimension.setHeight(result.getDouble("height"));
			dimension.setWidht(result.getDouble("widht"));
			dimension.setWeight(result.getDouble("weight"));
			dimension.setDepth(result.getDouble("depth"));
			
			stmt.close();

			return dimension;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public List<Entity> selectDisabled() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}

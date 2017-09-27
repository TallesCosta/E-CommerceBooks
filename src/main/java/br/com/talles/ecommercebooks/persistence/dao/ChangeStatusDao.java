package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.ChangeStatus;
import br.com.talles.ecommercebooks.domain.DeactivationCategory;
import br.com.talles.ecommercebooks.domain.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeStatusDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		ChangeStatus changeStatus = (ChangeStatus) entity;
		String sql = "INSERT INTO ChangeStatus(enabled, justification, id_deactivationCategory)"
				+ "VALUES(?, ?, ?)";

		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, changeStatus.isEnabled());
			statement.setString(2, changeStatus.getJustification());
			statement.setLong(3, changeStatus.getStatusCategory().getId());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(ChangeStatusDao.class.getName()).log(Level.SEVERE, null, ex);
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
		ChangeStatus changeStatus = new ChangeStatus();
		
		String query = "SELECT * FROM ChangeStatus WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			changeStatus.setId(result.getLong("id"));
			changeStatus.setEnabled(result.getBoolean("enabled"));
			changeStatus.setJustification(result.getString("justification"));
			changeStatus.setStatusCategory(new DeactivationCategory(result.getLong("id_deactivationCategory")));
			
			stmt.close();

			return changeStatus;
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

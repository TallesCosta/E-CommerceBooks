package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.book.ChangeStatus;
import br.com.talles.ecommercebooks.domain.book.DeactivationCategory;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.ActivationCategory;

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
		String sql;
		
		if (changeStatus.getStatusCategory() instanceof DeactivationCategory) {
			sql = "INSERT INTO ChangeStatus(enabled, justification, id_deactivationCategory)"
				+ "VALUES(?, ?, ?)";
		} else {
			sql = "INSERT INTO ChangeStatus(enabled, justification, id_activationCategory)"
					+ "VALUES(?, ?, ?)";
		}
		
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
		ChangeStatus changeStatus = (ChangeStatus) entity;
        String sql = "SELECT * FROM ChangeStatus "
                + "WHERE id = ?";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, changeStatus.getId());
            
            ResultSet result = statement.executeQuery();
            
            if(result.first()){
                changeStatus.setId(result.getLong("id"));
				changeStatus.setEnabled(result.getBoolean("enabled"));
				changeStatus.setJustification(result.getString("justification"));
				
				if (result.getLong("id_activationCategory") != 0L) {
					changeStatus.setStatusCategory(new ActivationCategory(
							result.getString("id_activationCategory")));
				} else {
					changeStatus.setStatusCategory(new DeactivationCategory(
							result.getString("id_deactivationCategory")));
				}
            }
            
            result.close();
            statement.close();
            
            return  changeStatus;
        } catch(SQLException e) {
            throw new RuntimeException(e);   
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean update(Entity entity) {
		ChangeStatus changeStatus = (ChangeStatus) entity;
        
		String sql;
		
        // Updates the AbstractCategory
		if (changeStatus.getStatusCategory() instanceof DeactivationCategory) {
			sql = "UPDATE ChangeStatus "
                + "SET enabled = ?, justification = ?, id_deactivationCategory = ?, id_activationCategory = ? "
                + "WHERE id = ?";
		} else {
			sql = "UPDATE ChangeStatus "
                + "SET enabled = ?, justification = ?, id_activationCategory = ?, id_deactivationCategory = ? "
                + "WHERE id = ?";
		}
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, changeStatus.isEnabled());
            statement.setString(2, changeStatus.getJustification());
            statement.setLong(3, changeStatus.getStatusCategory().getId());
            statement.setLong(4, 0L);
            statement.setLong(5, changeStatus.getId());
            
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

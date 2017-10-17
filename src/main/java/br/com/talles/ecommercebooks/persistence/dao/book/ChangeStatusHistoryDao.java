package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.book.ChangeStatus;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.StatusCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeStatusHistoryDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		ChangeStatus changeStatus = (ChangeStatus) entity;
		
		String sql = "INSERT INTO ChangeStatus(enabled, justification, id_statusCategory)"
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
				changeStatus.setStatusCategory(new StatusCategory(result.getLong("id_statusCategory")));
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
	public boolean update(Entity entity, String operation) {
		ChangeStatus changeStatus = (ChangeStatus) entity;
        
		String sql = "UPDATE ChangeStatus "
                + "SET enabled = ?, justification = ?, id_statusCategory = ? "
                + "WHERE id = ?";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, changeStatus.isEnabled());
            statement.setString(2, changeStatus.getJustification());
            statement.setLong(3, changeStatus.getStatusCategory().getId());
            statement.setLong(4, changeStatus.getId());
            
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
			changeStatus.setStatusCategory(new StatusCategory(result.getLong("id_statusCategory")));
			
			stmt.close();

			return changeStatus;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}
	
}

package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.DeactivationCategory;
import br.com.talles.ecommercebooks.domain.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeactivationCategoryDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		List<Entity> deactivationCategories = new ArrayList();
        String sql = "SELECT * FROM DeactivationCategories WHERE enabled = ?";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                DeactivationCategory deactivationCategory = new DeactivationCategory();
                
                deactivationCategory.setId(result.getLong("id"));
                deactivationCategory.setEnabled(result.getBoolean("enabled"));
                deactivationCategory.setName(result.getString("name"));
                deactivationCategory.setDescription(result.getString("description"));
                
                deactivationCategories.add(deactivationCategory);
            }
            
            result.close();
            statement.close();
            
            return deactivationCategories;
        }catch(SQLException e){
            throw new RuntimeException(e);
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

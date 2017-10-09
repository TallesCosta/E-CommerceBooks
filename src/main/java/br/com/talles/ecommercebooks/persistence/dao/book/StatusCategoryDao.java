package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.book.ActivationCategory;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivationCategoryDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		List<Entity> activationCategories = new ArrayList();
        String sql = "SELECT * FROM ActivationCategories WHERE enabled = ?";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                ActivationCategory activationCategory = new ActivationCategory();
                
                activationCategory.setId(result.getLong("id"));
                activationCategory.setEnabled(result.getBoolean("enabled"));
                activationCategory.setName(result.getString("name"));
                activationCategory.setDescription(result.getString("description"));
                
                activationCategories.add(activationCategory);
            }
            
            result.close();
            statement.close();
            
            return activationCategories;
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
	public boolean update(Entity entity, String operation) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity findLast() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}

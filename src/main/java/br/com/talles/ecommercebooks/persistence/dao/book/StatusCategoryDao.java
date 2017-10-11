package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.StatusCategory;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusCategoryDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> statusCategories = new ArrayList();
        String sql = "SELECT * FROM StatusCategories WHERE enabled = ? AND id <> -1";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                StatusCategory statusCategory = new StatusCategory();
                
                statusCategory.setId(result.getLong("id"));
                statusCategory.setEnabled(result.getBoolean("enabled"));
                statusCategory.setName(result.getString("name"));
                statusCategory.setActivationCategory(result.getBoolean("activationStatus"));
                statusCategory.setDescription(result.getString("description"));
                
                statusCategories.add(statusCategory);
            }
            
            result.close();
            statement.close();
            
            return statusCategories;
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

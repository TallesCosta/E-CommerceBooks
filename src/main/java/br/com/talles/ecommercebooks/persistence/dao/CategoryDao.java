package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Category;
import br.com.talles.ecommercebooks.domain.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		List<Entity> categories = new ArrayList();
        String sql = "SELECT * FROM categories";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                Category category = new Category();
                
                category.setId(result.getLong("id"));
                category.setEnabled(result.getBoolean("enabled"));
                category.setName(result.getString("name"));
                category.setDescription(result.getString("description"));
                
                categories.add(category);
            }
            
            result.close();
            statement.close();
            
            return  categories;
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
	
}

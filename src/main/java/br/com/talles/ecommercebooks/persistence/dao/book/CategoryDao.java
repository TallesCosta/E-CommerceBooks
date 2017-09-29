package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.book.Category;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
		List<Entity> categories = new ArrayList();
        String sql = "SELECT * FROM categories WHERE enabled = ?";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
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
		}catch(Exception e){
           throw e;
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

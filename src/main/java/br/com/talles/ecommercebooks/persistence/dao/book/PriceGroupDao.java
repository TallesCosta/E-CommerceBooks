package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.book.PriceGroup;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceGroupDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> priceGroups = new ArrayList();
        String sql = "SELECT * FROM priceGroups WHERE enabled = ?";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                PriceGroup priceGroup = new PriceGroup();
                
                priceGroup.setId(result.getLong("id"));
                priceGroup.setEnabled(result.getBoolean("enabled"));
                priceGroup.setMarkup(result.getDouble("markup"));
                
                priceGroups.add(priceGroup);
            }
            
            result.close();
            statement.close();
            
            return  priceGroups;
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

package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.PriceGroup;
import br.com.talles.ecommercebooks.domain.Entity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceGroupDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		List<Entity> priceGroups = new ArrayList();
        String sql = "SELECT * FROM priceGroups WHERE enabled = true";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
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
	public boolean update(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity findLast() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Entity> selectDisabled() {
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

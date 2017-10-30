package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> sales = new ArrayList();
		
        String sql = "SELECT s.* "
				+ "FROM Sales s "
				+ "WHERE s.enabled = ? ";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            			
            while (result.next()) {
				Sale sale = new Sale();
				
				sale.setId(result.getLong("sales.id"));
				sale.setEnabled(result.getBoolean("sales.enabled"));
				sale.setSaleNumber(result.getString("sales.saleNumber"));
				sale.setDate(result.getDate("sales.date"));
				sale.setPrice(result.getDouble("sales.price"));
				sale.setTotalAmount(result.getInt("sales.totalAmount"));
				sale.setDeliveryForecast(result.getDate("sales.deliveryForecast"));
				
				sales.add(sale);
            }
            
            result.close();
            statement.close();
            
            return  sales;
        } catch (SQLException e) {
            throw new RuntimeException (e);   
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

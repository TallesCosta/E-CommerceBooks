package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		Sale sale = (Sale) entity;

		// TODO: Add id_promotionalCoupon
		String sql = "INSERT INTO Sales (enabled, saleNumber, date, price, totalAmount, deliveryForecast, "
				+ "status, id_deliveryAddress, id_creditCard, id_customer) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, sale.isEnabled());
			statement.setString(2, sale.getSaleNumber());
			statement.setDate(3, new java.sql.Date(sale.getDate().getTime()));
			statement.setDouble(4, sale.getPrice());
			statement.setInt(5, sale.getTotalAmount());
			statement.setDate(6, new java.sql.Date(sale.getDeliveryForecast().getTime()));
			statement.setString(7, sale.getStatus().getName());

			statement.setLong(8, sale.getDeliveryAddress().getId());
			statement.setLong(9, sale.getCreditCard().getId());
			statement.setLong(10, sale.getCustomer().getId());

			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
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

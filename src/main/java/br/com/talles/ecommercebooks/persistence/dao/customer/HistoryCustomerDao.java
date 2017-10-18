package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.History;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoryCustomerDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		String sql = "INSERT INTO HistoryCustomers(enabled, date, serializedObject, id_customer, id_user)"
				+ "VALUES(?, ?, ?, ?, ?)";

		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, entity.isEnabled());
			statement.setTimestamp(2, new Timestamp(entity.getHistory().getDate().getTime()));
			statement.setObject(3, entity);
			statement.setLong(4, entity.getId());
			statement.setLong(5, entity.getHistory().getUser().getId());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(HistoryCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
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
		String sql = "SELECT * FROM HistoryBooks WHERE id_customer = ? ORDER BY date DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, entity.getId());
            
            ResultSet result = statement.executeQuery();
			
			if (result.first()) {				
				entity.setId(result.getLong("id_customer"));
				entity.setHistory(new History(new Date(result.getTimestamp("date").getTime()), new User(result.getLong("id_user")), 
						(Entity) result.getObject("serializedObject"),  result.getLong("id")));
			}
			
			statement.close();

			return entity;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
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

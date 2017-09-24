package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.SaleParameterization;
import br.com.talles.ecommercebooks.domain.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleParameterizationDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		openConnection();

		SaleParameterization saleParameterization = (SaleParameterization) entity;
		String sql = "INSERT INTO SaleParameterizations(enabled, minSaleLimit, periodicity)"
				+ "VALUES(?, ?, ?)";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, true);
			statement.setInt(2, saleParameterization.getMinSaleLimit());
			statement.setInt(3, saleParameterization.getPeriodicity());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(SaleParameterizationDao.class.getName()).log(Level.SEVERE, null, ex);
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
	public boolean update(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity findLast() {
		SaleParameterization saleParameterization = new SaleParameterization();
		
		String query = "SELECT * FROM SaleParameterizations ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			saleParameterization.setId(result.getLong("id"));
			saleParameterization.setEnabled(result.getBoolean("enabled"));
			saleParameterization.setMinSaleLimit(result.getInt("minSaleLimit"));
			saleParameterization.setPeriodicity(result.getInt("periodicity"));
			
			stmt.close();

			return saleParameterization;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}
	
}

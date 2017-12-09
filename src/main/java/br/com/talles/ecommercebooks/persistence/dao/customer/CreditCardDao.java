package br.com.talles.ecommercebooks.persistence.dao.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CardCompany;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCardDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		CreditCard creditCard = (CreditCard) entity;
		List<Entity> creditCards = new ArrayList();

		String sql = "SELECT cc.*, comp.* "
				+ "FROM CreditCards cc "
				+ "INNER JOIN Customers cus on cc.id_customer = cus.id "
				+ "INNER JOIN CardCompanies comp on cc.id_cardCompany = comp.id "
				+ "WHERE cc.enabled = ? AND cus.id = ?";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			statement.setLong(2, creditCard.getCustomer().getId());

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				CreditCard cc = new CreditCard();

				cc.setId(result.getLong("creditCards.id"));
				cc.setEnabled(result.getBoolean("creditCards.enabled"));
				cc.setNumber(result.getString("creditCards.number"));
				cc.setPrintedName(result.getString("creditCards.PrintedName"));
				cc.setSecurityCode(result.getString("creditCards.securityCode"));
				cc.setExpirationDate(result.getDate("creditCards.expirationDate"));
				cc.setCardCompany(new CardCompany(result.getString("cardCompanies.name")));

				creditCards.add(cc);
			}

			result.close();
			statement.close();

			return creditCards;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
		CreditCard creditCard = (CreditCard) entity;
		String sql = "INSERT INTO CreditCards (enabled, number, printedName, securityCode, expirationDate, id_cardCompany, id_customer)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, creditCard.isEnabled());
			statement.setString(2, creditCard.getNumber());
			statement.setString(3, creditCard.getPrintedName());
			statement.setString(4, creditCard.getSecurityCode());
			statement.setDate(5, new java.sql.Date(creditCard.getExpirationDate().getTime()));
			
			statement.setLong(6, creditCard.getCardCompany().getId());
			statement.setLong(7, creditCard.getCustomer().getId());
						
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(CreditCardDao.class.getName()).log(Level.SEVERE, null, ex);
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

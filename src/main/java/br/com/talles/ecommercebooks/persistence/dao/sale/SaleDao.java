package br.com.talles.ecommercebooks.persistence.dao.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.sale.*;
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
				sale.setStatus(new Status(result.getString("sales.status")));
				sale.setPrice(result.getDouble("sales.price"));
				sale.setTotalAmount(result.getInt("sales.totalAmount"));
				sale.setDelivery(new Delivery(result.getDate("sales.deliveryForecast")));
				
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
		String sql = "INSERT INTO Sales (enabled, saleNumber, date, price, totalAmount, status, "
				+ "deliveryForecast, shippingCost, id_deliveryAddress, id_creditCard, id_customer) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, sale.isEnabled());
			statement.setString(2, sale.getSaleNumber());
			statement.setDate(3, new java.sql.Date(sale.getDate().getTime()));
			statement.setDouble(4, sale.getPrice());
			statement.setInt(5, sale.getTotalAmount());
			statement.setString(6, sale.getStatus().getName());
			statement.setDate(7, new java.sql.Date(sale.getDelivery().getDeliveryForecast().getTime()));
			statement.setDouble(8, sale.getDelivery().getShippingCost().getValue());

			statement.setLong(9, sale.getDelivery().getDeliveryAddress().getId());
			statement.setLong(10, sale.getCreditCard().getId());
			statement.setLong(11, sale.getCustomer().getId());

			statement.execute();
			statement.close();

		} catch (SQLException ex) {
			Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection();
		}

		Sale lastSale = (Sale) findLast();
		sale.setId(lastSale.getId());

		sql = "INSERT INTO SaleItems (enabled, unitaryPrice, amount, id_sale, id_book) "
				+ "VALUES(?, ?, ?, ?, ?)";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			for (SaleItem saleItem : sale.getSaleItems()) {
				statement.setBoolean(1, saleItem.isEnabled());
				statement.setDouble(2, saleItem.getUnitaryPrice());
				statement.setInt(3, saleItem.getAmount());
				statement.setLong(4, sale.getId());
				statement.setLong(5, saleItem.getBook().getId());

				statement.execute();
			}

			statement.close();

		} catch (SQLException ex) {
			Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection();
		}

		return true;
	}

	@Override
	public boolean delete(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity find(Entity entity) {
		Sale sale = (Sale) entity;

		String sql = "SELECT s.*, si.unitaryPrice, si.amount, b.id, b.title, " +
				"c.id, c.name, cc.id, cc.number, da.id, da.alias " +
				"FROM Sales s " +
				"INNER JOIN SaleItems si on s.id = si.id_sale " +
				"INNER JOIN Books b on si.id_book = b.id " +
				"INNER JOIN Customers c on c.id = s.id_customer " +
				"INNER JOIN CreditCards cc on cc.id = s.id_creditCard " +
				"INNER JOIN DeliveryAddresses da on da.id = s.id_deliveryAddress " +
				"WHERE s.id = ?;";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, sale.getId());

			ResultSet result = statement.executeQuery();

			sale.setSaleItems(new ArrayList<SaleItem>());

			if(result.first()){
				// Sale datas
				sale.setId(result.getLong("sales.id"));
				sale.setEnabled(result.getBoolean("sales.enabled"));
				sale.setSaleNumber(result.getString("sales.saleNumber"));
				sale.setDate(result.getDate("sales.date"));
				sale.setStatus(new Status(result.getString("sales.status")));
				sale.setPrice(result.getDouble("sales.price"));
				sale.setTotalAmount(result.getInt("sales.totalAmount"));

				// Customer datas
				sale.setCustomer(new Customer(
						result.getString("customers.name"),
						result.getLong("customers.id")));

				// Delivery datas
				sale.setDelivery(new Delivery(result.getDate("sales.deliveryForecast"),
						new ShippingCost(result.getDouble("sales.shippingCost")),
						new DeliveryAddress(
								result.getString("deliveryaddresses.alias"),
								result.getLong("deliveryaddresses.id"))));

				// CreditCard datas
				sale.setCreditCard(new CreditCard(
						result.getString("creditcards.number"),
						result.getLong("creditcards.id")));

				do {
					SaleItem saleItem = new SaleItem(
							result.getDouble("saleitems.unitaryPrice"),
							result.getInt("saleitems.amount"));
					saleItem.setBook(new Book(
							result.getString("books.title"),
							result.getLong("books.id")));

					sale.getSaleItems().add(saleItem);
				} while(result.next());
			}

			result.close();
			statement.close();

			return sale;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	@Override
	public boolean update(Entity entity, String operation) {
        Sale sale = (Sale) entity;

        String sql = "UPDATE Sales "
                + "SET enabled = ?, status = ? "
                + "WHERE id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, sale.isEnabled());
            statement.setString(2, sale.getStatus().getName());

			statement.setLong(3, sale.getId());

            statement.execute();
            statement.close();

            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            closeConnection();
        }
	}

	@Override
	public Entity findLast() {
		Sale sale = new Sale();

		String query = "SELECT * FROM Sales WHERE enabled = true ORDER BY ID DESC LIMIT 1";

		try {
			openConnection();

			PreparedStatement stmt = conn.prepareStatement(query);

			ResultSet result = stmt.executeQuery();
			result.first();

			sale.setId(result.getLong("id"));

			stmt.close();

			return sale;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}
	
}

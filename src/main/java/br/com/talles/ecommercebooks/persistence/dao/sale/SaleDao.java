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
		List<Entity> orders = new ArrayList();
		String where = queryBuilder(entity);
		
        String sql = "SELECT s.* "
				+ "FROM Sales s "
				+ "INNER JOIN Customers c on s.id_customer = c.id "
				+ "WHERE s.enabled = ? " + where;
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Order order = entity instanceof Sale ? new Sale() : new OrderRequest();
				
				order.setId(result.getLong("sales.id"));
				order.setEnabled(result.getBoolean("sales.enabled"));
				order.setSaleNumber(result.getString("sales.saleNumber"));
				order.setDate(result.getDate("sales.date"));
				order.setStatus(new Status(result.getString("sales.status")));
				order.setPrice(result.getDouble("sales.price"));
				order.setTotalAmount(result.getInt("sales.totalAmount"));
				order.setDelivery(new Delivery(result.getDate("sales.deliveryForecast")));
				
				orders.add(order);
            }
            
            result.close();
            statement.close();
            
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException (e);   
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
        Order order = (Order) entity;

		String sql = "";
		if (order.getPromotionalCoupon().getId() == 0L)
			sql = "INSERT INTO Sales (enabled, saleNumber, date, price, totalAmount, status, "
					+ "deliveryForecast, shippingCost, id_deliveryAddress, id_creditCard, id_customer) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		else
			sql = "INSERT INTO Sales (enabled, saleNumber, date, price, totalAmount, status, "
					+ "deliveryForecast, shippingCost, id_deliveryAddress, id_creditCard, id_customer, id_promotionalCoupon) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, order.isEnabled());
			statement.setString(2, order.getSaleNumber());
			statement.setDate(3, new java.sql.Date(order.getDate().getTime()));
			statement.setDouble(4, order.getPrice());
			statement.setInt(5, order.getTotalAmount());
			statement.setString(6, order.getStatus().getName());
			statement.setDate(7, new java.sql.Date(order.getDelivery().getDeliveryForecast().getTime()));
			statement.setDouble(8, order.getDelivery().getShippingCost().getValue());

			statement.setLong(9, order.getDelivery().getDeliveryAddress().getId());
			statement.setLong(10, order.getCreditCard().getId());
			statement.setLong(11, order.getCustomer().getId());

			if (order.getPromotionalCoupon().getId() != 0L)
				statement.setLong(12, order.getPromotionalCoupon().getId());

			statement.execute();
			statement.close();

		} catch (SQLException ex) {
			Logger.getLogger(SaleDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			closeConnection();
		}

		Order lastSale = (Order) findLast();
		order.setId(lastSale.getId());

		sql = "INSERT INTO SaleItems (enabled, unitaryPrice, amount, id_sale, id_book) "
				+ "VALUES(?, ?, ?, ?, ?)";

		try {
			openConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			for (SaleItem saleItem : order.getSaleItems()) {
				statement.setBoolean(1, saleItem.isEnabled());
				statement.setDouble(2, saleItem.getUnitaryPrice());
				statement.setInt(3, saleItem.getAmount());
				statement.setLong(4, order.getId());
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
		Order order = (Order) entity;

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
			statement.setLong(1, order.getId());

			ResultSet result = statement.executeQuery();

			order.setSaleItems(new ArrayList<SaleItem>());

			if(result.first()){
				// Sale datas
				order.setId(result.getLong("sales.id"));
				order.setEnabled(result.getBoolean("sales.enabled"));
				order.setSaleNumber(result.getString("sales.saleNumber"));
				order.setDate(result.getDate("sales.date"));
				order.setStatus(new Status(result.getString("sales.status")));
				order.setPrice(result.getDouble("sales.price"));
				order.setTotalAmount(result.getInt("sales.totalAmount"));

				// Customer datas
				order.setCustomer(new Customer(
						result.getString("customers.name"),
						result.getLong("customers.id")));

				// Delivery datas
				order.setDelivery(new Delivery(result.getDate("sales.deliveryForecast"),
						new ShippingCost(result.getDouble("sales.shippingCost")),
						new DeliveryAddress(
								result.getString("deliveryaddresses.alias"),
								result.getLong("deliveryaddresses.id"))));

				// CreditCard datas
				order.setCreditCard(new CreditCard(
						result.getString("creditcards.number"),
						result.getLong("creditcards.id")));

				do {
					SaleItem saleItem = new SaleItem(
							result.getDouble("saleitems.unitaryPrice"),
							result.getInt("saleitems.amount"));
					saleItem.setBook(new Book(
							result.getString("books.title"),
							result.getLong("books.id")));

					order.getSaleItems().add(saleItem);
				} while(result.next());
			}

			result.close();
			statement.close();

		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}

		if (order.getStatus().getName().equals("TROCA EM ANÁLISE")) {
			IDao dao = new ExchangeDao();
			order.setExchange(new Exchange(new Order(order.getId())));
			dao.find(order.getExchange());
		}

		return order;
	}

	@Override
	public boolean update(Entity entity, String operation) {
        Order order = (Order) entity;

        String sql = "UPDATE Sales "
                + "SET enabled = ?, status = ? "
                + "WHERE id = ?";

        try {
            openConnection();

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setBoolean(1, order.isEnabled());
            statement.setString(2, order.getStatus().getName());

			statement.setLong(3, order.getId());

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
		Order order = new Order();

		String query = "SELECT * FROM Sales WHERE enabled = true ORDER BY ID DESC LIMIT 1";

		try {
			openConnection();

			PreparedStatement stmt = conn.prepareStatement(query);

			ResultSet result = stmt.executeQuery();
			result.first();

			order.setId(result.getLong("id"));

			stmt.close();

			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	public String queryBuilder(Entity entity) {
		Order order = (Order) entity;
		String where = "";

		if (order.getCustomer() != null && order.getCustomer().getId() != 0L)
			where += "AND c.id = '" + order.getCustomer().getId() + "' ";

		return where;
	}
	
}

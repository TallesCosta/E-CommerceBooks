package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.History;
import br.com.talles.ecommercebooks.domain.book.Author;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorHistoryDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		Author author = (Author) entity;
		List<Entity> authorsHistory = new ArrayList();
		
		String query = "SELECT * FROM AuthorsHistory "
				+ "WHERE id_author = ?";
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setLong(1, author.getId());
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Author authorHistory = new Author();
				
				authorHistory.setId(result.getLong("id"));
				authorHistory.setEnabled(result.getBoolean("enabled"));
				authorHistory.setName(result.getString("name"));
				authorHistory.setHistory(
						new History(new Date(result.getTimestamp("date").getTime()), new User(result.getLong("id_user")), 
								result.getLong("id_book")));
				
				authorsHistory.add(authorHistory);
			}
			
			statement.close();

			return authorsHistory;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}		
	}

	@Override
	public boolean save(Entity entity) {
		Author author = (Author) entity;
		
		String sql = "INSERT INTO AuthorsHistory(enabled, name, date, id_author, id_user)"
				+ "VALUES(?, ?, ?, ?, ?)";

		IDao authorDao = new AuthorDao();
		Author a = (Author) authorDao.find(entity);
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setBoolean(1, author.isEnabled());
			statement.setString(2, a.getName());
			statement.setTimestamp(3, new Timestamp(author.getHistory().getDate().getTime()));
			statement.setLong(4, author.getId());
			statement.setLong(5, author.getHistory().getUser().getId());
			
			statement.execute();
			statement.close();

			return true;
		} catch (SQLException ex) {
			Logger.getLogger(DimensionDao.class.getName()).log(Level.SEVERE, null, ex);
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
		/*Author authorHistory = (Author) entity;
		
		String sql = "SELECT * FROM AuthorsHistory WHERE id = ?";
		
		try {
			openConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, authorHistory.getId());
            
            ResultSet result = statement.executeQuery();
			result.first();

			authorHistory.setId(result.getLong("id"));
			authorHistory.setEnabled(result.getBoolean("enabled"));
			authorHistory.setName(result.getString("name"));
			authorHistory.setHistory(
					new History(new Date(result.getTimestamp("date").getTime()), new User(result.getLong("id_user")), 
							result.getLong("id_book")));
			
			statement.close();

			return authorHistory;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}*/
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean update(Entity entity, String operation) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity findLast() {
		Author authorHistory = new Author();
		
		String query = "SELECT * FROM AuthorsHistory WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			authorHistory.setId(result.getLong("id"));
			authorHistory.setEnabled(result.getBoolean("enabled"));
			authorHistory.setName(result.getString("name"));
			authorHistory.setHistory(new History(new Date(result.getTimestamp("date").getTime()), 
					new User(result.getLong("id_user")), result.getLong("id_author")));
			
			stmt.close();

			return authorHistory;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}
	
}

package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksCategoriesDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean save(Entity entity) {
		Book book = (Book) entity;
		
		// SQL query
        String sql = "INSERT INTO BooksCategories(id_book, id_category) "
				+ "VALUES(?, ?)";
		try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
			for(int i = 0; i <= book.countCategories() - 1; i++){
				statement.setLong(1, book.getId());
				statement.setLong(2, book.getCategory(i).getId());
				
				statement.execute();
			}
            
            statement.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Entity> selectDisabled() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}

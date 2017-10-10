package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Category;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksCategoriesDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled) {
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
		Book book = (Book) entity;
        
        String sql = "DELETE FROM BooksCategories "
                + "WHERE id = ?";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
			for (int i = 0; i <= book.countCategories() - 1; i++) {
				statement.setLong(1, book.getCategory(i).getId());
				statement.execute();
			}
            statement.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BooksCategoriesDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
			closeConnection();
		}
	}

	@Override
	public Entity find(Entity entity) {
		Book book = (Book) entity;
		
        String sql = "SELECT * FROM BooksCategories "
				+ "WHERE id = ?";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, book.getId());
            
            ResultSet result = statement.executeQuery();
            
            if(result.next()){
                book.addCategory(new Category(result.getLong("id_category")));				
            }
            
            result.close();
            statement.close();
            
            return  book;
        } catch(SQLException e) {
            throw new RuntimeException(e);   
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean update(Entity entity, String operation) {
		// Delete all currentily categories assosciation with this book
		Entity entityFound = find(entity);
		if (!delete(entityFound))
			return false;
		
		// Save new categories
		return save(entity);
	}

	@Override
	public Entity findLast() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}

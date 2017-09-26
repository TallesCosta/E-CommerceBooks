package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Category;
import br.com.talles.ecommercebooks.domain.Dimension;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.SaleParameterization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		List<Entity> books = new ArrayList();
        String sql = "SELECT b.*, GROUP_CONCAT(c.name SEPARATOR '-') AS categories FROM Books b "
				+ "INNER JOIN BooksCategories bc ON b.id = bc.id_book "
				+ "INNER JOIN Categories c ON bc.id_category = c.id "
				+ "GROUP BY bc.id_book";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
			
            while (result.next()) {
				Book book = new Book();
				
				book.setId(result.getLong("books.id"));
				book.setEnabled(result.getBoolean("books.enabled"));
				book.setTitle(result.getString("books.title"));
				book.setPublicationYear(result.getInt("books.publicationYear"));
				book.setNumberOfPages(result.getInt("books.numberOfPages"));
				book.setEdition(result.getString("books.edition"));
				book.setIsbn(result.getString("books.isbn"));
				book.setEan13(result.getString("books.ean13"));
				
				List<String> categories = Arrays.asList(result.getString("categories").split("-"));
				for(String category : categories){
					book.addCategory(new Category(category));
				}
				
				/*Long id = result.getLong("booksCategories.id_book");
				String name = result.getString("categories.name");

				Category category = new Category(id, name);
				book.addCategory(category);*/
				
				books.add(book);
            }
            
            result.close();
            statement.close();
            
            return  books;
        } catch (SQLException e) {
            throw new RuntimeException (e);   
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
		Book book = (Book) entity;
        
        // Persists the Dimension
        IDao dimensionDao = new DimensionDao();
        if(!dimensionDao.save(book.getDimension())){
            return false;
        }
		
        book.setDimension((Dimension) dimensionDao.findLast());
        
		// Persists the SaleParameterization
        IDao saleParameterizationDao = new SaleParameterizationDao();
        if(!saleParameterizationDao.save(book.getSaleParameterization())){
            return false;
        }
		
		book.setSaleParameterization((SaleParameterization) saleParameterizationDao.findLast());
		
		// SQL query
        String sql = "INSERT INTO Books(enabled, title, edition, publicationYear, numberOfPages, synopsis, isbn, ean13, "
				+ "id_author, id_publishingCompany, id_dimension, id_priceGroup, id_saleParameterization) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setBoolean(1, book.isEnabled());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getEdition());
            statement.setInt(4, book.getPublicationYear());
            statement.setInt(5, book.getNumberOfPages());
            statement.setString(6, book.getSynopsis());
            statement.setString(7, book.getIsbn());
            statement.setString(8, book.getEan13());
			
            statement.setLong(9, book.getAuthor().getId());
            statement.setLong(10, book.getPublishingCompany().getId());
            statement.setLong(11, book.getDimension().getId());
			statement.setLong(12, book.getPriceGroup().getId());
			statement.setLong(13, book.getSaleParameterization().getId());
			
            statement.execute();
			statement.close();
			            
            // Find the last book register to get its id
			Book lastBook = (Book) findLast();
			book.setId(lastBook.getId());
			
			// Insert in books-categories association table
			IDao booksCategoriesDao = new BooksCategoriesDao();
			return booksCategoriesDao.save(book);					// If insert association is true, insert book is true.
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean delete(Entity entity) {
		return false;
	}

	@Override
	public Entity find(Entity entity) {
		return null;
	}

	@Override
	public boolean update(Entity entity) {
		return false;
	}	

	@Override
	public Entity findLast() {
		Book book = new Book();
		
		String query = "SELECT * FROM Books ORDER BY ID DESC LIMIT 1";
		
		try {
			openConnection();
			
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result;
			
			result = stmt.executeQuery();
			result.first();

			book.setId(result.getLong("id"));
			
			stmt.close();

			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}
	
}

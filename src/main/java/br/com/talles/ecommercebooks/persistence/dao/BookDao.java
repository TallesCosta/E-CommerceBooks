package br.com.talles.ecommercebooks.persistence.dao;

import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Dimension;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.SaleParameterization;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao extends AbstractDao {

	@Override
	public List<Entity> select() {
		List<Entity> books = new ArrayList();
        String sql = "SELECT * FROM Books";
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                Book book = new Book();
                
                book.setId(result.getLong("id"));
                book.setEnabled(result.getBoolean("enabled"));
                book.setTitle(result.getString("title"));
                book.setPublicationYear(result.getInt("publicationYear"));
                book.setNumberOfPages(result.getInt("numberOfPages"));
                book.setEdition(result.getString("edition"));
                book.setIsbn(result.getString("isbn"));
                book.setEan13(result.getString("ean13"));
                
                books.add(book);
            }
            
            result.close();
            statement.close();
            
            return  books;
        }catch(SQLException e){
            throw new RuntimeException(e);   
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
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
		return null;
	}
	
}

package br.com.talles.ecommercebooks.persistence.dao.book;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Author;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.book.Category;
import br.com.talles.ecommercebooks.domain.book.ChangeStatus;
import br.com.talles.ecommercebooks.domain.book.Dimension;
import br.com.talles.ecommercebooks.domain.book.PriceGroup;
import br.com.talles.ecommercebooks.domain.book.PublishingCompany;
import br.com.talles.ecommercebooks.domain.book.SaleParameterization;
import br.com.talles.ecommercebooks.domain.book.StatusCategory;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.Diagnostic;

public class BookHistoryDao extends AbstractDao {
	
	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> booksHistory = new ArrayList();
		String where = queryBuilder(entity);
		
        String sql = "SELECT b.*, d.*, sp.*, a.*, pc.*, pg.*, "
				+ "GROUP_CONCAT(c.name SEPARATOR '-') AS categories "
				+ "FROM Books b "
				+ "INNER JOIN Dimensions d on b.id_dimension = d.id "
				+ "INNER JOIN SaleParameterizations sp on b.id_saleParameterization = sp.id "
				+ "INNER JOIN Authors a on b.id_author = a.id "
				+ "INNER JOIN PublishingCompanies pc on b.id_publishingCompany = pc.id "
				+ "INNER JOIN PriceGroups pg on b.id_priceGroup = pg.id "
				+ "INNER JOIN BooksCategories bc ON b.id = bc.id_book "
				+ "INNER JOIN Categories c ON bc.id_category = c.id "
				+ "WHERE b.enabled = ? " + where
				+ "GROUP BY bc.id_book";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            
			
            while (result.next()) {
				Book book = new Book();
				
				book.setId(result.getLong("books.id"));
				book.setEnabled(result.getBoolean("books.enabled"));
				book.setTitle(result.getString("books.title"));
				book.setSynopsis(result.getString("books.synopsis"));
				book.setPublicationYear(result.getInt("books.publicationYear"));
				book.setNumberOfPages(result.getInt("books.numberOfPages"));
				book.setEdition(result.getString("books.edition"));
				book.setIsbn(result.getString("books.isbn"));
				book.setEan13(result.getString("books.ean13"));
				
				// Dimension
				book.setDimension(new Dimension(result.getDouble("dimensions.height"), 
						result.getDouble("dimensions.widht"), result.getDouble("dimensions.weight"), 
						result.getDouble("dimensions.depth"), result.getLong("dimensions.id")));
				// Sale Parameterization
				book.setSaleParameterization(new SaleParameterization(
						result.getInt("saleParameterizations.minSaleLimit"), 
						result.getInt("saleParameterizations.periodicity"), 
						result.getLong("saleParameterizations.id")));
				// Author
				book.setAuthor(new Author(result.getString("authors.name"), result.getLong("authors.id")));
				// PublishingCompany
				book.setPublishingCompany(new PublishingCompany(result.getString("publishingCompanies.name"), 
						result.getLong("publishingCompanies.id")));
				// PriceGroup
				book.setPriceGroup(new PriceGroup(result.getDouble("priceGroups.markup"), 
						result.getLong("priceGroups.id")));
				// Categories
				List<String> categories = Arrays.asList(result.getString("categories").split("-"));
				for(String category : categories){
					book.addCategory(new Category(category));
				}
				
				booksHistory.add(book);
            }
            
            result.close();
            statement.close();
            
            return  booksHistory;
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
        /*IDao dimensionDao = new DimensionDao();
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
		
		// Persists the ChangeStatus default
		IDao changeStatusDao = new ChangeStatusDao();
		if(!changeStatusDao.save(book.getChangeStatus())){
            return false;
        }		
		book.setChangeStatus((ChangeStatus) changeStatusDao.findLast());*/
		
		// Persists the Author
		IDao authorHistoryDao = new AuthorHistoryDao();
		if(!authorHistoryDao.save(book.getAuthor()))
            return false;
		book.setAuthor((Author) authorHistoryDao.findLast());
		
		return true;
		
		// SQL query
        /*String sql = "INSERT INTO BooksHistory (enabled, title, edition, publicationYear, numberOfPages, synopsis, "
				+ "isbn, ean13, id_author, id_publishingCompany, id_dimension, id_priceGroup, "
				+ "id_saleParameterization, id_changeStatus) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
			statement.setLong(14, book.getChangeStatus().getId());
			
            statement.execute();
			statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
			closeConnection();
		}
		
		// Find the last book register to get its id
		Book lastBook = (Book) findLast();
		book.setId(lastBook.getId());

		// Insert in books-categories association table
		IDao booksCategoriesDao = new BooksCategoriesDao();
		return booksCategoriesDao.save(book);			// If insert association is true, insert book is true.*/
	}

	@Override
	public boolean delete(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Entity find(Entity entity) {
		return new Book("title", "edition", 0, 0, "sysnopsis", "isbn", "ean13", new Dimension(), new PriceGroup(1), new PublishingCompany(2), new SaleParameterization(), new ChangeStatus(new StatusCategory(0), 0), new Author(2), Arrays.asList(new Category(1), new Category(2)));
	}
	
	@Override
	public boolean update(Entity entity, String operation) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}	

	@Override
	public Entity findLast() {
		Book book = new Book();
		
		String query = "SELECT * FROM BooksHistory WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
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
	
	public String queryBuilder(Entity entity) {
		Book book = (Book) entity;
		String where = "";
		
		if (book.getTitle() != null && !book.getTitle().equals(""))
			where += "AND b.title = '" + book.getTitle() + "' ";
		if (book.getEdition() != null && !book.getEdition().equals(""))
			where += "AND b.edition = '" + book.getEdition() + "' ";
		if (book.getPublicationYear() != 0)
			where += "AND b.publicationYear = " + book.getPublicationYear() + " ";
		if (book.getNumberOfPages() != 0)
			where += "AND b.numberOfPages = " + book.getNumberOfPages() + " ";
		if (book.getSynopsis() != null && !book.getSynopsis().equals(""))
			where += "AND b.synopsis = " + book.getSynopsis() + " ";
		if (book.getIsbn() != null && !book.getIsbn().equals(""))
			where += "AND b.isbn = " + book.getIsbn() + " ";
		if (book.getEan13() != null && !book.getEan13().equals(""))
			where += "AND b.ean13 = " + book.getEan13() + " ";
		// Dimension
		if (book.getDimension().getHeight() != 0.0)
			where += "AND d.height = " + book.getDimension().getHeight() + " ";
		if (book.getDimension().getWidht() != 0.0)
			where += "AND d.widht = " + book.getDimension().getWidht() + " ";
		if (book.getDimension().getWeight() != 0.0)
			where += "AND d.weight = " + book.getDimension().getWeight() + " ";
		if (book.getDimension().getDepth() != 0.0)
			where += "AND d.depth = " + book.getDimension().getDepth() + " ";
		// Sale Parameterization
		if (book.getSaleParameterization().getMinSaleLimit() != 0)
			where += "AND sp.minSaleLimit = " + book.getSaleParameterization().getMinSaleLimit() + " ";
		if (book.getSaleParameterization().getPeriodicity() != 0)
			where += "AND sp.periodicity = " + book.getSaleParameterization().getPeriodicity() + " ";
		// Author
		if (book.getAuthor().getId()!= 0L)
			where += "AND a.id = " + book.getAuthor().getId() + " ";
		// PublishingCompany
		if (book.getPublishingCompany().getId()!= 0L)
			where += "AND pc.id = " + book.getPublishingCompany().getId() + " ";
		// PriceGroup
		if (book.getPriceGroup().getId()!= 0L)
			where += "AND pg.id = " + book.getPriceGroup().getId() + " ";
		// Cateogories
		for (Category category : book.getCategories()) {
			if (category.getId()!= 0L)
				where += "AND c.id = " + category.getId() + " ";
		}
		
		return where;
	}
	
}

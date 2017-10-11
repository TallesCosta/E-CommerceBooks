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

public class BookDao extends AbstractDao {
	
	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> books = new ArrayList();
		String where = queryBuilder(entity);
		
        String sql = "SELECT b.*, GROUP_CONCAT(c.name SEPARATOR '-') AS categories "
				+ "FROM Books b "
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
				book.setPublicationYear(result.getInt("books.publicationYear"));
				book.setNumberOfPages(result.getInt("books.numberOfPages"));
				book.setEdition(result.getString("books.edition"));
				book.setIsbn(result.getString("books.isbn"));
				book.setEan13(result.getString("books.ean13"));
				
				List<String> categories = Arrays.asList(result.getString("categories").split("-"));
				for(String category : categories){
					book.addCategory(new Category(category));
				}
				
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
		
		// Persists the ChangeStatus default
		IDao changeStatusDao = new ChangeStatusDao();
		if(!changeStatusDao.save(book.getChangeStatus())){
            return false;
        }		
		book.setChangeStatus((ChangeStatus) changeStatusDao.findLast());
		
		// SQL query
        String sql = "INSERT INTO Books (enabled, title, edition, publicationYear, numberOfPages, synopsis, "
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
		return booksCategoriesDao.save(book);			// If insert association is true, insert book is true.
	}

	@Override
	public boolean delete(Entity entity) {
		return false;
	}

	@Override
	public Entity find(Entity entity) {
		Book book = (Book) entity;
		
        String sql = "SELECT b.*, a.*, pc.*, d.*, pg.*, sp.*, cs.*, "
				+ "GROUP_CONCAT(c.id SEPARATOR '-') AS catsId, "
				+ "GROUP_CONCAT(c.name SEPARATOR '-') AS catsName "
				+ "FROM Books b "
                + "INNER JOIN Authors a on b.id_author = a.id "
				+ "INNER JOIN PublishingCompanies pc on b.id_publishingCompany = pc.id "
				+ "INNER JOIN Dimensions d on b.id_dimension = d.id "
				+ "INNER JOIN PriceGroups pg on b.id_priceGroup = pg.id "
				+ "INNER JOIN SaleParameterizations sp on b.id_saleParameterization = sp.id "
				+ "INNER JOIN ChangeStatus cs on b.id_changeStatus = cs.id "
				+ "INNER JOIN BooksCategories bc on b.id = bc.id_book "
				+ "INNER JOIN Categories c on bc.id_category = c.id "
				+ "WHERE b.id = ? "
				+ "GROUP BY bc.id_book";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, book.getId());
            
            ResultSet result = statement.executeQuery();
            
            if(result.first()){
                book.setId(result.getLong("books.id"));
				book.setEnabled(result.getBoolean("books.enabled"));
				book.setTitle(result.getString("books.title"));
				book.setEdition(result.getString("books.edition"));
				book.setPublicationYear(result.getInt("books.publicationYear"));
				book.setNumberOfPages(result.getInt("books.numberOfPages"));
				book.setSynopsis(result.getString("books.synopsis"));
				book.setIsbn(result.getString("books.isbn"));
				book.setEan13(result.getString("books.ean13"));
				
				book.setAuthor(new Author(result.getString("authors.name"), result.getLong("authors.id")));
				book.setPublishingCompany(new PublishingCompany(result.getString("publishingCompanies.name"), 
						result.getLong("publishingCompanies.id")));
				book.setDimension(new Dimension(result.getDouble("dimensions.height"), 
						result.getDouble("dimensions.widht"), result.getDouble("dimensions.weight"), 
						result.getDouble("dimensions.depth"), result.getLong("dimensions.id")));
				book.setPriceGroup(new PriceGroup(result.getDouble("priceGroups.markup"), 
						result.getLong("priceGroups.id")));
				book.setSaleParameterization(new SaleParameterization(
						result.getInt("saleParameterizations.minSaleLimit"), 
						result.getInt("saleParameterizations.periodicity"), 
						result.getLong("saleParameterizations.id")));
				book.setChangeStatus(new ChangeStatus(result.getString("changeStatus.justification"), 
						new StatusCategory(result.getLong("changeStatus.id_statusCategory")), 
						result.getLong("changeStatus.id")));
				
				List<Category> categories = new ArrayList<>();
				String[] catsIds = result.getString("catsId").split("-");
				String[] catsNames = result.getString("catsName").split("-");
				
				for (int i = 0; i < catsIds.length; i++) {
					categories.add(new Category(catsNames[i], new Long(catsIds[i])));
				}
				
				book.setCategories(categories);
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
		Book book = (Book) entity;
        
		if (operation.equals("UPDATE")) {
			// Updates the Dimension
			DimensionDao dimensionDao = new DimensionDao();
			if(!dimensionDao.update(book.getDimension(), operation)){
				return false;
			}

			// Updates the SaleParameterization
			SaleParameterizationDao saleParameterizationDao = new SaleParameterizationDao();
			if(!saleParameterizationDao.update(book.getSaleParameterization(), operation)){
				return false;
			}

			// Updates in books-categories association table
			IDao booksCategoriesDao = new BooksCategoriesDao();
			if(!booksCategoriesDao.update(book, operation))
				return false;
		}
		
		// Updates the ChangeStatus
		ChangeStatusDao changeStatusDao = new ChangeStatusDao();
		if (!changeStatusDao.update(book.getChangeStatus(), "UPDATE"))
			return false;
		
        String sql = "UPDATE Books "
                + "SET enabled = ?, title = ?, edition = ?, publicationYear = ?, numberOfPages = ?, synopsis = ?, "
				+ "isbn = ?, ean13 = ?, id_author = ?, id_publishingCompany = ?, id_dimension = ?, "
				+ "id_priceGroup = ?, id_saleParameterization = ?, id_changeStatus = ? "
                + "WHERE id = ?";
        
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
            statement.setLong(15, book.getId());
            
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
		Book book = new Book();
		
		String query = "SELECT * FROM Books WHERE enabled = true ORDER BY ID DESC LIMIT 1";
		
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
	
	public boolean hasThisIsbn(Entity entity){
		Book book = (Book) entity;
        String sql = "SELECT * FROM Books WHERE isbn = ?";
		boolean bookFound = false;
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, book.getIsbn());
            
            ResultSet result = statement.executeQuery();
            
            if(result.first()){
				bookFound = true;
                if (book.getId() != 0L && book.getId() == result.getLong("id"))
					bookFound = false;
            }
            
            result.close();
            statement.close();
            
            return bookFound;
        }catch(SQLException e){
            throw new RuntimeException(e);   
        } finally {
			closeConnection();
		}
	}
	
	public boolean hasThisEan13(Entity entity){
		Book book = (Book) entity;
		String sql = "SELECT * FROM Books WHERE ean13 = ?";
		boolean bookFound = false;
        
        try{
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, book.getEan13());
            
            ResultSet result = statement.executeQuery();
            
            if(result.first()){
				bookFound = true;
                if (book.getId() != 0L && book.getId() == result.getLong("id"))
					bookFound = false;
            }
            
            result.close();
            statement.close();
            
            return bookFound;
        }catch(SQLException e){
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
		
		return where;
	}
	
}

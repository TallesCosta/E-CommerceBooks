
package br.com.talles.ecommercebooks.persistence.dao.sale;

import java.util.ArrayList;
import java.util.List;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Author;
import br.com.talles.ecommercebooks.domain.book.Category;
import br.com.talles.ecommercebooks.domain.book.Dimension;
import br.com.talles.ecommercebooks.domain.book.PriceGroup;
import br.com.talles.ecommercebooks.domain.book.PublishingCompany;
import br.com.talles.ecommercebooks.domain.book.SaleParameterization;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import br.com.talles.ecommercebooks.persistence.dao.AbstractDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class StockDao extends AbstractDao {

	@Override
	public List<Entity> select(boolean enabled, Entity entity) {
		List<Entity> stocks = new ArrayList<>();
		
        String sql = "SELECT s.*, b.*, d.*, sp.*, a.*, pc.*, pg.*, "
				+ "GROUP_CONCAT(c.name SEPARATOR '-') AS categories "
				+ "FROM Stocks s "
				+ "INNER JOIN Books b on s.id_book = b.id "
				+ "INNER JOIN Dimensions d on b.id_dimension = d.id "
				+ "INNER JOIN SaleParameterizations sp on b.id_saleParameterization = sp.id "
				+ "INNER JOIN Authors a on b.id_author = a.id "
				+ "INNER JOIN PublishingCompanies pc on b.id_publishingCompany = pc.id "
				+ "INNER JOIN PriceGroups pg on b.id_priceGroup = pg.id "
				+ "INNER JOIN BooksCategories bc ON b.id = bc.id_book "
				+ "INNER JOIN Categories c ON bc.id_category = c.id "
				+ "WHERE b.enabled = ? "
				+ "GROUP BY bc.id_book";
        
        try {
			openConnection();
			
            PreparedStatement statement = conn.prepareStatement(sql);
			statement.setBoolean(1, enabled);
			
            ResultSet result = statement.executeQuery();
            
			
            while (result.next()) {
				Stock stock = new Stock();
				
				stock.setId(result.getLong("stocks.id"));
				stock.setEnabled(result.getBoolean("stocks.enabled"));
				stock.setAveragePrice(result.getDouble("stocks.averagePrice"));
				stock.setMinimumPrice(result.getDouble("stocks.minimumPrice"));
				stock.setSalePrice(result.getInt("stocks.salePrice"));
				stock.setRealAmount(result.getInt("stocks.realAmount"));
				stock.setVirtualAmount(result.getInt("stocks.virtualAmount"));
				
				stock.getBook().setId(result.getLong("books.id"));
				stock.getBook().setEnabled(result.getBoolean("books.enabled"));
				stock.getBook().setTitle(result.getString("books.title"));
				stock.getBook().setSynopsis(result.getString("books.synopsis"));
				stock.getBook().setPublicationYear(result.getInt("books.publicationYear"));
				stock.getBook().setNumberOfPages(result.getInt("books.numberOfPages"));
				stock.getBook().setEdition(result.getString("books.edition"));
				stock.getBook().setIsbn(result.getString("books.isbn"));
				stock.getBook().setEan13(result.getString("books.ean13"));
				
				// Dimension
				stock.getBook().setDimension(new Dimension(result.getDouble("dimensions.height"), 
						result.getDouble("dimensions.widht"), result.getDouble("dimensions.weight"), 
						result.getDouble("dimensions.depth"), result.getLong("dimensions.id")));
				// Sale Parameterization
				stock.getBook().setSaleParameterization(new SaleParameterization(
						result.getInt("saleParameterizations.minSaleLimit"), 
						result.getInt("saleParameterizations.periodicity"), 
						result.getLong("saleParameterizations.id")));
				// Author
				stock.getBook().setAuthor(new Author(result.getString("authors.name"), result.getLong("authors.id")));
				// PublishingCompany
				stock.getBook().setPublishingCompany(new PublishingCompany(result.getString("publishingCompanies.name"), 
						result.getLong("publishingCompanies.id")));
				// PriceGroup
				stock.getBook().setPriceGroup(new PriceGroup(result.getDouble("priceGroups.markup"), 
						result.getLong("priceGroups.id")));
				// Categories
				List<String> categories = Arrays.asList(result.getString("categories").split("-"));
				for(String category : categories){
					stock.getBook().addCategory(new Category(category));
				}
				
				stocks.add(stock);
            }
            
            result.close();
            statement.close();
            
            return stocks;
        } catch (SQLException e) {
            throw new RuntimeException (e);   
        } finally {
			closeConnection();
		}
	}

	@Override
	public boolean save(Entity entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

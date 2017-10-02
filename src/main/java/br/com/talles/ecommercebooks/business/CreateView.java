package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.book.ActivationCategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.AuthorDao;
import br.com.talles.ecommercebooks.persistence.dao.book.CategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.DeactivationCategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.PriceGroupDao;
import br.com.talles.ecommercebooks.persistence.dao.book.PublishingCompanyDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CityDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CountryDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.StateDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CardCompanyDao;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CreateView implements IStrategy {
	
	private static final String LIST = "LIST";
    private static final String LIST_DISABLE = "LIST-DISABLE";
	private static final String CREATE = "CREATE";
	
	protected String view;
	protected Map<String, List<IDao>> persistence;
	
	public CreateView(String operation) {
		this.view = operation;
		
		// Context = Entity + Operation		
		String book = Book.class.getSimpleName();
		String customer = Customer.class.getSimpleName();
		
		// Datas needed to create view Book
		List<IDao> createBook = new ArrayList<>();
		createBook.add(new AuthorDao());
		createBook.add(new CategoryDao());
		createBook.add(new PriceGroupDao());
		createBook.add(new PublishingCompanyDao());
		
		// Datas needed to list view Book
		List<IDao> listBook = new ArrayList<>();
		listBook.add(new AuthorDao());
		listBook.add(new CategoryDao());
		listBook.add(new PriceGroupDao());
		listBook.add(new PublishingCompanyDao());
		listBook.add(new DeactivationCategoryDao());
				
		// Datas needed to list-disable view Book
		List<IDao> listDisableBook = new ArrayList<>();
		listDisableBook.add(new AuthorDao());
		listDisableBook.add(new CategoryDao());
		listDisableBook.add(new PriceGroupDao());
		listDisableBook.add(new PublishingCompanyDao());
		listDisableBook.add(new ActivationCategoryDao());
				
		// Datas needed to create Customer view
		List<IDao> createCustomer = new ArrayList<>();
		createCustomer.add(new CityDao());
		createCustomer.add(new StateDao());
		createCustomer.add(new CountryDao());
		createCustomer.add(new CardCompanyDao());
		
		persistence = new HashMap();
		persistence.put(book + CREATE, createBook);
		persistence.put(book + LIST, listBook);
		persistence.put(book + LIST_DISABLE, listDisableBook);
		persistence.put(customer + CREATE, createCustomer);
	}
	
	@Override
	public Result process(Entity entity, Result result) {
		List<IDao> daos = persistence.get(entity.getClass().getSimpleName() + view);
		
		for (IDao dao : daos) {
			result.addEntities(dao.select(true));
		}
		
		return result;
	}
	
}

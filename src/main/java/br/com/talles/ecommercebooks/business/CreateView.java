package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.customer.ChargeAddress;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.book.StatusCategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.AuthorDao;
import br.com.talles.ecommercebooks.persistence.dao.book.CategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.book.PriceGroupDao;
import br.com.talles.ecommercebooks.persistence.dao.book.PublishingCompanyDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CreateView implements IStrategy {
	
	private static final String LIST = "LIST";
    private static final String LIST_DISABLE = "LIST-DISABLE";
	private static final String CREATE = "CREATE";
	
	protected Map<String, List<IDao>> persistence;
	
	public CreateView() {		
		// Context = Entity + Operation		
		String book = Book.class.getSimpleName();
		String customer = Customer.class.getSimpleName();
		String chargeAddress = ChargeAddress.class.getSimpleName();
		String deliveryAddress = DeliveryAddress.class.getSimpleName();
		String creditCard = CreditCard.class.getSimpleName();

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
		listBook.add(new StatusCategoryDao());
				
		// Datas needed to list-disable view Book
		List<IDao> listDisableBook = new ArrayList<>();
		listDisableBook.add(new AuthorDao());
		listDisableBook.add(new CategoryDao());
		listDisableBook.add(new PriceGroupDao());
		listDisableBook.add(new PublishingCompanyDao());
		listDisableBook.add(new StatusCategoryDao());
				
		// Datas needed to create Customer view
		List<IDao> createCustomer = new ArrayList<>();
		createCustomer.add(new StateDao());
		createCustomer.add(new CountryDao());
		createCustomer.add(new CardCompanyDao());
		
		// Datas needed to list Customer view
		List<IDao> listCustomer = new ArrayList<>();
		listCustomer.add(new StateDao());
		listCustomer.add(new CountryDao());
		
		// Datas needed to list-disable Customer view
		List<IDao> listDisableCustomer = new ArrayList<>();
		listDisableCustomer.add(new StateDao());
		listDisableCustomer.add(new CountryDao());

		// Datas needed to create view ChargeAddress
		List<IDao> createChargeAddress = new ArrayList<>();
		createChargeAddress.add(new StateDao());
		createChargeAddress.add(new CountryDao());

		// Datas needed to create view DeliveryAddress
		List<IDao> createDeliveryAddress = new ArrayList<>();
		createDeliveryAddress.add(new StateDao());
		createDeliveryAddress.add(new CountryDao());

		// Datas needed to create view CreditCard
		List<IDao> createCreditCard = new ArrayList<>();
		createCreditCard.add(new CardCompanyDao());

		persistence = new HashMap();
		persistence.put(book + CREATE, createBook);
		persistence.put(book + LIST, listBook);
		persistence.put(book + LIST_DISABLE, listDisableBook);
		persistence.put(customer + CREATE, createCustomer);
		persistence.put(customer + LIST, listCustomer);
		persistence.put(customer + LIST_DISABLE, listDisableCustomer);
		persistence.put(chargeAddress + CREATE, createChargeAddress);
		persistence.put(deliveryAddress + CREATE, createDeliveryAddress);
		persistence.put(creditCard + CREATE, createCreditCard);
	}
	
	@Override
	public Result process(Entity entity, Result result) {
		List<IDao> daos = persistence.get(entity.getClass().getSimpleName() + result.getTransaction().getOperation());
		
		for (IDao dao : daos) {
			result.addEntities(dao.select(true, entity));
		}
		
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o || getClass() == o.getClass()) return true;
		return false;
	}

}

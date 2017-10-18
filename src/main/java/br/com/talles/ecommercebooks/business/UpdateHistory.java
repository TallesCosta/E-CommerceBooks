package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.book.HistoryBookDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.HistoryCustomerDao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateHistory implements IStrategy {

	protected Map<String, IDao> persistence;
	protected Map<String, IDao> persistenceHistory;

	public UpdateHistory() {
		String book = Book.class.getSimpleName();
		String customer = Customer.class.getSimpleName();

		persistence = new HashMap();
		persistence.put(book, new BookDao());
		persistence.put(customer, new CustomerDao());
		
		persistenceHistory = new HashMap();
		persistenceHistory.put(book, new HistoryBookDao());
		persistenceHistory.put(customer, new HistoryCustomerDao());
	}

	@Override
	public Result process(Entity entity, Result result) {
		try {
			Entity entityHistory = entity.getClass().newInstance();
			entityHistory.setId(entity.getId());

			IDao dao = persistence.get(entity.getClass().getSimpleName());
			dao.find(entityHistory);
			entityHistory.setHistory(entity.getHistory());
			
			dao = persistenceHistory.get(entity.getClass().getSimpleName());
			dao.save(entityHistory);
		} catch (InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(UpdateHistory.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return result;
	}

}

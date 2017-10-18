package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.book.HistoryBookDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.HistoryCustomerDao;

import java.util.HashMap;
import java.util.Map;

public class SaveHistory implements IStrategy {
	
	protected Map<String, IDao> persistence;
	
	public SaveHistory() {
		String book = Book.class.getSimpleName();
		String customer = Customer.class.getSimpleName();
				
		persistence = new HashMap();
		persistence.put(book, new HistoryBookDao());
		persistence.put(customer, new HistoryCustomerDao());
	}
	
	@Override
	public Result process(Entity entity, Result result) {
		IDao dao = persistence.get(entity.getClass().getSimpleName());
		dao.save(entity);
		
		return result;
	}	
	
}

package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;

public class FindBook implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao dao = new BookDao();
		entity = dao.find(entity);
		
		return result;
	}
	
}

package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;

public class IsbnUnique implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Book book = (Book) entity;
		
		BookDao bookDao = new BookDao();
		
		if (bookDao.hasThisIsbn(entity)) {
			result.addMsg("ISBN " + book.getIsbn() + " já cadastrado!\n");
		}
		
		return result;
	}
	
}

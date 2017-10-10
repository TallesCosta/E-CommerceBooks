package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;

public class Ean13Unique implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Book book = (Book) entity;
		
		BookDao bookDao = new BookDao();
		if (bookDao.hasThisEan13(entity)) {
			result.addMsg("Código de Barras " + book.getEan13() + " já cadastrado!\n");
		}
		
		return result;
	}
	
}

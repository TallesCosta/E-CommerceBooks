package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.BookDao;

public class Ean13Unique implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Book book = (Book) entity;
		
		BookDao bookDao = new BookDao();
		Book foundBook = (Book) bookDao.findEan13(entity);
		
		if (foundBook.getId() != 0L) {
			result.addMsg("Código de Barras " + foundBook.getEan13() + " já cadastrado para o livro " + foundBook.getTitle() + "!");
		}
		
		return result;
	}
	
}

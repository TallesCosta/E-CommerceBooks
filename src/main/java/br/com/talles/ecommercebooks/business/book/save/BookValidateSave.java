package br.com.talles.ecommercebooks.business.book.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.book.BookNotBlank;
import br.com.talles.ecommercebooks.business.book.Ean13Unique;
import br.com.talles.ecommercebooks.business.book.IsbnUnique;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;

public class BookValidateSave implements IStrategy {
	
	@Override
	public Result process(Entity entity, Result result) {
		Book book = (Book) entity;
        
		IStrategy bookNotBlank = new BookNotBlank();
		IStrategy isbnUnique = new IsbnUnique();
		IStrategy ean13Unique = new Ean13Unique();
						
		result = bookNotBlank.process(book, result);
		result = isbnUnique.process(book, result);
		result = ean13Unique.process(book, result);
		
		return result;
	}
	
}

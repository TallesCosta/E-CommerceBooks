package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;

public class ModifyStatus implements IStrategy {
	
	@Override
	public Result process(Entity entity, Result result) {
		IStrategy findBook = new FindBook();
		
		Entity bookTemp = new Book(entity.getId());
		findBook.process(bookTemp, result);
		
		fillBookWithOutChangeStatus((Book) entity, (Book) bookTemp);
		
		return result;
	}
	
	public void fillBookWithOutChangeStatus(Book entity, Book bookTemp){
		entity.setTitle(bookTemp.getTitle());
		entity.setEdition(bookTemp.getEdition());
		entity.setPublicationYear(bookTemp.getPublicationYear());
		entity.setNumberOfPages(bookTemp.getNumberOfPages());
		entity.setSynopsis(bookTemp.getSynopsis());
		entity.setIsbn(bookTemp.getIsbn());
		entity.setEan13(bookTemp.getEan13());
		entity.setAuthor(bookTemp.getAuthor());
		entity.setPublishingCompany(bookTemp.getPublishingCompany());
		entity.setDimension(bookTemp.getDimension());
		entity.setPriceGroup(bookTemp.getPriceGroup());
		entity.setSaleParameterization(bookTemp.getSaleParameterization());
		entity.setCategories(bookTemp.getCategories());
		
		if (bookTemp.getChangeStatus().getId() != 0L)
			entity.getChangeStatus().setId(bookTemp.getChangeStatus().getId());
	}
	
}

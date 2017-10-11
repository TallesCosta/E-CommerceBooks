package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.Entity;

public class BookNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Book book = (Book) entity;
        
		IStrategy dimensionNotBlank = new DimensionNotBlank();
		IStrategy saleParameterizationNotBlank = new SaleParameterizationNotBlank();
		IStrategy authorNotBlank = new AuthorNotBlank();
		IStrategy publishingCompanyNotBlank = new PublishingCompanyNotBlank();
		IStrategy priceGroupNotBlank = new PriceGroupNotBlank();
		IStrategy categoryNotBlank = new CategoryNotBlank();
		
        if (book.getTitle ()== null || book.getTitle().trim().isEmpty()) {
            result.addMsg("Título é um campo obrigatório!\n");
		} if (book.getSynopsis ()== null || book.getSynopsis().trim().isEmpty()) {
            result.addMsg("Sinopse é um campo obrigatório!\n");
		} if (book.getEdition ()== null || book.getEdition().trim().isEmpty()) {
            result.addMsg("Edição é um campo obrigatório!\n");
		} if (book.getPublicationYear() == 0) {
            result.addMsg("Ano de Publicação é um campo obrigatório!\n");
		} if (book.getPublicationYear() > 2017) {
            result.addMsg("Ano de Publicação deve ser menor que 2017!\n");
		} if (book.getNumberOfPages() == 0) {
            result.addMsg("Número de Páginas é um campo obrigatório!\n");
		} if (book.getNumberOfPages() < 1) {
            result.addMsg("Número de Páginas deve ser, no mínimo, igual a 1!\n");
		} if (book.getIsbn ()== null || book.getIsbn().trim().isEmpty()) {
            result.addMsg("ISBN é um campo obrigatório!\n");
		} if (book.getEan13 ()== null || book.getEan13().trim().isEmpty()) {
            result.addMsg("Código de Barras é um campo obrigatório!\n");
		}
				
		result = dimensionNotBlank.process(book.getDimension(), result);
		result = saleParameterizationNotBlank.process(book.getSaleParameterization(), result);
		result = authorNotBlank.process(book.getAuthor(), result);
		result = publishingCompanyNotBlank.process(book.getPublishingCompany(), result);
		result = priceGroupNotBlank.process(book.getPriceGroup(), result);
		result = categoryNotBlank.process(book.getCategory(0), result);
		
		return result;
	}
	
}

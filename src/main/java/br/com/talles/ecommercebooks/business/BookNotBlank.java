package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Book;
import br.com.talles.ecommercebooks.domain.Entity;

public class BookNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Book book = (Book) entity;
        
        if (book.getTitle ()== null || book.getTitle().trim().isEmpty()) {
            result.addMsg("Título é um campo obrigatório!\n");
		} if (book.getEdition ()== null || book.getEdition().trim().isEmpty()) {
            result.addMsg("Edição é um campo obrigatório!\n");
		} if (book.getPublicationYear() == 0) {
            result.addMsg("Ano de Publicação é um campo obrigatório!\n");
		} if (book.getNumberOfPages() == 0) {
            result.addMsg("Número de Páginas é um campo obrigatório!\n");
		} if (book.getSynopsis ()== null || book.getSynopsis().trim().isEmpty()) {
            result.addMsg("Sinopse é um campo obrigatório!\n");
		} if (book.getIsbn ()== null || book.getIsbn().trim().isEmpty()) {
            result.addMsg("ISBN é um campo obrigatório!\n");
		} if (book.getEan13 ()== null || book.getEan13().trim().isEmpty()) {
            result.addMsg("Código de Barras é um campo obrigatório!\n");
		}
		
		return result;
	}
	
}

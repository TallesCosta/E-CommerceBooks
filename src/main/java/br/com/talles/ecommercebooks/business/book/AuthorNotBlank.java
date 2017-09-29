package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Author;
import br.com.talles.ecommercebooks.domain.Entity;

public class AuthorNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Author author = (Author) entity;
		
		if (author.getId() == 0L) {
            result.addMsg("A escolha de um Autor é obrigatória!\n");
		}
		
		return result;
	}
	
}

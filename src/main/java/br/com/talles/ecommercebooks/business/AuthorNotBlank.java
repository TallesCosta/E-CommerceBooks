package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Author;
import br.com.talles.ecommercebooks.domain.Entity;

public class AuthorNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Author author = (Author) new Author();
		
		if (author.getId() == 0L) {
            result.addMsg("A escolha de um Autor é obrigatória!\n");
		}
		
		return result;
	}
	
}

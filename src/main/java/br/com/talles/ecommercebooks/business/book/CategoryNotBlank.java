package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.book.Category;
import br.com.talles.ecommercebooks.domain.Entity;

public class CategoryNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		Category category = (Category) entity;
		
		if (category.getId() == 0L) {
            result.addMsg("A escolha de ao menos uma Categoria é obrigatória!\n");
		}
		
		return result;
	}
	
}

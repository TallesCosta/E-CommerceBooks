package br.com.talles.ecommercebooks.business.book;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.PublishingCompany;

public class PublishingCompanyNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		PublishingCompany publishingCompany = (PublishingCompany) entity;
		
		if (publishingCompany.getId() == 0L) {
            result.addMsg("A escolha de uma Editora é obrigatória!\n");
		}
		
		return result;
	}
	
}

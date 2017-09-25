package br.com.talles.ecommercebooks.business.view;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.AuthorDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

public class SelectAuthor implements IStrategy {
	
	@Override
	public Result process(Entity entity, Result result) {
		IDao authorDao = new AuthorDao();
		
		result.addEntities(authorDao.select());
		
		return result;
	}
	
}

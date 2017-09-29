package br.com.talles.ecommercebooks.business.book.view;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.book.ActivationCategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

public class SelectActivationCategory implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao activationCategoryDao = new ActivationCategoryDao();
		
		result.addEntities(activationCategoryDao.select(true));
		
		return result;
	}
	
}

package br.com.talles.ecommercebooks.business.book.view;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.book.CategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

public class SelectCategory implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao categoryDao = new CategoryDao();
		
		result.addEntities(categoryDao.select(true));
		
		return result;
	}
	
}

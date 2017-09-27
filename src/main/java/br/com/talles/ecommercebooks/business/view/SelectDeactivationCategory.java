package br.com.talles.ecommercebooks.business.view;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.DeactivationCategoryDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

public class SelectDeactivationCategory implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao deactivationCategoryDao = new DeactivationCategoryDao();
		
		result.addEntities(deactivationCategoryDao.select(true));
		
		return result;
	}
	
}

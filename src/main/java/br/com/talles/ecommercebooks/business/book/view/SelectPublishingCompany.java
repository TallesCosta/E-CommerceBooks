package br.com.talles.ecommercebooks.business.book.view;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.book.PublishingCompanyDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

public class SelectPublishingCompany implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao publishingCompanyDao = new PublishingCompanyDao();
		
		result.addEntities(publishingCompanyDao.select(true));
		
		return result;
	}
	
}

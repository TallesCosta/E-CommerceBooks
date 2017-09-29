package br.com.talles.ecommercebooks.business.book.view;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.book.PriceGroupDao;
import br.com.talles.ecommercebooks.persistence.dao.IDao;

public class SelectPriceGroup implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao priceGroupDao = new PriceGroupDao();
		
		result.addEntities(priceGroupDao.select(true));
		
		return result;
	}
	
}

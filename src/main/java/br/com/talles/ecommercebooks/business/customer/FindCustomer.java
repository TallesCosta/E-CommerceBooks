package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;

public class FindCustomer implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		IDao dao = new CustomerDao();
		entity = dao.find(entity);
		
		return result;
	}
	
}

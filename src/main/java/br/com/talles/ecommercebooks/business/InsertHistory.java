package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.HistoryDao;

import java.util.Map;

public class InsertHistory implements IStrategy {
	
	protected Map<String, IDao> persistence;
	
	@Override
	public Result process(Entity entity, Result result) {
		IDao dao = new HistoryDao();
		dao.save(entity);
		
		return result;
	}	
	
}

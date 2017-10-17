package br.com.talles.ecommercebooks.business;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class DefinesPathSerializable implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		entity.getHistory().setPath(entity.getHistory().getPath() 
				+ "/" + entity.getId() + "-" + entity.getHistory().getDate().getTime());
		return result;
	}
	
}

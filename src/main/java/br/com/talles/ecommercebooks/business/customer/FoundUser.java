package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class FoundUser implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		if (!result.hasEntities()) {
			result.addMsg("Usuário ou senha inválido!\n");
		}
		return result;
	}
	
}

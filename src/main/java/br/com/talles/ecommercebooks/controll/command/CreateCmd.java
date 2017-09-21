package br.com.talles.ecommercebooks.controll.command;

import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

public class CreateCmd extends AbstractCommand {

	@Override
	public Result execute(Entity entity) {
		return facade.create(entity);
	}
	
}

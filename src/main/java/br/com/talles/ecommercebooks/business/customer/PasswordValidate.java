package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.User;

public class PasswordValidate implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		User user = (User) entity;
		
		if (!user.getPassword().equals(user.getPasswordVerify())) {
            result.addMsg("As senhas digitadas são diferentes!\n");
		}
		
		return result;
	}
	
}

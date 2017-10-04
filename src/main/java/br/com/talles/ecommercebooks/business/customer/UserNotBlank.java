package br.com.talles.ecommercebooks.business.customer;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.User;

public class UserNotBlank implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		User user = (User) entity;
		
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            result.addMsg("E-mail é um campo obrigatório!\n");
		} if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            result.addMsg("Senha é um campo obrigatório!\n");
		} if (user.getPasswordVerify() == null || user.getPasswordVerify().trim().isEmpty()) {
            result.addMsg("Confirmação da senha é um campo obrigatório!\n");
		}
		
		return result;
	}
	
}

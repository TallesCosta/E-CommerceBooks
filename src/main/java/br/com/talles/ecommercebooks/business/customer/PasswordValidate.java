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
		} if (!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            result.addMsg("A senha deve conter no mínimo 8 caracteres, entre eles número, "
					+ "letras minúsculas e maiúsculas e caracteres especiais!\n");
		}
		
		return result;
	}
	
}

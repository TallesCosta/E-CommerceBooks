package br.com.talles.ecommercebooks.business.user.list;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.User;

import javax.servlet.http.HttpSession;

public class LoginUser implements IStrategy {

	@Override
	public Result process(Entity entity, Result result) {
		if (!result.hasEntities()) {
			result.addMsg("Usuário ou senha inválido!\n");
		} else {
			HttpSession session = result.getTransaction().getRequest().getSession();

			User userSession = (User) result.getEntities(User.class.getSimpleName()).get(0);
			session.setAttribute("user", userSession);
		}
		return result;
	}
	
}

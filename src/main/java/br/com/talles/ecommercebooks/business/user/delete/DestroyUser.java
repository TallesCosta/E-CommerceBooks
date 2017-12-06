package br.com.talles.ecommercebooks.business.user.delete;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;

import javax.servlet.http.HttpSession;

public class DestroyUser implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        session.setAttribute("user", null);
        return result;
    }
    
}

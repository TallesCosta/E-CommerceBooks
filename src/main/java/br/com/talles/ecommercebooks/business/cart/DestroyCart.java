package br.com.talles.ecommercebooks.business.cart;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;

import javax.servlet.http.HttpSession;

public class DestroyCart implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        session.setAttribute("cart", new Cart());
        return result;
    }

}

package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;

import javax.servlet.http.HttpSession;

public class ValidateCart implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        Cart cartSession = (Cart) session.getAttribute("cart");

        if (cartSession == null || cartSession.countSaleItems() == 0)
            result.addMsg("Adicione itens ao carrinho para continuar!");

        return result;
    }

}

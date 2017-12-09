package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.Stock;

import javax.servlet.http.HttpSession;

public class ValidateAmountStock implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Cart cart = (Cart) entity;
        int i = cart.countSaleItems() - 1;

        HttpSession session = result.getTransaction().getRequest().getSession();
        Stock stockSession = (Stock) session.getAttribute("stock" + cart.getSaleItem(i).getBook().getId());

        if (cart.getSaleItem(i).getAmount() > stockSession.getRealAmount()) {
            result.addMsg("Livro indisponível temporariamente no estoque.");
        }

        return result;
    }

}

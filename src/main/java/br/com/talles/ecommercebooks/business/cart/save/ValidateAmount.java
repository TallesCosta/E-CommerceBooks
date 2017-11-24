package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.Stock;

public class ValidateAmount implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Cart cart = (Cart) entity;
        int i = cart.countSaleItems() - 1;
        Stock stock = cart.getSaleItem(i).getBook().getStock();

        if (cart.getSaleItem(i).getAmount() > stock.getVirtualAmount()) {
            result.addMsg("Livro indisponível temporariamente no estoque.");
        }

        return result;
    }

}

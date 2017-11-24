package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.StockDao;

public class CompleteCart implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Cart cart = (Cart) entity;
        int i = cart.countSaleItems() - 1;

        IDao dao = new StockDao();
        dao.find(cart.getSaleItem(i).getBook().getStock());

        return result;
    }

}

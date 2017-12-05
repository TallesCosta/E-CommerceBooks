package br.com.talles.ecommercebooks.business.cart.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.StockDao;

public class UpdateStockLastManipulation implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        IDao dao = new StockDao();

        Cart cart = (Cart) entity;
        int i = cart.countSaleItems() - 1;
        Stock stock = cart.getSaleItem(i).getBook().getStock();

        dao.update(stock, "UPDATE");

        return result;
    }

}

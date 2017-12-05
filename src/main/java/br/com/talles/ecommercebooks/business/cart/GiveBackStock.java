package br.com.talles.ecommercebooks.business.cart;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.SaleItem;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.StockDao;

public class GiveBackStock implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        IDao dao = new StockDao();

        Cart cart = (Cart) entity;
        for (SaleItem saleItem : cart.getSaleItems()) {
            Stock stock = saleItem.getBook().getStock();
            dao.update(stock, "UPDATE");
        }

        return result;
    }

}

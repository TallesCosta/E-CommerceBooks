package br.com.talles.ecommercebooks.business.cart;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.SaleItem;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.StockDao;

import javax.servlet.http.HttpSession;

public class CheckouStock implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        Cart cartSession = (Cart) session.getAttribute("cart");

        IDao dao = new StockDao();

        int i = 0;
        for (SaleItem saleItem : cartSession.getSaleItems()) {
            Stock stock = saleItem.getBook().getStock();
            dao.update(stock, "UPDATE");
            i++;
        }

        return result;
    }

}

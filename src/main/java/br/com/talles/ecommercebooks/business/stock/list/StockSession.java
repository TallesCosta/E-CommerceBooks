package br.com.talles.ecommercebooks.business.stock.list;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Stock;

import javax.servlet.http.HttpSession;
import java.util.List;

public class StockSession implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        List<Entity> stocks = result.getEntities(Stock.class.getSimpleName());

        for (Entity e : stocks) {
            Stock stock = (Stock) e;
            long idBook = stock.getBook().getId();
            Stock stockSession = (Stock) session.getAttribute("stock" + idBook);

            if (stockSession == null) {
                stockSession = stock;
                session.setAttribute("stock" + idBook, stockSession);
            }
        }

        return result;
    }

}

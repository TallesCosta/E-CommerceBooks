package br.com.talles.ecommercebooks.business.exchange;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import br.com.talles.ecommercebooks.domain.sale.Order;
import br.com.talles.ecommercebooks.domain.sale.SaleItem;
import br.com.talles.ecommercebooks.domain.sale.Stock;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.book.BookDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.StockDao;

public class CheckinStock implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Order order = (Order) entity;
        IDao dao;

        for (SaleItem saleItem : order.getSaleItems()) {
            Book book = saleItem.getBook();
            Stock stock = new Stock();
            stock.setBook(book);

            dao = new StockDao();
            dao.find(stock);

            stock.plusRealAmount(saleItem.getAmount());
            dao.update(stock, "UPDATE");
        }

        return result;
    }

}

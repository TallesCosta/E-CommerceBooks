package br.com.talles.ecommercebooks.business.exchange.update;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Exchange;
import br.com.talles.ecommercebooks.domain.sale.Status;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.SaleDao;

public class ExchangeReprovedStatusSale implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Exchange exchange = (Exchange) entity;

        IDao dao = new SaleDao();
        exchange.getOrder().setStatus(new Status("TROCA NEGADA"));
        dao.update(exchange.getOrder(), "UPDATE");

        return result;
    }

}

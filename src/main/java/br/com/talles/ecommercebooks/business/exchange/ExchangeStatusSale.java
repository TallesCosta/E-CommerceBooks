package br.com.talles.ecommercebooks.business.exchange;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.exchangeCoupon.GenerateExchangeCoupon;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.Exchange;
import br.com.talles.ecommercebooks.domain.sale.Status;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.ExchangeCouponDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.ExchangeDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.SaleDao;

public class ExchangeStatusSale implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Exchange exchange = (Exchange) entity;

        IDao dao = new SaleDao();
        dao.find(exchange.getOrder());

        switch (exchange.getOrder().getStatus().getName()) {
            case "ENTREGUE":
                exchange.getOrder().setStatus(new Status("TROCA EM ANÁLISE"));
                break;

            case "TROCA EM ANÁLISE":
                if (!exchange.isAccepted()) {
                    exchange.getOrder().setStatus(new Status("TROCA NEGADA"));
                }
                else {
                    if (exchange.getDestination() != null && exchange.getDestination().equals("stock")){
                        IStrategy checkinStock = new CheckinStock();
                        checkinStock.process(exchange.getOrder(), result);
                    }

                    exchange.getOrder().setStatus(new Status("TROCA APROVADA"));
                    IStrategy generateExchangeCoupon = new GenerateExchangeCoupon();
                    generateExchangeCoupon.process(exchange.getOrder(), result);
                }
                break;
        }

        dao.update(exchange.getOrder(), "UPDATE");

        return result;
    }

}

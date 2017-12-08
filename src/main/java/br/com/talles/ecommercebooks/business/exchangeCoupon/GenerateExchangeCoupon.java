package br.com.talles.ecommercebooks.business.exchangeCoupon;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.domain.sale.Order;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.ExchangeCouponDao;

import java.util.UUID;

public class GenerateExchangeCoupon implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Order order = (Order) entity;
        ExchangeCoupon exchangeCoupon = new ExchangeCoupon(UUID.randomUUID().toString(), order.getPrice());
        exchangeCoupon.setCustomer(order.getCustomer());

        IDao dao = new ExchangeCouponDao();
        dao.save(exchangeCoupon);

        return result;
    }

}

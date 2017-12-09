package br.com.talles.ecommercebooks.business.sale.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.exchangeCoupon.GenerateExchangeCoupon;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.domain.sale.Order;

public class ValidatePayment implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Order order = (Order) entity;

        double price = order.getPrice();
        double exchangeCouponsTotalValue = 0.0;
        for (ExchangeCoupon exchangeCoupon : order.getExchangeCoupons()) {
            exchangeCouponsTotalValue += exchangeCoupon.getValue();
        }

        if (order.hasExchangeCoupons()) {
            if (exchangeCouponsTotalValue < price)
                result.addMsg("Valor insuficiente para finalizar a compra!");
            else if (exchangeCouponsTotalValue > price) {
                double difference = exchangeCouponsTotalValue - price;
                IStrategy generateExchangeCoupon = new GenerateExchangeCoupon();
                generateExchangeCoupon.process(new Order(difference, order.getCustomer()), result);
            }
        }

        return result;
    }

}

package br.com.talles.ecommercebooks.business.sale.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.business.exchangeCoupon.GenerateExchangeCoupon;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.domain.sale.Order;

import java.math.BigDecimal;

public class ValidatePayment implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Order order = (Order) entity;
        double price = order.getPrice();

        // Calculate paymentValue to ExchangeCuopon
        BigDecimal exchangeCouponsTotalValue = new BigDecimal(0);
        for (ExchangeCoupon exchangeCoupon : order.getExchangeCoupons()) {
            exchangeCouponsTotalValue = exchangeCouponsTotalValue.add(BigDecimal.valueOf(exchangeCoupon.getValue()));
        }

        // Calculate paymentValue to CreditCard
        BigDecimal sumPaymentValue = new BigDecimal(0);
        for (CreditCard creditCard : order.getCreditCards()) {
            sumPaymentValue = sumPaymentValue.add(BigDecimal.valueOf(creditCard.getPaymentValue()));
        }

        BigDecimal exchangeCouponPlusCreditCard = exchangeCouponsTotalValue.add(sumPaymentValue);
        IStrategy generateExchangeCoupon = new GenerateExchangeCoupon();

        // ExchangeCoupon and CreditCard?
        if (order.hasExchangeCoupons() && order.hasCreditCards()) {
            // Payment greater than price?
            if (exchangeCouponPlusCreditCard.doubleValue() > price) {
                // Generates a ExclangeCoupon with the difference
                double difference = exchangeCouponPlusCreditCard.doubleValue() - price;
                generateExchangeCoupon.process(new Order(difference, order.getCustomer()), result);
            }
            // Payment less than price?
            else if (exchangeCouponPlusCreditCard.doubleValue() < price) {
                // Negative answer.
                result.addMsg("Valor insuficiente para finalizar a compra!");
            }
        }
        // Only ExchangeCoupon?
        else if (order.hasExchangeCoupons()) {
            // Payment less than price?
            if (exchangeCouponsTotalValue.doubleValue() < price) {
                // Negative answer.
                result.addMsg("Valor insuficiente para finalizar a compra!");
            }
            // Payment greater than price?
            else if (exchangeCouponsTotalValue.doubleValue() > price) {
                // Generates a ExclangeCoupon with the difference
                double difference = exchangeCouponsTotalValue.doubleValue() - price;
                generateExchangeCoupon.process(new Order(difference, order.getCustomer()), result);
            }
        }
        // Only CreditCard?
        else if (order.hasCreditCards()) {
            // Payment less than price?
            if (sumPaymentValue.doubleValue() < price) {
                // Negative answer.
                result.addMsg("Valor insuficiente para finalizar a compra!");
            }
            // Payment greater than price?
            else if (sumPaymentValue.doubleValue() > price) {
                // Negative answer.
                result.addMsg("Valor superior ao total da compra!");
            }
            // Minimum 10 reais in credit card
            for (CreditCard creditCard : order.getCreditCards()) {
                if (creditCard.getPaymentValue() < 10.0) {
                    result.addMsg("Compras no cartão devem ter o valor mínimo de R$ 10,00!");
                }
            }
        }

        return result;
    }

}

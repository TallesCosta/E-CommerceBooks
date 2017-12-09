package br.com.talles.ecommercebooks.business.sale.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.domain.sale.Order;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.ExchangeCouponDao;

import java.util.Arrays;

public class DisableExchangeCoupons implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Order order = (Order) entity;

        IDao dao = new ExchangeCouponDao();
        for (ExchangeCoupon exchangeCoupon : order.getExchangeCoupons()) {
            Customer customer = order.getCustomer();
            customer.setSales(Arrays.asList((Sale) order));

            exchangeCoupon.setCustomer(customer);
            exchangeCoupon.setEnabled(false);
            dao.update(exchangeCoupon ,"UPDATE");
        }

        return result;
    }

}

package br.com.talles.ecommercebooks.business.sale.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.sale.Order;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CreditCardDao;

import java.util.Arrays;

public class RelatesCardsForSale implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Order order = (Order) entity;

        IDao dao = new CreditCardDao();
        for (CreditCard creditCard : order.getCreditCards()) {
            Customer customer = order.getCustomer();
            customer.setSales(Arrays.asList((Sale) order));

            creditCard.setCustomer(customer);
            dao.update(creditCard,"UPDATE");
        }

        return result;
    }

}

package br.com.talles.ecommercebooks.business.sale.create;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.domain.sale.Delivery;
import br.com.talles.ecommercebooks.domain.sale.PromotionalCoupon;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.BaseShippingCostDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.PromotionalCouponDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CustomerFragment implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        User userSession = (User) session.getAttribute("user");

        IDao dao = new CustomerDao();
        List<Entity> customers = dao.select(true, new Customer(userSession));
        Customer customer = (Customer) customers.get(0);

        // Remove duplicate registries
        customer.removeDeliveryAddress(customer.countDeliveryAddresses() / 2);
        customer.removeCreditCards(customer.countCreditCards() / 2);

        // Get DeliveryAddresses that Customer
        dao = new BaseShippingCostDao();
        List<Entity> deEntities = new ArrayList<>();
        List<DeliveryAddress> deliveryAddresses = customer.getDeliveryAddresses();
        for (DeliveryAddress deliveryAddress : deliveryAddresses) {
            Delivery delivery = new Delivery();
            delivery.setDeliveryAddress(deliveryAddress);
            dao.find(delivery);
            deEntities.add(delivery);
        }
        result.addEntities(deEntities);

        // Get CreditCards that Customer
        List<Entity> ccEntities = new ArrayList<>();
        List<CreditCard> creditCards = customer.getCreditCards();
        for (CreditCard creditCard : creditCards) {
            ccEntities.add(creditCard);
        }
        result.addEntities(ccEntities);

        // Get all PromotionalCoupons to possible validates
        dao = new PromotionalCouponDao();
        result.addEntities(dao.select(true, new PromotionalCoupon()));

        return result;
    }

}

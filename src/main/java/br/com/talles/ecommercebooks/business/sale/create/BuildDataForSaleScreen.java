package br.com.talles.ecommercebooks.business.sale.create;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.*;
import br.com.talles.ecommercebooks.domain.sale.Delivery;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.domain.sale.PromotionalCoupon;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.BaseShippingCostDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.ExchangeCouponDao;
import br.com.talles.ecommercebooks.persistence.dao.sale.PromotionalCouponDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BuildDataForSaleScreen implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        HttpSession session = result.getTransaction().getRequest().getSession();
        User userSession = (User) session.getAttribute("user");

        IDao dao = new CustomerDao();
        List<Entity> customers = dao.select(true, new Customer(userSession));
        Customer customer = (Customer) customers.get(0);
        result.addEntity(customer);

        // Remove duplicate registries ChargeAddresses
        List<ChargeAddress> casReapeat = customer.getChargeAddresses();
        List<ChargeAddress> cas = new ArrayList<>();
        for(ChargeAddress chargeAddress : casReapeat) {
            if (!cas.contains(chargeAddress))
                cas.add(chargeAddress);
        }

        // Remove duplicate registries DeliveryAddresses
        List<DeliveryAddress> dasReapeat = customer.getDeliveryAddresses();
        List<DeliveryAddress> das = new ArrayList<>();
        for(DeliveryAddress deliveryAddress : dasReapeat) {
            if (!das.contains(deliveryAddress))
                das.add(deliveryAddress);
        }

        // Remove duplicate registries CreditCards
        List<CreditCard> ccsReapeat = customer.getCreditCards();
        List<CreditCard> ccs = new ArrayList<>();
        for(CreditCard creditCard : ccsReapeat) {
            if (!ccs.contains(creditCard))
                ccs.add(creditCard);
        }

        // Get ChargeAddresses that Customer
        List<Entity> caEntities = new ArrayList<>();
        List<ChargeAddress> chargeAddresses = cas;
        for (ChargeAddress chargeAddress : chargeAddresses) {
            caEntities.add(chargeAddress);
        }
        result.addEntities(caEntities);

        // Get CreditCards that Customer
        List<Entity> ccEntities = new ArrayList<>();
        for (CreditCard creditCard : ccs) {
            ccEntities.add(creditCard);
        }
        result.addEntities(ccEntities);

        // Get DeliveryAddresses with shipping-cost value that Customer
        dao = new BaseShippingCostDao();
        List<Entity> daEntities = new ArrayList<>();
        for (DeliveryAddress deliveryAddress : das) {
            Delivery delivery = new Delivery();
            delivery.setDeliveryAddress(deliveryAddress);
            dao.find(delivery);
            daEntities.add(delivery);
        }
        result.addEntities(daEntities);

        // Get ExchangeCoupons that Customer
        dao = new ExchangeCouponDao();
        result.addEntities(dao.select(true, new ExchangeCoupon(customer)));

        // Get all PromotionalCoupons to possible validates
        dao = new PromotionalCouponDao();
        result.addEntities(dao.select(true, new PromotionalCoupon()));

        return result;
    }

}

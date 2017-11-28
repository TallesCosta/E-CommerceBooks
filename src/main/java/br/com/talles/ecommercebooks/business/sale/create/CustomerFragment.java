package br.com.talles.ecommercebooks.business.sale.create;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CustomerFragment implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        List<Entity> entities = new ArrayList<>();
        HttpSession session = result.getTransaction().getRequest().getSession();
        User userSession = (User) session.getAttribute("user");

        IDao dao = new CustomerDao();
        List<Entity> customers = dao.select(true, new Customer(userSession));
        Customer customer = (Customer) customers.get(0);

        // Get DeliveryAddresses that Customer
        List<DeliveryAddress> deliveryAddresses = customer.getDeliveryAddresses();
        for (DeliveryAddress deliveryAddress : deliveryAddresses) {
            entities.add(deliveryAddress);
        }
        result.addEntities(entities);
        entities.clear();

        // Get CreditCards that Customer
        List<CreditCard> creditCards = customer.getCreditCards();
        for (CreditCard creditCard : creditCards) {
            entities.add(creditCard);
        }
        result.addEntities(entities);
        entities.clear();

        // TODO: Contemple n ChageAddresses
        result.addEntity(customer.getChargeAddress());

        return result;
    }

}

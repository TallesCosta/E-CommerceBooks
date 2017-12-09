package br.com.talles.ecommercebooks.business.order.list;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.domain.sale.OrderRequest;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;

import javax.servlet.http.HttpSession;
import java.util.List;

public class SetCustomerCurrent implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        OrderRequest order = (OrderRequest) entity;

        HttpSession session = result.getTransaction().getRequest().getSession();
        User userSession = (User) session.getAttribute("user");

        IDao dao = new CustomerDao();
        List<Entity> customers = dao.select(true, new Customer(userSession));
        Customer customer = (Customer) customers.get(0);

        order.setCustomer(customer);

        result.addEntity(customer);
        return result;
    }

}

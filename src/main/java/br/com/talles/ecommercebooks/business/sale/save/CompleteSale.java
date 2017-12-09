package br.com.talles.ecommercebooks.business.sale.save;

import br.com.talles.ecommercebooks.business.IStrategy;
import br.com.talles.ecommercebooks.controll.Result;
import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.User;
import br.com.talles.ecommercebooks.domain.sale.Cart;
import br.com.talles.ecommercebooks.domain.sale.Sale;
import br.com.talles.ecommercebooks.domain.sale.Status;
import br.com.talles.ecommercebooks.persistence.dao.IDao;
import br.com.talles.ecommercebooks.persistence.dao.customer.CustomerDao;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CompleteSale implements IStrategy {

    @Override
    public Result process(Entity entity, Result result) {
        Sale sale = (Sale) entity;

        sale.setSaleNumber(UUID.randomUUID().toString());
        sale.setDate(new Date());
        Date date = new Date();
        date.setTime(date.getTime() + 5000000000L);
        sale.getDelivery().setDeliveryForecast(date);

        HttpSession session = result.getTransaction().getRequest().getSession();
        Cart cartSession = (Cart) session.getAttribute("cart");

        //sale.setPrice(cartSession.getPrice());
        sale.setTotalAmount(cartSession.getTotalAmount());
        sale.setSaleItems(cartSession.getSaleItems());

        User userSession = (User) session.getAttribute("user");
        Customer customer = new Customer(userSession);
        IDao dao = new CustomerDao();
        List<Entity> entities = dao.select(true, customer);
        sale.setCustomer((Customer) entities.get(0));

        sale.setStatus(new Status("EM PROCESSAMENTO"));

        return result;
    }

}

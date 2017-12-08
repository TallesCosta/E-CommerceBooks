package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.customer.Customer;

public class ExchangeCoupon extends Coupon {

    private Customer customer;

    // Constructors
    public ExchangeCoupon() {
    }

    public ExchangeCoupon(long id) {
        super(id);
    }

    public ExchangeCoupon(String code, double value) {
        super(code, value);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}

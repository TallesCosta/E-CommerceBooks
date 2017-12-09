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

    public ExchangeCoupon(Customer customer) {
        this.customer = customer;
    }

    public ExchangeCoupon(long id, Double value) {
        super(id);
        this.setValue(value);
    }

    public ExchangeCoupon(long id, Customer customer) {
        super(id);
        this.customer = customer;
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

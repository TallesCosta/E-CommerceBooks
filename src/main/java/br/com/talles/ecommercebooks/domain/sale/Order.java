package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;

import java.util.Date;
import java.util.List;

public class Order extends Entity {

    // Attributes
    private String saleNumber;
    private Date date;
    private double price;
    private int totalAmount;
    private Status status;
    private Delivery delivery;
    private PromotionalCoupon promotionalCoupon;
    private CreditCard creditCard;
    private Customer customer;
    private Exchange exchange;
    private List<SaleItem> saleItems;

    // Constructors
    public Order() {
        super(true);
    }

    public Order(long id) {
        super(id, true);
    }

    public Order(String saleNumber, Date date, double price, int totalAmount, Status status, Delivery delivery, PromotionalCoupon promotionalCoupon, CreditCard creditCard, Customer customer, Exchange exchange, List<SaleItem> saleItems) {
        super(true);
        this.saleNumber = saleNumber;
        this.date = date;
        this.price = price;
        this.totalAmount = totalAmount;
        this.status = status;
        this.delivery = delivery;
        this.promotionalCoupon = promotionalCoupon;
        this.creditCard = creditCard;
        this.customer = customer;
        this.exchange = exchange;

        this.saleItems = saleItems;
    }

    // Getters
    public String getSaleNumber() {
        return saleNumber;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public PromotionalCoupon getPromotionalCoupon() {
        return promotionalCoupon;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    // Setters
    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setPromotionalCoupon(PromotionalCoupon promotionalCoupon) {
        this.promotionalCoupon = promotionalCoupon;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

}

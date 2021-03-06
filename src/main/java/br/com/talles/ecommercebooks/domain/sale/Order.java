package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;

import java.util.ArrayList;
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
    private Customer customer;
    private Exchange exchange;
    private List<CreditCard> creditCards;
    private List<ExchangeCoupon> exchangeCoupons;
    private List<SaleItem> saleItems;

    // Constructors
    public Order() {
        super(true);

        this.creditCards = new ArrayList<>();
        this.saleItems = new ArrayList<>();
        this.exchangeCoupons = new ArrayList<>();
    }

    public Order(long id) {
        super(id, true);

        this.creditCards = new ArrayList<>();
        this.saleItems = new ArrayList<>();
        this.exchangeCoupons = new ArrayList<>();
    }

    public Order(double price, Customer customer) {
        this.price = price;
        this.customer = customer;

        this.creditCards = creditCards;
        this.saleItems = saleItems;
        this.exchangeCoupons = exchangeCoupons;
    }

    public Order(String saleNumber, Date date, double price, int totalAmount, Status status, Delivery delivery, PromotionalCoupon promotionalCoupon, Customer customer, Exchange exchange, List<CreditCard> creditCards, List<SaleItem> saleItems, List<ExchangeCoupon> exchangeCoupons) {
        super(true);
        this.saleNumber = saleNumber;
        this.date = date;
        this.price = price;
        this.totalAmount = totalAmount;
        this.status = status;
        this.delivery = delivery;
        this.promotionalCoupon = promotionalCoupon;
        this.customer = customer;
        this.exchange = exchange;

        this.creditCards = creditCards;
        this.saleItems = saleItems;
        this.exchangeCoupons = exchangeCoupons;
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

    public List<CreditCard> getCreditCards() {
        return creditCards;
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

    public List<ExchangeCoupon> getExchangeCoupons() {
        return exchangeCoupons;
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

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
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

    public void setExchangeCoupons(List<ExchangeCoupon> exchangeCoupons) {
        this.exchangeCoupons = exchangeCoupons;
    }

    // Others methods
    public boolean hasExchangeCoupons() {
        return !this.exchangeCoupons.isEmpty();
    }

    public boolean hasCreditCards() {
        return !this.creditCards.isEmpty();
    }

}

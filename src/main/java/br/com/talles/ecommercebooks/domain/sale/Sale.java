package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Address;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;

import java.util.Date;
import java.util.List;

public class Sale extends Entity {

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
	private List<SaleItem> saleItems;

	// Constructors
	public Sale() {
		super(true);
	}

	// Getters

	public Sale(String saleNumber, Date date, double price, int totalAmount, Status status, Delivery delivery, PromotionalCoupon promotionalCoupon, CreditCard creditCard, Customer customer, List<SaleItem> saleItems) {
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

		this.saleItems = saleItems;
	}

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

	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}



}

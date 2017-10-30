package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.Address;
import br.com.talles.ecommercebooks.domain.customer.CreditCard;
import br.com.talles.ecommercebooks.domain.customer.Customer;

import java.util.Date;
import java.util.List;

public class Sale extends Entity {

	// Attributes
	private String saleNumber;
	private Date date;
	private double price;
	private int totalAmount;
	private Status status;
	private Date deliveryForecast;
	private PromotionalCoupon promotionalCoupon;
	private Address deliveryAddress;
	private Address chargeAddress;
	private CreditCard creditCard;
	private Customer customer;
	private List<SaleItem> saleItems;

	// Constructors
	public Sale() { }

	public Sale(String saleNumber, Date date, double price, int totalAmount, Status status, Date deliveryForecast, PromotionalCoupon promotionalCoupon, Address deliveryAddress, Address chargeAddress, CreditCard creditCard, Customer customer, List<SaleItem> saleItems) {
		this.saleNumber = saleNumber;
		this.date = date;
		this.price = price;
		this.totalAmount = totalAmount;
		this.status = status;
		this.deliveryForecast = deliveryForecast;
		this.promotionalCoupon = promotionalCoupon;
		this.deliveryAddress = deliveryAddress;
		this.chargeAddress = chargeAddress;
		this.creditCard = creditCard;
		this.customer = customer;
		
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

	public Date getDeliveryForecast() {
		return deliveryForecast;
	}
	
	public PromotionalCoupon getPromotionalCoupon() {
		return promotionalCoupon;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public Address getChargeAddress() {
		return chargeAddress;
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

	public void setDeliveryForecast(Date deliveryForecast) {
		this.deliveryForecast = deliveryForecast;
	}
	
	public void setPromotionalCoupon(PromotionalCoupon promotionalCoupon) {
		this.promotionalCoupon = promotionalCoupon;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setChargeAddress(Address chargeAddress) {
		this.chargeAddress = chargeAddress;
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

package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;

import java.util.List;

public class Cart extends Entity {
	
	// Attributes
	private double price;
	private int totalAmount;
	private List<SaleItem> saleItems;

	// Constructors
	public Cart() { }

	public Cart(double price, int totalAmount, List<SaleItem> saleItems) {
		this.price = price;
		this.totalAmount = totalAmount;		
		this.saleItems = saleItems;
	}

	// Getters
	public double getPrice() {
		return price;
	}

	public int getTotalAmount() {
		return totalAmount;
	}
	
	public List<SaleItem> getSaleItems() {
		return saleItems;
	}

	// Setters
	public void setPrice(double price) {
		this.price = price;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}
	
	public void addSaleItem(SaleItem saleItem) {
		this.saleItems.add(saleItem);
	}
	
	// 
	public void calcTotalPrice() {
		
	}
	
	public void calcTotalAmount() {
		
	}
	
}

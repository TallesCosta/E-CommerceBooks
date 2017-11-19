package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import java.util.ArrayList;

import java.util.List;

public class Cart extends Entity {
	
	// Attributes
	private double price;
	private int totalAmount;
	private List<SaleItem> saleItems;

	// Constructors
	public Cart() {
		this.price = 0.0;
		this.totalAmount = 0;
		this.saleItems = new ArrayList<>();
	}

	public Cart(List<SaleItem> saleItems) {
		this.price = 0.0;
		this.totalAmount = 0;
		this.saleItems = saleItems;
	}

	// Getters
	public double getPrice() {
		return price;
	}

	public int getTotalAmount() {
		return totalAmount;
	}
	
	public SaleItem getSaleItem(int index) {
		return saleItems.get(index);
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
		
		updateValues();
	}
	
	public void addSaleItems(List<SaleItem> saleItems) {
		for(SaleItem saleItem : saleItems){
			this.saleItems.add(saleItem);
		}
		
		updateValues();
	}
	
	public void removeSaleItem(SaleItem saleItem) {
		this.saleItems.remove(saleItem);
		
		updateValues();
	}
	
	public boolean hasSaleItem(){
		return !this.saleItems.isEmpty();
	}
	
	// Updates the cart when a new sale-item is added.
	private void updateValues() {
		if (saleItems.isEmpty()) {
			price = 0.0;
			totalAmount = 0;
		} else {
			SaleItem saleItem = saleItems.get(saleItems.size() - 1);
			price += saleItem.getUnitaryPrice() * saleItem.getAmount();
			totalAmount += 1;
		}
	}
	
}

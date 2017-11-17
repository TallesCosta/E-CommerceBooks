package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;

public class StockItem extends Entity {

	// Attributes
	private double unitaryPrice;
	private int amount;

	// Constructors
	public StockItem() { }

	public StockItem(double unitaryPrice, int amount) {
		this.unitaryPrice = unitaryPrice;
		this.amount = amount;
	}

	// Getters
	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public int getAmount() {
		return amount;
	}

	// Setters
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}



}

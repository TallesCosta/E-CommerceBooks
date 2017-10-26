package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.book.Book;

public class SaleItem {

	// Attributes
	private double unitaryPrice;
	private int amount;
	private Book book;

	// Constructors
	public SaleItem() { }

	public SaleItem(double unitaryPrice, int amount, Book book) {
		this.unitaryPrice = unitaryPrice;
		this.amount = amount;
		this.book = book;
	}

	// Getters
	public Book getBook() {
		return book;
	}

	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public int getAmount() {
		return amount;
	}
	
	// Setters
	public void setBook(Book book) {
		this.book = book;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	

}

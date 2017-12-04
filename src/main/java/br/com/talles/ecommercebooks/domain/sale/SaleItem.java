package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;

public class SaleItem extends Entity {

	// Attributes
	private double unitaryPrice;
	private int amount;
	private Book book;

	// Constructors
	public SaleItem() { super(true); }

	public SaleItem(double unitaryPrice, int amount) {
		super(true);
		this.unitaryPrice = unitaryPrice;
		this.amount = amount;
	}
	
	public SaleItem(double unitaryPrice, int amount, Book book) {
		super(true);
		this.unitaryPrice = unitaryPrice;
		this.amount = amount;
		this.book = book;
	}

	public SaleItem(long id, double unitaryPrice, int amount, Book book) {
		super(id, true);
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

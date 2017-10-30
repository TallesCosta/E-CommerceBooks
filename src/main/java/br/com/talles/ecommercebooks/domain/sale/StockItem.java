package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;

public class StockItem extends Entity {

	// Attributes
	private double unitaryPrice;
	private int amount;
	private Book book;

	// Constructors
	public StockItem() { }

	public StockItem(double unitaryPrice, int amount, Book book) {
		this.unitaryPrice = unitaryPrice;
		this.amount = amount;
		this.book = book;
	}

	// Getters
	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public int getAmount() {
		return amount;
	}

	public Book getBook() {
		return book;
	}

	// Setters
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setBook(Book book) {
		this.book = book;
	}



}

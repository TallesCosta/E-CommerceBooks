package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.book.Book;
import java.util.ArrayList;

import java.util.List;

public class Stock extends Entity {

	// Attributes
	private double averagePrice;
	private double minimumPrice;
	private double salePrice;
	private int realAmount;
	private int virtualAmount;
	private Book book;
	private List<StockItem> stockItems;

	// Constructors
	public Stock() {
		super(true);
		this.book = new Book();
		
		this.stockItems = new ArrayList<>();
	}

	public Stock(long id) {
		super(id, true);
		this.book = new Book();

		this.stockItems = new ArrayList<>();
	}

	public Stock(double averagePrice, double minimumPrice, double salePrice, int realAmount, int virtualAmount, 
			Book book, List<StockItem> stockItems) {
		super(true);
		this.averagePrice = averagePrice;
		this.minimumPrice = minimumPrice;
		this.salePrice = salePrice;
		this.realAmount = realAmount;
		this.virtualAmount = virtualAmount;
		this.book = book;
		
		this.stockItems = stockItems;
	}

	public Stock(long id, double averagePrice, double minimumPrice, double salePrice, int realAmount, int virtualAmount,
				 Book book, List<StockItem> stockItems) {
		super(id, true);
		this.averagePrice = averagePrice;
		this.minimumPrice = minimumPrice;
		this.salePrice = salePrice;
		this.realAmount = realAmount;
		this.virtualAmount = virtualAmount;
		this.book = book;

		this.stockItems = stockItems;
	}

	// Getters
	public double getAveragePrice() {
		return averagePrice;
	}

	public double getMinimumPrice() {
		return minimumPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public int getRealAmount() {
		return realAmount;
	}

	public int getVirtualAmount() {
		return virtualAmount;
	}

	public Book getBook() {
		return book;
	}
	
	public List<StockItem> getStockItems() {
		return stockItems;
	}

	// Setters
	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public void setMinimumPrice(double minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public void setRealAmount(int realAmount) {
		this.realAmount = realAmount;
	}

	public void setVirtualAmount(int virtualAmount) {
		this.virtualAmount = virtualAmount;
	}

	public void setStockItems(List<StockItem> stockItems) {
		this.stockItems = stockItems;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	

}

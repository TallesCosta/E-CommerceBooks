package br.com.talles.ecommercebooks.domain;

import java.util.ArrayList;
import java.util.List;

public class Book extends Entity {

	private String title;
	private String edition;
	private int publicationYear;
	private int numberOfPages;
	private String synopsis;
	private String isbn;
	private String ean13;
	private Author author;
	private PublishingCompany publishingCompany;
	private Dimension dimension;
	private PriceGroup priceGroup;
	private SaleParameterization saleParameterization;
	private ChangeStatus changeStatus;
	private List<Category> categories;

	public Book() {
		super(true);
		
		this.categories = new ArrayList<>();
	}

	public Book(String title, String edition, int publicationYear, int numberOfPages, String synopsis, 
			String isbn, String ean13) {
		super(true);
		this.title = title;
		this.edition = edition;
		this.publicationYear = publicationYear;
		this.numberOfPages = numberOfPages;
		this.synopsis = synopsis;
		this.isbn = isbn;
		this.ean13 = ean13;
		
		this.categories = new ArrayList<>();
	}

	public Book(String title, String edition, int publicationYear, int numberOfPages, String synopsis, 
			String isbn, String ean13, Dimension dimension, SaleParameterization saleParameterization) {
		super(true);
		this.title = title;
		this.edition = edition;
		this.publicationYear = publicationYear;
		this.numberOfPages = numberOfPages;
		this.synopsis = synopsis;
		this.isbn = isbn;
		this.ean13 = ean13;
		this.dimension = dimension;
		this.saleParameterization = saleParameterization;
		
		this.categories = new ArrayList<>();
	}
	
	public Book(String title, String edition, int publicationYear, int numberOfPages, String synopsis, 
			String isbn, String ean13, Dimension dimension, PriceGroup priceGroup, 
			PublishingCompany publishingCompany, SaleParameterization saleParameterization, 
			ChangeStatus changeStatus, Author author) {
		super(true);
		this.title = title;
		this.edition = edition;
		this.publicationYear = publicationYear;
		this.numberOfPages = numberOfPages;
		this.synopsis = synopsis;
		this.isbn = isbn;
		this.ean13 = ean13;
		this.dimension = dimension;
		this.priceGroup = priceGroup;
		this.publishingCompany = publishingCompany;
		this.saleParameterization = saleParameterization;
		this.changeStatus = changeStatus;
		this.author = author;
		
		this.categories = new ArrayList<>();
	}

	public Book(String title, String edition, int publicationYear, int numberOfPages, String synopsis, 
			String isbn, String ean13, Dimension dimension, PriceGroup priceGroup, 
			PublishingCompany publishingCompany, SaleParameterization saleParameterization, 
			ChangeStatus changeStatus, Author author, List<Category> categories) {
		super(true);
		this.title = title;
		this.edition = edition;
		this.publicationYear = publicationYear;
		this.numberOfPages = numberOfPages;
		this.synopsis = synopsis;
		this.isbn = isbn;
		this.ean13 = ean13;
		this.dimension = dimension;
		this.priceGroup = priceGroup;
		this.publishingCompany = publishingCompany;
		this.saleParameterization = saleParameterization;
		this.changeStatus = changeStatus;
		this.author = author;
		
		this.categories = new ArrayList<>();
		this.categories = categories;
	}

	public Book(String title, String edition, int publicationYear, int numberOfPages, String synopsis, String isbn, 
			String ean13, Dimension dimension, PriceGroup priceGroup, PublishingCompany publishingCompany, 
			SaleParameterization saleParameterization, ChangeStatus changeStatus, Author author, 
			List<Category> categories, long id) {
		super(id, true);
		this.title = title;
		this.edition = edition;
		this.publicationYear = publicationYear;
		this.numberOfPages = numberOfPages;
		this.synopsis = synopsis;
		this.isbn = isbn;
		this.ean13 = ean13;
		this.dimension = dimension;
		this.priceGroup = priceGroup;
		this.publishingCompany = publishingCompany;
		this.saleParameterization = saleParameterization;
		this.changeStatus = changeStatus;
		this.author = author;
		
		this.categories = new ArrayList<>();
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public PublishingCompany getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(PublishingCompany publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public PriceGroup getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(PriceGroup priceGroup) {
		this.priceGroup = priceGroup;
	}

	public SaleParameterization getSaleParameterization() {
		return saleParameterization;
	}

	public void setSaleParameterization(SaleParameterization saleParameterization) {
		this.saleParameterization = saleParameterization;
	}

	public ChangeStatus getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(ChangeStatus changeStatus) {
		this.changeStatus = changeStatus;
	}
	
	public Category getCategory(int index){
		return this.categories.get(index);
	}
	
	public List<Category> getCategories(){
		return this.categories;
	}
	
	public void setCategory(Category category){
		this.categories.clear();
		this.categories.add(category);
	}
	
	public void setCategories(List<Category> categories){
		this.categories.clear();
		this.categories = categories;
	}
	
	public void addCategory(Category category){
		this.categories.add(category);
	}
	
	public void addCategories(List<Category> categories){
		for(Category category : categories){
			this.categories.add(category);
		}
	}
	
	public int countCategories(){
		return this.categories.size();
	}
	
	public boolean containsCategory(Category category){
		return this.categories.contains(category);
	}
	
	public void clearCategories(){
		this.categories.clear();
	}
	
	public boolean categoriesAreEmpty(){
		return this.categories.isEmpty();
	}
	
}

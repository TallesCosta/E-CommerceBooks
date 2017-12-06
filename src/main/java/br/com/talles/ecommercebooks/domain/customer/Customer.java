package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.PromotionalCoupon;
import br.com.talles.ecommercebooks.domain.sale.Sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends Entity {

	private String registry;
	private String name;
	private Date birthDate;
	private Gender gender;
	private Phone phone;
	private User user;
	private Address homeAddress;
	// TODO: Make the asssociation with n chargeAddress
	private Address chargeAddress;
	private List<DeliveryAddress> deliveryAddresses;
	private List<CreditCard> creditCards;
	private List<Sale> sales;
	//private List<PromotionalCoupon> promotionalCoupons;

	public Customer() {
		super(true);
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}

	public Customer(User user) {
		super(true);
		this.birthDate = new Date(0L);
		this.gender = new Gender();
		this.phone = new Phone();
		this.homeAddress = new Address();
		this.chargeAddress = new Address();

		this.user = user;

		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}

	public Customer(String name, long id){
		super(id, true);
		this.name = name;

		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			Address homeAddress) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			Address homeAddress, Address chargeAddres) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		this.chargeAddress = chargeAddres;
		
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			Address homeAddress, List<CreditCard> creditCards) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		
		this.creditCards = creditCards;
		this.deliveryAddresses = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			Address homeAddress, List<CreditCard> creditCards, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		
		this.creditCards = creditCards;
		this.deliveryAddresses = new ArrayList<>();
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			Address homeAddress, Address chargeAddres, List<CreditCard> creditCard, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		this.chargeAddress = chargeAddres;
		
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = creditCard;
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			Address homeAddress, Address chargeAddress, List<CreditCard> creditCard, List<DeliveryAddress> deliveryAddresses) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		this.chargeAddress = chargeAddress;
		
		this.deliveryAddresses = deliveryAddresses;
		this.creditCards = creditCard;
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, 
			User user, Address homeAddress, Address chargeAddress, List<CreditCard> creditCard, 
			List<DeliveryAddress> deliveryAddresses, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		this.chargeAddress = chargeAddress;
		
		this.deliveryAddresses = deliveryAddresses;
		this.creditCards = creditCard;
		this.sales = new ArrayList<>();
		//this.promotionalCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, 
			User user, Address homeAddress, Address chargeAddress, List<CreditCard> creditCard, 
			List<DeliveryAddress> deliveryAddresses, List<Sale> sales, List<PromotionalCoupon> promotionalCoupons, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.homeAddress = homeAddress;
		this.chargeAddress = chargeAddress;
		
		this.deliveryAddresses = deliveryAddresses;
		this.creditCards = creditCard;
		this.sales = sales;
		//this.promotionalCoupons = promotionalCoupons;
	}
	
	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getChargeAddress() {
		return chargeAddress;
	}

	public void setChargeAddress(Address chargeAddress) {
		this.chargeAddress = chargeAddress;
	}

	public DeliveryAddress getDeliveryAddress(int index){
		return this.deliveryAddresses.get(index);
	}
	
	public List<DeliveryAddress> getDeliveryAddresses(){
		return this.deliveryAddresses;
	}
	
	public void setDeliveryAddress(DeliveryAddress deliveryAddress){
		this.deliveryAddresses.clear();
		this.deliveryAddresses.add(deliveryAddress);
	}
	
	public void setDeliveryAddresses(List<DeliveryAddress> deliveryAddresses){
		this.deliveryAddresses.clear();
		this.deliveryAddresses = deliveryAddresses;
	}
	
	public void addDeliveryAddress(DeliveryAddress deliveryAddress){
		this.deliveryAddresses.add(deliveryAddress);
	}
	
	public void addDeliveryAddresses(List<DeliveryAddress> deliveryAddresses){
		for(DeliveryAddress deliveryAddress : deliveryAddresses){
			this.deliveryAddresses.add(deliveryAddress);
		}
	}
	
	public int countDeliveryAddresses(){
		return this.deliveryAddresses.size();
	}
	
	public boolean containsDeliveryAddress(DeliveryAddress deliveryAddress){
		return this.deliveryAddresses.contains(deliveryAddress);
	}
	
	public void clearDeliveryAddresses(){
		this.deliveryAddresses.clear();
	}
	
	public boolean deliveryAddressesAreEmpty(){
		return this.deliveryAddresses.isEmpty();
	}

	public void removeDeliveryAddress(int amount) {
		int i = this.deliveryAddresses.size() - 1;
		while (amount > 0) {
			this.deliveryAddresses.remove(i);
			i -= 2;
			amount--;
		}
	}

	public List<CreditCard> getCreditCards() {
		return this.creditCards;
	}

	public CreditCard getCreditCard(int index) {
		return this.creditCards.get(index);
	}
	
	public void setCreditCards(List<CreditCard> creditCard) {
		this.creditCards = creditCard;
	}

	public void addCreditCard(CreditCard creditCard){
		this.creditCards.add(creditCard);
	}

	public void addCreditCards(List<CreditCard> creditCards){
		for(CreditCard creditCard : creditCards){
			this.creditCards.add(creditCard);
		}
	}

	public int countCreditCards(){
		return this.creditCards.size();
	}

	public void removeCreditCards(int amount) {
		int i = this.creditCards.size() - 1;
		while (amount > 0) {
			this.creditCards.remove(i);
			i--;
			amount--;
		}
	}

	public List<Sale> getSales() {
		return sales;
	}

	public Sale getSale(int index){
		return this.sales.get(index);
	}
	
	public void setSales(List<Sale> sale) {
		this.sales = sale;
	}

	/*
	public List<PromotionalCoupon> getPromotionalCoupons() {
		return promotionalCoupons;
	}

	public PromotionalCoupon getPromotionalCoupon(int index){
		return this.promotionalCoupons.get(index);
	}
	
	public void setPromotionalCoupons(List<PromotionalCoupon> promotionalCoupons) {
		this.promotionalCoupons = promotionalCoupons;
	}
	*/
	
	
	
}

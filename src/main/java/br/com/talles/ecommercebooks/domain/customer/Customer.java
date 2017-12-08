package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.sale.ExchangeCoupon;
import br.com.talles.ecommercebooks.domain.sale.PromotionalCoupon;
import br.com.talles.ecommercebooks.domain.sale.Sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Customer extends Entity {

	private String registry;
	private String name;
	private Date birthDate;
	private Gender gender;
	private Phone phone;
	private User user;
	private List<ChargeAddress> chargeAddresses;
	private List<DeliveryAddress> deliveryAddresses;
	private List<CreditCard> creditCards;
	private List<Sale> sales;
	private List<ExchangeCoupon> exchangeCoupons;

	public Customer() {
		super(true);
		this.chargeAddresses = new ArrayList<>();
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}

	public Customer(long id) {
		super(id, true);
	}

	public Customer(User user) {
		super(true);
		this.birthDate = new Date(0L);
		this.gender = new Gender();
		this.phone = new Phone();

		this.user = user;

		this.chargeAddresses = new ArrayList();
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}

	public Customer(String name, long id){
		super(id, true);
		this.name = name;

		this.chargeAddresses = new ArrayList();
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = new ArrayList();
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			List<CreditCard> creditCards) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = new ArrayList();
		this.creditCards = creditCards;
		this.deliveryAddresses = new ArrayList<>();
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			List<CreditCard> creditCards, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = new ArrayList();
		this.creditCards = creditCards;
		this.deliveryAddresses = new ArrayList<>();
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			List<ChargeAddress> chargeAddreses, List<CreditCard> creditCard, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = chargeAddreses;
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = creditCard;
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			List<ChargeAddress> chargeAddresses, List<DeliveryAddress> deliveryAddresses, List<CreditCard> creditCard) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = chargeAddresses;
		this.deliveryAddresses = deliveryAddresses;
		this.creditCards = creditCard;
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, 
			User user, List<ChargeAddress> chargeAddresses, List<DeliveryAddress>  deliveryAddresses,
			List<CreditCard> creditCard, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = chargeAddresses;
		this.deliveryAddresses = deliveryAddresses;
		this.creditCards = creditCard;
		this.sales = new ArrayList<>();
		this.exchangeCoupons = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, 
			User user, List<ChargeAddress> chargeAddresses, List<CreditCard> creditCard,
			List<DeliveryAddress> deliveryAddresses, List<Sale> sales, List<PromotionalCoupon> promotionalCoupons, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;

		this.chargeAddresses = chargeAddresses;
		this.deliveryAddresses = deliveryAddresses;
		this.creditCards = creditCard;
		this.sales = sales;
		this.exchangeCoupons = new ArrayList<>();
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

	public ChargeAddress getChargeAddress(int index){
		return this.chargeAddresses.get(index);
	}

	public List<ChargeAddress> getChargeAddresses(){
		return this.chargeAddresses;
	}

	public void setChargeAddress(ChargeAddress chargeAddress){
		this.chargeAddresses.clear();
		this.chargeAddresses.add(chargeAddress);
	}

	public void setChargeAddresses(List<ChargeAddress> chargeAddresses){
		this.chargeAddresses.clear();
		this.chargeAddresses = chargeAddresses;
	}

	public void addChargeAddress(ChargeAddress chargeAddress){
		this.chargeAddresses.add(chargeAddress);
	}

	public void addChargeAddresses(List<ChargeAddress> chargeAddresses){
		for(ChargeAddress chargeAddress : chargeAddresses){
			this.chargeAddresses.add(chargeAddress);
		}
	}

	public int countChargeAddresses(){
		return this.chargeAddresses.size();
	}

	public boolean containsChargeAddress(ChargeAddress chargeAddress){
		return this.chargeAddresses.contains(chargeAddress);
	}

	public void clearChargeAddresses(){
		this.chargeAddresses.clear();
	}

	public boolean chargeAddressesAreEmpty(){
		return this.chargeAddresses.isEmpty();
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

	public List<Sale> getSales() {
		return sales;
	}

	public Sale getSale(int index){
		return this.sales.get(index);
	}
	
	public void setSales(List<Sale> sale) {
		this.sales = sale;
	}

	public List<ExchangeCoupon> getExchangeCoupons() {
		return exchangeCoupons;
	}

	public ExchangeCoupon getExchangeCoupon(int index){
		return this.exchangeCoupons.get(index);
	}
	
	public void setExchangeCoupons(List<ExchangeCoupon> exchangeCoupons) {
		this.exchangeCoupons = exchangeCoupons;
	}

	public void addExchangeCoupons(ExchangeCoupon exchangeCoupon) {
		this.exchangeCoupons.add(exchangeCoupon);
	}

}

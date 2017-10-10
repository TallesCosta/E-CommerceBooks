package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

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
	private Address chargeAddress;
	private List<DeliveryAddress> deliveryAddresses;
	private List<CreditCard> creditCards;

	public Customer() {
		super(true);
		this.deliveryAddresses = new ArrayList<>();
		this.creditCards = new ArrayList<>();
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
		this.creditCards = new ArrayList<>();
		this.creditCards = creditCard;
	}
	
	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, 
			User user, Address homeAddress, Address chargeAddress, List<CreditCard> creditCard, 
			List<DeliveryAddress> deliveryAddresses) {
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
	
	public List<DeliveryAddress> getDeliveryAddress() {
		return this.deliveryAddresses;
	}

	public DeliveryAddress getDeliveryAddress(int index) {
		return this.deliveryAddresses.get(index);
	}
	
	public void setDeliveryAddress(List<DeliveryAddress> deliveryAddress) {
		this.deliveryAddresses = deliveryAddress;
	}
	
	public List<CreditCard> getCreditCard() {
		return this.creditCards;
	}

	public CreditCard getCreditCard(int index) {
		return this.creditCards.get(index);
	}
	
	public void setCreditCard(List<CreditCard> creditCard) {
		this.creditCards = creditCard;
	}
	
}

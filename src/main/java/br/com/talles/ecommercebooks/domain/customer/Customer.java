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
	private List<DeliveryAddress> deliveryAddress;
	private List<ChargeAddress> chargeAddress;
	private List<CreditCard> creditCard;

	public Customer() {
		super(true);
		this.deliveryAddress = new ArrayList<>();
		this.chargeAddress = new ArrayList<>();
		this.creditCard = new ArrayList<>();
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			List<CreditCard> creditCard, List<DeliveryAddress> deliveryAddress, List<ChargeAddress> chargeAddress) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.deliveryAddress = deliveryAddress;
		this.chargeAddress = chargeAddress;
		this.creditCard = creditCard;
	}

	public Customer(String registry, String name, Date birthDate, Gender gender, Phone phone, User user, 
			List<CreditCard> creditCard, List<DeliveryAddress> deliveryAddress, List<ChargeAddress> chargeAddress, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
		this.deliveryAddress = deliveryAddress;
		this.chargeAddress = chargeAddress;
		this.creditCard = creditCard;
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
	
	public List<DeliveryAddress> getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(List<DeliveryAddress> deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<ChargeAddress> getChargeAddress() {
		return chargeAddress;
	}

	public void setChargeAddress(List<ChargeAddress> chargeAddress) {
		this.chargeAddress = chargeAddress;
	}
	
	public List<CreditCard> getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(List<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}
	
}

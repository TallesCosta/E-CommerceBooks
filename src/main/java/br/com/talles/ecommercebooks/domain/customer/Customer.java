package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

import java.util.Date;
import java.util.List;

public class Customer extends Entity {

	private String registry;
	private String name;
	private Date birthDate;
	private List<DeliveryAddress> deliveryAddress;
	private List<CreditCard> creditCard;
	private List<ChargeAddress> chargeAddress;
	private Gender gender;
	private Phone phone;
	private User user;

	public Customer() {
		super(true);
	}

	public Customer(String registry, String name, Date birthDate, List<DeliveryAddress> deliveryAddress, 
			List<CreditCard> creditCard, List<ChargeAddress> chargeAddress, Gender gender, Phone phone, User user) {
		super(true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.deliveryAddress = deliveryAddress;
		this.creditCard = creditCard;
		this.chargeAddress = chargeAddress;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
	}

	public Customer(String registry, String name, Date birthDate, List<DeliveryAddress> deliveryAddress, 
			List<CreditCard> creditCard, List<ChargeAddress> chargeAddress, Gender gender, Phone phone, User user, long id) {
		super(id, true);
		this.registry = registry;
		this.name = name;
		this.birthDate = birthDate;
		this.deliveryAddress = deliveryAddress;
		this.creditCard = creditCard;
		this.chargeAddress = chargeAddress;
		this.gender = gender;
		this.phone = phone;
		this.user = user;
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

	public List<DeliveryAddress> getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(List<DeliveryAddress> deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<CreditCard> getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(List<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}

	public List<ChargeAddress> getChargeAddress() {
		return chargeAddress;
	}

	public void setChargeAddress(List<ChargeAddress> chargeAddress) {
		this.chargeAddress = chargeAddress;
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
	
}

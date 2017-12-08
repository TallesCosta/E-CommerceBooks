package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

import java.util.Objects;

public class Address extends Entity {

	private String alias;
	private String observation;
	private String publicPlaceType;
	private String publicPlace;
	private String number;
	private String district;
	private String postalCode;
	private String homeType;
	private String city;
	private State state;

	public Address() {
		super(true);
	}

	public Address(long id) {
		super(id, true);
	}

	public Address(String alias, long id) {
		super(id, true);
		this.alias = alias;
	}
	
	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, 
			String district, String postalCode, String homeType) {
		super(true);
		this.alias = alias;
		this.observation = observation;
		this.publicPlaceType = publicPlaceType;
		this.publicPlace = publicPlace;
		this.number = number;
		this.district = district;
		this.postalCode = postalCode;
		this.homeType = homeType;
	}
	
	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, 
			String district, String postalCode, String homeType, long id) {
		super(id, true);
		this.alias = alias;
		this.observation = observation;
		this.publicPlaceType = publicPlaceType;
		this.publicPlace = publicPlace;
		this.number = number;
		this.district = district;
		this.postalCode = postalCode;
		this.homeType = homeType;
	}
	
	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, 
			String district, String postalCode, String homeType, String city, State state) {
		super(true);
		this.alias = alias;
		this.observation = observation;
		this.publicPlaceType = publicPlaceType;
		this.publicPlace = publicPlace;
		this.number = number;
		this.district = district;
		this.postalCode = postalCode;
		this.homeType = homeType;
		this.city = city;
		this.state = state;
	}

	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, 
			String district, String postalCode, String homeType, String city, State state, long id) {
		super(id, true);
		this.alias = alias;
		this.observation = observation;
		this.publicPlaceType = publicPlaceType;
		this.publicPlace = publicPlace;
		this.number = number;
		this.district = district;
		this.postalCode = postalCode;
		this.homeType = homeType;
		this.city = city;
		this.state = state;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getHomeType() {
		return homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getPublicPlaceType() {
		return publicPlaceType;
	}

	public void setPublicPlaceType(String publicPlaceType) {
		this.publicPlaceType = publicPlaceType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return alias.equals(address.alias) &&
				getId() == address.getId();
	}

}

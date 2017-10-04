package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Address extends Entity {

	private String alias;
	private String observation;
	private String publicPlaceType;
	private String publicPlace;
	private String number;
	private String district;
	private String postalCode;
	private String homeType;
	private City city;

	public Address() {
		super(true);
	}

	public Address(String alias, long id) {
		super(id, true);
		this.alias = alias;
	}
	
	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, String district, 
			String postalCode, String homeType) {
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
	
	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, String district, 
			String postalCode, String homeType, City city) {
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
	}

	public Address(String alias, String observation, String publicPlaceType, String publicPlace, String number, String district, 
			String postalCode, String homeType, City city, long id) {
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}

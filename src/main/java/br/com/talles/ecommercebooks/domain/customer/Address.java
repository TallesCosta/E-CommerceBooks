package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Address extends Entity {

	private String alias;
	private String observation;
	private String publicPlace;
	private String number;
	private String district;
	private String postalCode;
	private HomeType homeType;
	private PublicPlaceType publicPlaceType;
	private City city;

	public Address() {
		super(true);
	}

	public Address(String alias, String observation, String publicPlace, String number, String district, 
			String postalCode, HomeType homeType, PublicPlaceType publicPlaceType, City city) {
		super(true);
		this.alias = alias;
		this.observation = observation;
		this.publicPlace = publicPlace;
		this.number = number;
		this.district = district;
		this.postalCode = postalCode;
		this.homeType = homeType;
		this.publicPlaceType = publicPlaceType;
		this.city = city;
	}

	public Address(String alias, String observation, String publicPlace, String number, String district, 
			String postalCode, HomeType homeType, PublicPlaceType publicPlaceType, City city, long id) {
		super(id, true);
		this.alias = alias;
		this.observation = observation;
		this.publicPlace = publicPlace;
		this.number = number;
		this.district = district;
		this.postalCode = postalCode;
		this.homeType = homeType;
		this.publicPlaceType = publicPlaceType;
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

	public HomeType getHomeType() {
		return homeType;
	}

	public void setHomeType(HomeType homeType) {
		this.homeType = homeType;
	}

	public PublicPlaceType getPublicPlaceType() {
		return publicPlaceType;
	}

	public void setPublicPlaceType(PublicPlaceType publicPlaceType) {
		this.publicPlaceType = publicPlaceType;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}

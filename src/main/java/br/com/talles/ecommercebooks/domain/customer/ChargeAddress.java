package br.com.talles.ecommercebooks.domain.customer;

public class ChargeAddress extends Address {

	public ChargeAddress() {
	}

	public ChargeAddress(String alias, String observation, String publicPlace, String number, String district, 
			String postalCode, HomeType homeType, PublicPlaceType publicPlaceType, City city) {
		super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city);
	}

	public ChargeAddress(String alias, String observation, String publicPlace, String number, String district, 
			String postalCode, HomeType homeType, PublicPlaceType publicPlaceType, City city, long id) {
		super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city, id);
	}
	
}

package br.com.talles.ecommercebooks.domain.customer;

public class DeliveryAddress extends Address {

	private boolean preferential;

	public DeliveryAddress() {
		super();
	}

	public DeliveryAddress(boolean preferential, String alias, String observation, String publicPlaceType, String publicPlace, 
			String number, String district, String postalCode, String homeType, City city) {
		super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city);
		this.preferential = preferential;
	}

	public DeliveryAddress(boolean preferential, String alias, String observation, String publicPlaceType, String publicPlace, 
			String number, String district, String postalCode, String homeType, City city, long id) {
		super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city, id);
		this.preferential = preferential;
	}

	public boolean isPreferential() {
		return preferential;
	}

	public void setPreferential(boolean preferential) {
		this.preferential = preferential;
	}
	
}

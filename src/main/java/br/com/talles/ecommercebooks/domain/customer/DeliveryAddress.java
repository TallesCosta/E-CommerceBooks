package br.com.talles.ecommercebooks.domain.customer;

public class DeliveryAddress extends Address {

	public DeliveryAddress() {
		super();
	}

	public DeliveryAddress(long id) {
		super(id);
	}

	public DeliveryAddress(String alias, long id) {
		super(alias, id);
	}
	
	public DeliveryAddress(String alias, String observation, String publicPlaceType, String publicPlace,
			String number, String district, String postalCode, String homeType, String city, State state) {
		super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city, state);
	}

	public DeliveryAddress(String alias, String observation, String publicPlaceType, String publicPlace,
			String number, String district, String postalCode, String homeType, String city, State state, long id) {
		super(alias, observation, publicPlace, number, district, postalCode, homeType, publicPlaceType, city, state, id);
	}

}

package br.com.talles.ecommercebooks.domain.customer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeliveryAddress extends Address {

	private boolean preferential;

	public DeliveryAddress() {
		super();
	}

	public DeliveryAddress(long id) {
		super(id);
	}

	public DeliveryAddress(String alias, long id) {
		super(alias, id);
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

	@Override
	public boolean equals(Object obj) {
		if(!obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
			return false;

		DeliveryAddress other = (DeliveryAddress) obj;
		boolean isEqual;

		isEqual = this.preferential == other.isPreferential();
		isEqual = isEqual || this.getAlias().equals( other.getAlias() );
		isEqual = isEqual || this.getObservation().equals( other.getObservation() );
		isEqual = isEqual || this.getPublicPlace().equals( other.getPublicPlace() );
		isEqual = isEqual || this.getPublicPlaceType().equals( other.getPublicPlaceType() );

		isEqual = isEqual || this.getNumber().equals( other.getNumber() );
		isEqual = isEqual || this.getDistrict().equals( other.getDistrict() );
		isEqual = isEqual || this.getPostalCode().equals( other.getPostalCode() );
		isEqual = isEqual || this.getHomeType().equals( other.getHomeType() );
		isEqual = isEqual || this.getCity().getName().equals( other.getCity().getName() );
		isEqual = isEqual || this.getCity().getState().equals( other.getCity().getState() );

		return isEqual;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		if(getAlias() != null)
			hashCode = 31 * hashCode + getAlias().hashCode();

		if(getObservation() != null)
			hashCode = 31 * hashCode + getObservation().hashCode();

		if(getPublicPlace() != null)
			hashCode = 31 * hashCode + getPublicPlace().hashCode();

		if(getPublicPlaceType() != null)
			hashCode = 31 * hashCode + getPublicPlaceType().hashCode();


		if(getNumber() != null)
			hashCode = 31 * hashCode + getNumber().hashCode();

		if(getDistrict() != null)
			hashCode = 31 * hashCode + getDistrict().hashCode();

		if(getPostalCode() != null)
			hashCode = 31 * hashCode + getPostalCode().hashCode();

		if(getHomeType() != null)
			hashCode = 31 * hashCode + getHomeType().hashCode();


		if(getCity() != null && getCity().getName() != null)
			hashCode = 31 * hashCode + getCity().getName().hashCode();

		if(getCity() != null && getCity().getState() != null)
			hashCode = 31 * hashCode + getCity().getState().hashCode();

		return hashCode;
	}
}

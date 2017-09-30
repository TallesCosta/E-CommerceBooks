package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Phone extends Entity {

	private String ddd;
	private String number;
	private PhoneType phoneType;

	public Phone() {
		super(true);
	}

	public Phone(String ddd, String number, PhoneType phoneType) {
		super(true);
		this.ddd = ddd;
		this.number = number;
		this.phoneType = phoneType;
	}

	public Phone(String ddd, String number, PhoneType phoneType, long id) {
		super(id, true);
		this.ddd = ddd;
		this.number = number;
		this.phoneType = phoneType;
	}	
	
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
	
	

}

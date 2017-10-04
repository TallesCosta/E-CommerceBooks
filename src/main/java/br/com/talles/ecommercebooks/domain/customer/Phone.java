package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Phone extends Entity {

	private String ddd;
	private String number;
	private String phoneType;

	public Phone() {
		super(true);
	}

	public Phone(String ddd, String number, String phoneType) {
		super(true);
		this.ddd = ddd;
		this.number = number;
		this.phoneType = phoneType;
	}

	public Phone(String ddd, String number, String phoneType, long id) {
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

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	@Override
	public String toString() {
		return "(" + ddd + ") " + number;
	}

}

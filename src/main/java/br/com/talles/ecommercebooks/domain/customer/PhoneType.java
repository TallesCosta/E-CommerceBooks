package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class PhoneType extends Entity {

	private String name;

	public PhoneType() {
		super(true);
	}
	
	public PhoneType(String name) {
		super(true);
		this.name = name;
	}

	public PhoneType(String name, long id) {
		super(id, true);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

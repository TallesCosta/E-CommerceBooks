package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Country extends Entity {

	private String name;

	public Country() {
		super(true);
	}

	public Country(long id) {
		super(id, true);
	}
	
	public Country(String name) {
		super(true);
		this.name = name;
	}

	public Country(String name, long id) {
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

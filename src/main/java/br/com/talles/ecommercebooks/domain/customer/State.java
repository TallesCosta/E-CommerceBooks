package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class State extends Entity {

	private String name;
	private Country country;

	public State() {
		super(true);
	}

	public State(String name, Country country) {
		super(true);
		this.name = name;
		this.country = country;
	}

	public State(String name, Country country, long id) {
		super(id, true);
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}

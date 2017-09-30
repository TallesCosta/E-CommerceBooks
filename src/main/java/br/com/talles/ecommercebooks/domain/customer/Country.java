package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Country extends Entity {

	private String name;
	private State state;

	public Country() {
		super(true);
	}

	public Country(String name, State state) {
		super(true);
		this.name = name;
		this.state = state;
	}

	public Country(String name, State state, long id) {
		super(id, true);
		this.name = name;
		this.state = state;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}

package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class City extends Entity {

	private String name;
	private State state;

	public City() {
		super(true);
	}

	public City(long id) {
		super(id, true);
	}

	public City(long id, State state) {
		super(id);
		this.state = state;
	}
	
	public City(String name, State state) {
		super(true);
		this.name = name;
		this.state = state;
	}

	public City(String name, State state, long id) {
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

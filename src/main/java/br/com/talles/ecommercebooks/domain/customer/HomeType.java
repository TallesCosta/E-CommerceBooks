package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class HomeType extends Entity {

	private String name;

	public HomeType() {
		super(true);
	}
	
	public HomeType(String name) {
		super(true);
		this.name = name;
	}

	public HomeType(String name, long id) {
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

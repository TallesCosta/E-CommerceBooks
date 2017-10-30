package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;

public class Status extends Entity {

	// Attributes
	private String name;

	// Constructors
	public Status() { }

	public Status(String name) {
		this.name = name;
	}

	// Getters
	public String getName() {
		return name;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}



}

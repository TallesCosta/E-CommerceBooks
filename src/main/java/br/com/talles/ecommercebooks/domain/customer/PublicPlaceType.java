package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class PublicPlaceType extends Entity {

	private String name;

	public PublicPlaceType() {
		super(true);
	}
	
	public PublicPlaceType(String name) {
		super(true);
		this.name = name;
	}

	public PublicPlaceType(String name, long id) {
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

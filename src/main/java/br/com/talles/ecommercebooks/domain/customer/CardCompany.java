package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class CardCompany extends Entity {

	private String code;
	private String name;

	public CardCompany() {
		super(true);
	}

	public CardCompany(long id) {
		super(id, true);
	}

	public CardCompany(String name) {
		this.name = name;
	}

	public CardCompany(String code, String name) {
		super(true);
		this.code = code;
		this.name = name;
	}

	public CardCompany(String code, String name, long id) {
		super(id, true);
		this.code = code;
		this.name = name;
	}
	
	public String getNumberId() {
		return code;
	}

	public void setNumberId(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

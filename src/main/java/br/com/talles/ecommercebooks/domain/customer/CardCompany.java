package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class CardCompany extends Entity {

	private String numberId;
	private String name;

	public CardCompany() {
		super(true);
	}

	public CardCompany(String numberId, String name) {
		super(true);
		this.numberId = numberId;
		this.name = name;
	}

	public CardCompany(String numberId, String name, long id) {
		super(id, true);
		this.numberId = numberId;
		this.name = name;
	}
	
	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

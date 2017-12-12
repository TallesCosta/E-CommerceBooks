package br.com.talles.ecommercebooks.domain.customer;

import br.com.talles.ecommercebooks.domain.Entity;

public class Gender extends Entity {

	private String name;

	public Gender() {
		super(true);
	}

	public Gender(String name) {
		super(true);
		this.name = name;
	}

	public Gender(String name, long id) {
		super(id, true);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Gender)) return false;
		Gender gender = (Gender) o;
		return this.name.equals(gender.name);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}

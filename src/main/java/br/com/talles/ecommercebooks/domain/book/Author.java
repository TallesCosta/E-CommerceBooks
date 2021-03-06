package br.com.talles.ecommercebooks.domain.book;	

import br.com.talles.ecommercebooks.domain.Entity;

public class Author extends Entity {

	private String name;

	public Author() {
		super(true);
	}

	public Author(long id) {
		super(id, true);
	}
	
	public Author(String name) {
		this.name = name;
	}

	public Author(String name, long id) {
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

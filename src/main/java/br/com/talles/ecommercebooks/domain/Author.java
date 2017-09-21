package br.com.talles.ecommercebooks.domain;	

public class Author extends Entity {

	private String name;

	public Author() {
	}

	public Author(String name) {
		this.name = name;
	}

	public Author(String name, long id, boolean enabled) {
		super(id, enabled);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

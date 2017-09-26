package br.com.talles.ecommercebooks.domain;

public class Category extends AbstractCategory {

	public Category() {
	}

	public Category(long id) {
		super(id);
	}
	
	public Category(String name){
		super(name);
	}
	
	public Category(String name, String description) {
		super(name, description);
	}

	public Category(long id, String name, String description) {
		super(id, name, description);
	}
	
}

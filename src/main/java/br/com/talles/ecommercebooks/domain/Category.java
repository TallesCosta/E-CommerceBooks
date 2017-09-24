package br.com.talles.ecommercebooks.domain;

public class Category extends AbstractCategory {

	public Category() {
	}

	public Category(long id) {
		super(id);
	}
	
	public Category(String name, String description) {
		super(name, description);
	}

	public Category(String name, String description, long id, boolean enabled) {
		super(name, description, id, enabled);
	}
	
}

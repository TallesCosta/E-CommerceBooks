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
	
	public Category(long id, String name){
		super(id, name);
	}
			
	public Category(String name, String description) {
		super(name, description);
	}

	public Category(long id, boolean enabled, String name, String description) {
		super(id, enabled, name, description);
	}
	
}

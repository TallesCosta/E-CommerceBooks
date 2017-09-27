package br.com.talles.ecommercebooks.domain;

public class StatusCategory extends AbstractCategory {
	
	// All constructors in the AbstractCategory makes enabled attribute as true
	public StatusCategory() {
	}

	public StatusCategory(long id) {
		super(id);
	}

	public StatusCategory(boolean enabled) {
		super(enabled);
	}
	
	public StatusCategory(String name) {
		super(name);
	}
	
	public StatusCategory(String name, String description) {
		super(name, description);
	}
	
	public StatusCategory(long id, String name, String description) {
		super(id, name, description);
	}
	
}

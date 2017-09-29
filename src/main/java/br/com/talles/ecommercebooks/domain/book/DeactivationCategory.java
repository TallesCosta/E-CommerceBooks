package br.com.talles.ecommercebooks.domain.book;

public class DeactivationCategory extends StatusCategory {
	
	// All constructors in the StatusCategory makes enabled attribute as true
	public DeactivationCategory() {
	}

	public DeactivationCategory(long id) {
		super(id);
	}
	
	public DeactivationCategory(String name) {
		super(name);
	}
	
	public DeactivationCategory(String name, String description) {
		super(name, description);
	}
	
	public DeactivationCategory(long id, String name, String description) {
		super(id, name, description);
	}
	
}

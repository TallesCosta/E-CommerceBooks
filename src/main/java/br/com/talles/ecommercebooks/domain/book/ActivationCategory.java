package br.com.talles.ecommercebooks.domain.book;

public class ActivationCategory extends StatusCategory {
	
	// All constructors in the StatusCategory makes enabled attribute as true
	public ActivationCategory() {
	}

	public ActivationCategory(long id) {
		super(id);
	}
	
	public ActivationCategory(String name) {
		super(name);
	}
	
	public ActivationCategory(String name, String description) {
		super(name, description);
	}
	
	public ActivationCategory(long id, String name, String description) {
		super(id, name, description);
	}
	
}

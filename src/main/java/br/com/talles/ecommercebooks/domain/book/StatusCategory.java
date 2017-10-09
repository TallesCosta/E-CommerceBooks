package br.com.talles.ecommercebooks.domain.book;

public class StatusCategory extends AbstractCategory {
	
	private boolean activationCategory;
	
	// All constructors in the AbstractCategory makes enabled attribute as true
	public StatusCategory() {
	}

	public StatusCategory(long id) {
		super(id);
	}
	
	public StatusCategory(boolean activationCategory) {
		super(true);
		this.activationCategory = activationCategory;
	}
	
	public StatusCategory(String name, boolean activationCategory) {
		super(name);
		this.activationCategory = activationCategory;
	}
	
	public StatusCategory(String name, String description, boolean activationCategory) {
		super(name, description);
		this.activationCategory = activationCategory;
	}
	
	public StatusCategory(long id, String name, String description, boolean activationCategory) {
		super(id, name, description);
		this.activationCategory = activationCategory;
	}

	public boolean isActivationCategory() {
		return activationCategory;
	}

	public void setActivationCategory(boolean activationCategory) {
		this.activationCategory = activationCategory;
	}
	
}

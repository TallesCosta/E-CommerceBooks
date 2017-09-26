package br.com.talles.ecommercebooks.domain;

public class StatusCategory extends AbstractCategory {

	private boolean activation;
	
	// All constructors in the AbstractCategory makes enabled attribute as true
	public StatusCategory() {
	}

	public StatusCategory(String name, boolean activation) {
		super(name);
		this.activation = activation;
	}
	
	public StatusCategory(String name, String description, boolean  activation) {
		super(name, description);
		this.activation = activation;
	}
	
	public StatusCategory(long id, String name, String description, boolean activation) {
		super(id, name, description);
		this.activation = activation;
	}

	public boolean isActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}
	
}

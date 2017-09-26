package br.com.talles.ecommercebooks.domain;

public abstract class AbstractCategory extends Entity {

	private String name;
	private String description;

	public AbstractCategory() {
	}

	public AbstractCategory(long id) {
		super(id);
	}

	public AbstractCategory(String name) {
		this.name = name;
	}
	
	public AbstractCategory(long id, String name) {
		super(id);
		this.name = name;
	}
	
	public AbstractCategory(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public AbstractCategory(long id, boolean enabled, String name, String description) {
		super(id, enabled);
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

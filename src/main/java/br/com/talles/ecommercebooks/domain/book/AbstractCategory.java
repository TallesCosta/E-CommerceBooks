package br.com.talles.ecommercebooks.domain.book;

import br.com.talles.ecommercebooks.domain.Entity;

public abstract class AbstractCategory extends Entity {

	private String name;
	private String description;

	public AbstractCategory() {
		super(true);
	}

	public AbstractCategory(long id) {
		super(id, true);
	}

	public AbstractCategory(boolean enabled) {
		super(enabled);
	}
	
	public AbstractCategory(String name) {
		super(true);
		this.name = name;
	}
	
	public AbstractCategory(String name, String description) {
		super(true);
		this.name = name;
		this.description = description;
	}

	public AbstractCategory(long id, String name, String description) {
		super(id, true);
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

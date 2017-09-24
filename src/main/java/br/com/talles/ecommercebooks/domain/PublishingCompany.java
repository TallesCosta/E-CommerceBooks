package br.com.talles.ecommercebooks.domain;

public class PublishingCompany extends Entity {

	private String name;

	public PublishingCompany() {
	}
	
	public PublishingCompany(long id) {
		super(id);
	}

	public PublishingCompany(String name, long id, boolean enabled) {
		super(id, enabled);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

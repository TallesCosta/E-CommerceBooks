package br.com.talles.ecommercebooks.domain;

public class PublishingCompany extends Entity {

	private String name;

	public PublishingCompany() {
		super(true);
	}

	public PublishingCompany(long id) {
		super(id, true);
	}
	
	public PublishingCompany(String name) {
		super(true);
		this.name = name;
	}

	public PublishingCompany(String name, long id) {
		super(id, true);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

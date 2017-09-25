package br.com.talles.ecommercebooks.domain;

public class ChangeStatus extends AbstractCategory {

	private String justification;

	public ChangeStatus() {
	}

	public ChangeStatus(String justification) {
		this.justification = justification;
	}

	public ChangeStatus(String justification, String name, String description) {
		super(name, description);
		this.justification = justification;
	}

	
	
	public ChangeStatus(long id, boolean enabled, String justification, String name, String description) {
		super(id, enabled, name, description);
		this.justification = justification;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}	

}

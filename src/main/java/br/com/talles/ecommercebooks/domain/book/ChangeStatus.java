package br.com.talles.ecommercebooks.domain.book;

import br.com.talles.ecommercebooks.domain.Entity;

public class ChangeStatus extends Entity {
	
	private String justification;
	private StatusCategory statusCategory;

	public ChangeStatus() {
		super(true);
	}

	public ChangeStatus(long id) {
		super(id, true);
	}
	
	public ChangeStatus(String justification, StatusCategory statusCategory) {
		super(true);
		this.justification = justification;
		this.statusCategory = statusCategory;
	}
	
	public ChangeStatus(String justification, StatusCategory statusCategory, long id) {
		super(id, true);
		this.justification = justification;
		this.statusCategory = statusCategory;
	}
	
	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public StatusCategory getStatusCategory() {
		return statusCategory;
	}

	public void setStatusCategory(StatusCategory statusCategory) {
		this.statusCategory = statusCategory;
	}
	
}

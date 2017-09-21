package br.com.talles.ecommercebooks.domain;

public class PriceGroup extends Entity {

	private double markup;

	public PriceGroup() {
	}

	public PriceGroup(double markup) {
		this.markup = markup;
	}

	public PriceGroup(double markup, long id, boolean enabled) {
		super(id, enabled);
		this.markup = markup;
	}

	public double getMarkup() {
		return markup;
	}

	public void setMarkup(double markup) {
		this.markup = markup;
	}
	
}

package br.com.talles.ecommercebooks.domain;

public class PriceGroup extends Entity {

	private double markup;

	public PriceGroup() {
		super(true);
	}

	public PriceGroup(long id) {
		super(id, true);
	}
	
	public PriceGroup(double markup) {
		super(true);
		this.markup = markup;
	}

	public PriceGroup(double markup, long id) {
		super(id, true);
		this.markup = markup;
	}

	public double getMarkup() {
		return markup;
	}

	public void setMarkup(double markup) {
		this.markup = markup;
	}
	
}

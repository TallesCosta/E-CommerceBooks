package br.com.talles.ecommercebooks.domain;

public class SaleParameterization extends Entity {

	private int minSaleLimit;
	private int periodicity;

	public SaleParameterization() {
	}

	public SaleParameterization(int minSaleLimit, int periodicity) {
		this.minSaleLimit = minSaleLimit;
		this.periodicity = periodicity;
	}

	public SaleParameterization(int minSaleLimit, int periodicity, long id, boolean enabled) {
		super(id, enabled);
		this.minSaleLimit = minSaleLimit;
		this.periodicity = periodicity;
	}
	
	public int getMinSaleLimit() {
		return minSaleLimit;
	}

	public void setMinSaleLimit(int minSaleLimit) {
		this.minSaleLimit = minSaleLimit;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}
	
}
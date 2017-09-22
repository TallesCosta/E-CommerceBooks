package br.com.talles.ecommercebooks.domain;

public class Dimension extends Entity {

	private double height;
	private double widht;
	private double weight;
	private double depth;

	public Dimension() {
	}

	public Dimension(double height, double widht, double weight, double depth) {
		this.height = height;
		this.widht = widht;
		this.weight = weight;
		this.depth = depth;
	}

	public Dimension(double height, double widht, double weight, double depth, long id, boolean enabled) {
		super(id, enabled);
		this.height = height;
		this.widht = widht;
		this.weight = weight;
		this.depth = depth;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidht() {
		return widht;
	}

	public void setWidht(double widht) {
		this.widht = widht;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}
	
}
package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;

public class ShippingCost extends Entity {

    private double value;
    private double baseValue;
    private double baseAddtionValue;

    public ShippingCost() {
        super(true);
    }

    public ShippingCost(double value) {
        super(true);
        this.value = value;
    }

    public ShippingCost(double baseValue, double baseAddtionValue) {
        this.baseValue = baseValue;
        this.baseAddtionValue = baseAddtionValue;
    }

    public ShippingCost(long id, double value, double baseValue, double baseAddtionValue) {
        super(id, true);
        this.value = value;
        this.baseValue = baseValue;
        this.baseAddtionValue = baseAddtionValue;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getBaseAddtionValue() {
        return baseAddtionValue;
    }

    // Setters
    public void setValue(double value) {
        this.value = value;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public void setBaseAddtionValue(double baseAddtionValue) {
        this.baseAddtionValue = baseAddtionValue;
    }

}

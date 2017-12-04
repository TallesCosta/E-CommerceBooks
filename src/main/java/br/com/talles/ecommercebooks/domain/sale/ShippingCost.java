package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;

public class ShippingCost extends Entity {

    private double value;

    public ShippingCost() {
    }

    public ShippingCost(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}

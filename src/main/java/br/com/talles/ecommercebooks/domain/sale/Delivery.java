package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;
import br.com.talles.ecommercebooks.domain.customer.DeliveryAddress;

import java.util.Date;

public class Delivery extends Entity {

    private Date deliveryForecast;
    private ShippingCost shippingCost;
    private DeliveryAddress deliveryAddress;

    // Constructors
    public Delivery(){
        super(true);
    }

    public Delivery(Date deliveryForecast) {
        super(true);
        this.deliveryForecast = deliveryForecast;
    }

    public Delivery(ShippingCost shippingCost) {
        super(true);
        this.shippingCost = shippingCost;
    }

    public Delivery(DeliveryAddress deliveryAddress) {
        super(true);
        this.deliveryAddress = deliveryAddress;
    }

    public Delivery(ShippingCost shippingCost, DeliveryAddress deliveryAddress) {
        super(true);
        this.shippingCost = shippingCost;
        this.deliveryAddress = deliveryAddress;
    }

    public Delivery(Date deliveryForecast, ShippingCost shippingCost, DeliveryAddress deliveryAddress) {
        super(true);
        this.deliveryForecast = deliveryForecast;
        this.shippingCost = shippingCost;
        this.deliveryAddress = deliveryAddress;
    }

    public Delivery(long id, Date deliveryForecast, ShippingCost shippingCost, DeliveryAddress deliveryAddress) {
        super(id, true);
        this.deliveryForecast = deliveryForecast;
        this.shippingCost = shippingCost;
        this.deliveryAddress = deliveryAddress;
    }

    // Getters
    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public ShippingCost getShippingCost() {
        return shippingCost;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    // Setters
    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }

    public void setShippingCost(ShippingCost shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

}

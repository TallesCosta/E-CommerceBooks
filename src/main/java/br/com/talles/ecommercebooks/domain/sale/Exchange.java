package br.com.talles.ecommercebooks.domain.sale;

import br.com.talles.ecommercebooks.domain.Entity;

public class Exchange extends Entity {

    private Boolean accepted;
    private String justification;
    private String destination;
    private Order order;

    public Exchange() {
        super(true);
    }

    public Exchange(Boolean accepted) {
        this.accepted = accepted;
    }

    public Exchange(String justification) {
        super(true);
        this.justification = justification;
        this.accepted = false;
    }

    public Exchange(Order order) {
        this.order = order;
    }

    public Exchange(Boolean accepted, String justification) {
        this.accepted = accepted;
        this.justification = justification;
    }

    public Exchange(long id, Boolean accepted, String justification, Order order) {
        super(id, true);
        this.justification = justification;
        this.accepted = accepted;
        this.order = order;
    }

    // Getters
    public Boolean isAccepted() {
        return accepted;
    }

    public String getJustification() {
        return justification;
    }

    public String getDestination() {
        return destination;
    }

    public Order getOrder() {
        return order;
    }

    // Setters
    public void setJustification(String justification) {
        this.justification = justification;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}

package main.java.moneytracker.model;

import main.java.moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.HashMap;
import java.util.Map;

public class Ticket {

    private String paidBy, createdBy;
    private PaymentStrategiesEnum paymentStrategy;
    private final Map<Person, Float> costPerPerson = new HashMap<>();

    public Ticket(String paidBy, String createdBy, PaymentStrategiesEnum paymentStrategy) {
        this.paidBy = paidBy;
        this.createdBy = createdBy;
        this.paymentStrategy = paymentStrategy;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public PaymentStrategiesEnum getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategiesEnum paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Map<Person, Float> getCostPerPerson() {
        return costPerPerson;
    }

    public void addCostPerPerson(Person person, Float cost) {
        // TODO: Check if person already exists

        costPerPerson.put(person, cost);
    }

    public float getTotal() {
        float total = 0f;
        for (Float cost : costPerPerson.values()) {
            total += cost;
        }
        return total;
    }
}

package main.java.moneytracker.model;

import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.model.strategies.PaymentStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Ticket {

    private final UUID id;
    private Person paidBy, createdBy;
    private PaymentStrategiesEnum paymentStrategy;
    private TicketTypeEnum ticketType;
    private Map<Person, Float> costPerPerson = new HashMap<>();

    public Ticket(Person paidBy, Person createdBy, TicketTypeEnum ticketType, PaymentStrategiesEnum paymentStrategyEnum, Map<Person, Float> costPerPerson, float total) throws IllegalArgumentException {
        this.id = UUID.randomUUID();
        this.paidBy = paidBy;
        this.createdBy = createdBy;
        this.ticketType = ticketType;
        this.paymentStrategy = paymentStrategyEnum;

        // Execute the payment strategy
        try {
            PaymentStrategy strategy = paymentStrategyEnum.getStrategyClass().newInstance();
            this.costPerPerson = strategy.pay(costPerPerson, total);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public UUID getId() {
        return id;
    }

    public Person getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
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

    public float getTotal() {
        float total = 0f;

        for (Float cost : costPerPerson.values()) {
            total += cost;
        }

        return total;
    }

}

package main.java.moneytracker.model.tickets;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.Map;
import java.util.UUID;

public abstract class Ticket {

    private final UUID id;
    private Person paidBy;
    private PaymentStrategiesEnum paymentStrategy;

    //private TicketParameters parameters;

    public Ticket(Person paidBy, PaymentStrategiesEnum paymentStrategy) throws IllegalArgumentException {
        this.id = UUID.randomUUID();
        this.paidBy = paidBy;
        this.paymentStrategy = paymentStrategy;
    }

    public UUID getId() {
        return id;
    }

    public Person getPaidBy() {
        return paidBy;
    }

    public Ticket setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
        return this;
    }

    public PaymentStrategiesEnum getPaymentStrategy() {
        return paymentStrategy;
    }

    public Ticket setPaymentStrategy(PaymentStrategiesEnum paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        return this;
    }

    public abstract Map<Person, Float> getCostPerPerson();

    public abstract float getTotal();
}

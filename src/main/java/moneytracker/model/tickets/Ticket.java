package main.java.moneytracker.model.tickets;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;

import java.util.Map;
import java.util.UUID;

public abstract class Ticket {

    private final UUID id;
    private Person paidBy, createdBy;
    private PaymentStrategiesEnum paymentStrategy;

    public Ticket(Person paidBy, Person createdBy, PaymentStrategiesEnum paymentStrategy) throws IllegalArgumentException {
        this.id = UUID.randomUUID();
        this.paidBy = paidBy;
        this.createdBy = createdBy;
        this.paymentStrategy = paymentStrategy;
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

    public abstract Map<Person, Float> getCostPerPerson();

    public abstract float getTotal();
}

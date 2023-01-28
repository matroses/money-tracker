package moneytracker.model.tickets;

import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Ticket {

    private final UUID id;
    private Person paidBy;
    private PaymentStrategiesEnum paymentStrategy;

    private final Map<Person, Float> owedPerPerson = new HashMap<>();

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

    public void updateOwedPerPerson() {
        owedPerPerson.clear();

        // Retrieve the cost per person
        Map<Person, Float> costPerPerson = getCostPerPerson();

        try {
            owedPerPerson.putAll(
                    getPaymentStrategy().getStrategyClass().getDeclaredConstructor().newInstance().pay(costPerPerson, getTotal())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PaymentStrategiesEnum getPaymentStrategy() {
        return paymentStrategy;
    }

    public Ticket setPaymentStrategy(PaymentStrategiesEnum paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        return this;
    }

    public Map<Person, Float> getOwedPerPerson() {
        return owedPerPerson;
    }

    public abstract Map<Person, Float> getCostPerPerson();

    public abstract float getTotal();
}

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

    public Ticket(Person paidBy, PaymentStrategiesEnum paymentStrategy) throws IllegalArgumentException {
        this.id = UUID.randomUUID();
        setPaidBy(paidBy);
        setPaymentStrategy(paymentStrategy);
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

    public float getTotal() {
        Map<Person, Float> costPerPerson = getCostPerPerson();

        float total = 0;
        for (Float cost : costPerPerson.values()) {
            total += cost;
        }

        return total;
    }
}

package main.java.moneytracker.model.tickets;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;

import java.util.Map;

/**
 * Generic ticket class, used for all types of tickets.
 * @author Sigfried
 */
public class GenericTicket extends Ticket {
    private final Map<Person, Float> costPerPerson;

    public GenericTicket(Person paidBy, Person createdBy, TicketTypeEnum ticketType, PaymentStrategiesEnum paymentStrategyEnum, Map<Person, Float> costPerPerson) throws IllegalArgumentException {
        super(paidBy, createdBy, ticketType, paymentStrategyEnum);
        this.costPerPerson = costPerPerson;
    }

    @Override
    public Map<Person, Float> getCostPerPerson() {
        return costPerPerson;
    }

    @Override
    public float getTotal() {
        float total = 0;

        for (Float cost : costPerPerson.values()) {
            total += cost;
        }

        return total;
    }
}

package moneytracker.model.tickets;

import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;
import moneytracker.model.enums.TicketTypeEnum;

import java.util.Map;

/**
 * Generic ticket class, used for all types of tickets.
 * @author Sigfried
 */
public class GenericTicket extends Ticket {
    private Map<Person, Float> costPerPerson;

    public GenericTicket(Person paidBy, PaymentStrategiesEnum paymentStrategyEnum) throws IllegalArgumentException {
        super(paidBy, paymentStrategyEnum);
    }

    public GenericTicket setCostPerPerson(Map<Person, Float> costPerPerson) {
        this.costPerPerson = costPerPerson;
        return this;
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

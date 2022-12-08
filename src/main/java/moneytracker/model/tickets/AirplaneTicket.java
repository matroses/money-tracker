package main.java.moneytracker.model.tickets;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;

import java.util.Map;

public class AirplaneTicket extends Ticket {

    private Map<Person, Float> seatPricePerPerson, baggagePricePerPerson, foodPricePerPerson, otherCostsPerPerson;

    public AirplaneTicket(Person paidBy, Person createdBy, PaymentStrategiesEnum paymentStrategyEnum) throws IllegalArgumentException {
        super(paidBy, createdBy, paymentStrategyEnum);
    }

    @Override
    public Map<Person, Float> getCostPerPerson() {
        return null;
    }

    @Override
    public float getTotal() {
        return 0;
    }
}

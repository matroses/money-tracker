package main.java.moneytracker.model.factories;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.model.strategies.PaymentStrategy;

import java.util.Map;

public class TicketFactory {

    public static Ticket createTicket(Person paidBy, Person createdBy, TicketTypeEnum ticketType, Map<Person, Float> enteredCostPerPerson, float amount, PaymentStrategiesEnum strategiesEnum) throws InstantiationException, IllegalAccessException {

        // Create an instance of the payment strategy
        PaymentStrategy strategy = strategiesEnum.getStrategyClass().newInstance();

        // Calculate the actual cost per person
        Map<Person, Float> costPerPerson = strategy.pay(enteredCostPerPerson, amount);

        // Create a new ticket
        return new Ticket(paidBy, createdBy, ticketType, strategiesEnum, costPerPerson);
    }

}

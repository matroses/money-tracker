package moneytracker.model.factories;

import moneytracker.model.Person;
import moneytracker.model.tickets.Ticket;
import moneytracker.model.enums.PaymentStrategiesEnum;
import moneytracker.model.enums.TicketTypeEnum;
import moneytracker.model.strategies.PaymentStrategy;

import java.util.Map;

public class TicketFactory {

    public static Ticket createTicket(Person paidBy, Person createdBy, TicketTypeEnum ticketType, Map<Person, Float> enteredCostPerPerson, float amount, PaymentStrategiesEnum strategiesEnum) throws InstantiationException, IllegalAccessException {

        // Create an instance of the payment strategy
        PaymentStrategy strategy = strategiesEnum.getStrategyClass().newInstance();

        // Calculate the actual cost per person
        Map<Person, Float> costPerPerson = strategy.pay(enteredCostPerPerson, amount);

        return null;

        // Create a new ticket
        //return new Ticket(paidBy, createdBy, ticketType, strategiesEnum, costPerPerson);
    }

}

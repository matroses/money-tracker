package moneytracker.controller.tickets;

import moneytracker.controller.Controller;
import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;
import moneytracker.model.tickets.AirplaneTicket;
import moneytracker.model.tickets.GenericTicket;
import moneytracker.model.tickets.Ticket;

import java.util.List;
import java.util.Map;

public class CreateTicketController extends Controller {

    public CreateTicketController(MoneyTrackerApp moneyTracker) {
        super(moneyTracker);
    }

    public List<Person> getPeople() {
        return app.getPeopleDB().getActivePeople();
    }

    /**
     * Creates a new airplane ticket
     * @param paidBy The person who paid for the ticket
     * @param paymentStrategyEnum The payment strategy
     * @param seatPricePerPerson The seat price per person
     * @param baggagePricePerPerson Additional baggage price per person
     * @param foodPricePerPerson Food price per person
     * @param otherCostsPerPerson Other costs per person
     * @return The created Airplane Ticket
     */
    public AirplaneTicket createAirplaneTicket(
            Person paidBy,
            PaymentStrategiesEnum paymentStrategyEnum,
            Map<Person, Float> seatPricePerPerson, Map<Person, Float> baggagePricePerPerson,
            Map<Person, Float> foodPricePerPerson, Map<Person, Float> otherCostsPerPerson
    ) {
        AirplaneTicket ticket = new AirplaneTicket(paidBy, paymentStrategyEnum);

        if (!seatPricePerPerson.isEmpty()) {
            ticket = ticket.setSeatPricePerPerson(seatPricePerPerson);
        }

        if (!baggagePricePerPerson.isEmpty()) {
            ticket = ticket.setBaggagePricePerPerson(baggagePricePerPerson);
        }

        if (!foodPricePerPerson.isEmpty()) {
            ticket = ticket.setFoodPricePerPerson(foodPricePerPerson);
        }

        if (!otherCostsPerPerson.isEmpty()) {
            ticket = ticket.setOtherCostsPerPerson(otherCostsPerPerson);
        }

        addTicket(ticket);

        return ticket;
    }

    public GenericTicket createGenericTicket(Person paidByPerson, PaymentStrategiesEnum paymentStrategyEnum, Map<Person, Float> costPerPerson) {
        GenericTicket ticket = new GenericTicket(paidByPerson, paymentStrategyEnum);

        ticket.setCostPerPerson(costPerPerson);

        addTicket(ticket);

        return ticket;
    }

    public void addTicket(Ticket ticket) {
        app.getTicketDB().addTicket(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        app.getTicketDB().deleteTicket(ticket);
    }
}

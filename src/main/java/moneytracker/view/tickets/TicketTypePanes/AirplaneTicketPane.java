package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.tickets.AirplaneTicket;
import main.java.moneytracker.model.tickets.Ticket;

import java.util.*;

public class AirplaneTicketPane extends TicketTypePane {

    public AirplaneTicketPane(CreateTicketController controller, Ticket ticket) {
        super(controller, ticket);
    }

    @Override
    public void update() {

    }

    @Override
    protected Map<String, Map<Person, Float>> getTicketValues() {
        AirplaneTicket ticket = (AirplaneTicket) this.ticket;
        Map<String, Map<Person, Float>> values = new LinkedHashMap<>();

        values.put("Seat price", ticket.getSeatPricePerPerson());
        values.put("Baggage price", ticket.getBaggagePricePerPerson());
        values.put("Food price", ticket.getFoodPricePerPerson());
        values.put("Other price", ticket.getOtherCostsPerPerson());

        return values;
    }

    public void addFieldsForNewPerson(Person person) {
        if (fieldsPerPerson.containsKey(person)) {
            return;
        }

        Map<String, Control> fields = new LinkedHashMap<>();
        fields.put("Seat price", new TextField());
        fields.put("Baggage price", new TextField());
        fields.put("Food price", new TextField());
        fields.put("Other costs", new TextField());
        fieldsPerPerson.put(person, fields);

        this.renderFields();
    }

    @Override
    protected void saveTicket() {
        // TODO: Implement
    }

    @Override
    protected void verifyField(String fieldName, Control field, Person person) {
        defaultCostFieldVerification(fieldName, field, person);
    }
}

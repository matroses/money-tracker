package moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.model.Person;
import moneytracker.model.tickets.AirplaneTicket;
import moneytracker.model.tickets.Ticket;

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

    public void addFieldsForNewPerson(Person person, boolean renderFields) {
        if (fieldsPerPerson.containsKey(person)) {
            return;
        }

        Map<String, Control> fields = new LinkedHashMap<>();
        fields.put("Seat price", new TextField());
        fields.put("Baggage price", new TextField());
        fields.put("Food price", new TextField());
        fields.put("Other costs", new TextField());
        fieldsPerPerson.put(person, fields);

        if (renderFields) {
            this.renderFields();
        }
    }

    @Override
    protected Ticket createTicket(Map<String, Map<Person, Float>> values) {
        return this.controller.createAirplaneTicket(
                this.paidByPerson, this.getSelectedStrategy(),
                values.get("Seat price"),
                values.get("Baggage price"),
                values.get("Food price"),
                values.get("Other costs")
        );
    }

    @Override
    protected void verifyField(String fieldName, Control field, Person person) {
        defaultCostFieldVerification(fieldName, field, person);
    }
}

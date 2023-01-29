package moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.model.Person;
import moneytracker.model.tickets.AirplaneTicket;
import moneytracker.model.tickets.RestaurantTicket;
import moneytracker.model.tickets.Ticket;

import java.util.LinkedHashMap;
import java.util.Map;

public class RestaurantTicketPane extends TicketTypePane {

    public RestaurantTicketPane(CreateTicketController controller, Ticket ticket) {
        super(controller, ticket);
    }

    @Override
    public void update() {

    }

    @Override
    protected Map<String, Map<Person, Float>> getTicketValues() {
        RestaurantTicket ticket = (RestaurantTicket) this.ticket;
        Map<String, Map<Person, Float>> values = new LinkedHashMap<>();

        values.put("Food price", ticket.getFoodPricePerPerson());
        values.put("Beverage price", ticket.getBeveragePricePerPerson());
        values.put("Other costs", ticket.getOtherCostsPerPerson());

        return values;
    }

    public void addFieldsForNewPerson(Person person, boolean renderFields) {
        if (fieldsPerPerson.containsKey(person)) {
            return;
        }

        Map<String, Control> fields = new LinkedHashMap<>();
        fields.put("Food price", new TextField());
        fields.put("Beverage costs", new TextField());
        fieldsPerPerson.put(person, fields);

        if (renderFields) {
            this.renderFields();
        }
    }

    @Override
    protected Ticket createTicket(Map<String, Map<Person, Float>> values) {
        return this.controller.createRestaurantTicket(
                this.paidByPerson, this.getSelectedStrategy(),
                values.get("Food price"),
                values.get("Beverage costs")
        );
    }

    @Override
    protected void verifyField(String fieldName, Control field, Person person) {
        defaultCostFieldVerification(fieldName, field, person);
    }
}

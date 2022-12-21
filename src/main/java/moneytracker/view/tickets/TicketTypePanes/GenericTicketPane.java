package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.tickets.GenericTicket;
import main.java.moneytracker.model.tickets.Ticket;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GenericTicketPane extends TicketTypePane {

    public GenericTicketPane(CreateTicketController controller, Ticket ticket) {
        super(controller, ticket);
    }

    @Override
    public void update() {

    }

    public void addFieldsForNewPerson(Person person) {
        if (fieldsPerPerson.containsKey(person)) {
            return;
        }

        Map<String, Control> fields = new LinkedHashMap<>();
        fields.put("Cost", new TextField());
        fieldsPerPerson.put(person, fields);

        this.renderFields();
    }

    @Override
    protected Map<String, Map<Person, Float>> getTicketValues() {
        GenericTicket ticket = (GenericTicket) this.ticket;
        Map<String, Map<Person, Float>> values = new LinkedHashMap<>();

        values.put("Cost", ticket.getCostPerPerson());

        return values;
    }

    @Override
    protected void saveTicket() {
        if (!this.verifyFields()) {
            this.renderFields();
            return;
        }

        // Get values from fields
        Map<String, Map<Person, Float>> values = this.getValuesPerField();

        this.ticket = controller.createGenericTicket(
            this.paidByPerson, this.getSelectedStrategy(), values.get("Cost")
        );
    }

    @Override
    protected void verifyField(String fieldName, Control field, Person person) {
        defaultCostFieldVerification(fieldName, field, person);
    }
}

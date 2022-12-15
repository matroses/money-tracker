package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;

import java.util.*;

public class AirplaneTicketPane extends TicketTypePane {

    public AirplaneTicketPane(CreateTicketController controller) {
        super(controller);
    }

    @Override
    public void update() {

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

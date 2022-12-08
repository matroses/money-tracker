package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;

import java.util.*;

public class AirplaneTicketPane extends TicketTypePane {

    public AirplaneTicketPane(CreateTicketController controller) {
        super(controller);

        this.add(personLabel, 0, 0);
        this.add(personChoiceBox, 1, 0);
    }

    @Override
    public void update() {

    }

    public void addFieldsForNewPerson(Person person) {
        if (fieldsPerPerson.containsKey(person)) {
            return;
        }

        Map<String, Control> fields = new LinkedHashMap<>();
        fields.put("Seat", new TextField());
        fields.put("Flight number", new TextField());
        fields.put("Departure airport", new TextField());
        fields.put("Arrival airport", new TextField());
        fields.put("Departure date", new TextField());
        fields.put("Arrival date", new TextField());
        fields.put("Price", new TextField());
        fieldsPerPerson.put(person, fields);

        this.renderFields();
    }

    @Override
    protected void saveTicket() {
        // TODO: Implement
    }
}

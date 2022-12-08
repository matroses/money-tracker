package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenericTicketPane extends TicketTypePane {

    public GenericTicketPane(CreateTicketController controller) {
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
        fields.put("Cost", new TextField());
        fieldsPerPerson.put(person, fields);

        this.renderFields();
    }

    @Override
    protected void saveTicket() {
        // TODO: Implement
    }
}

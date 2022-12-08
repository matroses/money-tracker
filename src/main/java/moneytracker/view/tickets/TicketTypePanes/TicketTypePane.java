package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.view.View;

import java.util.*;

public abstract class TicketTypePane extends GridPane implements View {

    protected final CreateTicketController controller;

    protected final Map<Person, Map<String, Control>> fieldsPerPerson = new HashMap<>();
    protected final ChoiceBox<Person> personChoiceBox = new ChoiceBox<>();
    protected final Label personLabel = new Label("Select a person to add: ");

    public TicketTypePane(CreateTicketController controller) {
        super();
        this.controller = controller;
        this.setPadding(new Insets(10, 0, 0, 0));

        personChoiceBox.setConverter(Person.getConverter());
        personChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.addFieldsForNewPerson(newValue);
            }
        });
        this.updatePersonChoiceBox();
    }

    protected abstract void addFieldsForNewPerson(Person person);
    protected abstract void saveTicket();

    /**
     * Go over each person and render their fields
     */
    protected void renderFields() {
        this.getChildren().clear();
        this.add(personLabel, 0, 0);
        this.add(personChoiceBox, 1, 0);
        this.add(new Label(""), 0, 2);

        // Go over each person and render the fields, we increment the row each time
        int row = 3;
        for (Map.Entry<Person, Map<String, Control>> entry : fieldsPerPerson.entrySet()) {
            // Configure the name label
            Label nameLabel = new Label(entry.getKey().getFullName());
            nameLabel.setStyle("-fx-font-weight: bold;");
            this.add(nameLabel, 0, row, 2, 1);

            row++;

            // Go over all the fields and add them to the grid
            for (Map.Entry<String, Control> field : entry.getValue().entrySet()) {
                this.add(new Label(field.getKey()), 0, row);
                this.add(field.getValue(), 1, row);
                row++;
            }

            // Add delete button
            Button deleteButton = createDeleteButtonForPerson(entry.getKey());
            this.add(deleteButton, 0, row++, 2, 1);

            // Add a separator
            this.add(new Label("------------------------------------------------"), 0, row++, 2, 1);
        }

        // Create a button to save the ticket
        Button saveButton = new Button("Save ticket");
        saveButton.setOnAction(event -> {
            this.saveTicket();
        });
    }

    /**
     * Get all the people that are not currently selected
     * We don't want to see the same person twice in the dropdown
     * @return List of people that are not currently selected
     */
    protected List<Person> getPeopleCurrentlySelected() {
        List<Person> allPeople = controller.getPeople();
        Set<Person> selectedPeople = fieldsPerPerson.keySet();
        List<Person> availablePeople = new ArrayList<>();

        for (Person person : allPeople) {
            if (!selectedPeople.contains(person)) {
                availablePeople.add(person);
            }
        }

        return availablePeople;
    }

    /**
     * Updates the person choice box with the people that are not yet selected
     */
    protected void updatePersonChoiceBox() {
        personChoiceBox.getItems().clear();
        personChoiceBox.getItems().addAll(this.getPeopleCurrentlySelected());
    }

    protected Button createDeleteButtonForPerson(Person person) {
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            fieldsPerPerson.remove(person);
            this.updatePersonChoiceBox();
            this.renderFields();
        });
        return deleteButton;
    }
}

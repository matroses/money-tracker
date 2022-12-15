package main.java.moneytracker.view.tickets.TicketTypePanes;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.tickets.Ticket;
import main.java.moneytracker.view.View;

import java.util.*;

public abstract class TicketTypePane extends GridPane implements View {

    protected final CreateTicketController controller;

    protected final ChoiceBox<Person> personChoiceBox = new ChoiceBox<>(), payerChoiceBox = new ChoiceBox<>();
    protected final ChoiceBox<PaymentStrategiesEnum> paymentStrategyChoiceBox = new ChoiceBox<>();
    protected final Label personLabel = new Label("Select a person to add: "), strategyLabel = new Label("Select a strategy: "), payerLabel = new Label("Select a payer: ");
    protected final Map<Person, Map<String, Control>> fieldsPerPerson = new HashMap<>();
    protected final Map<Person, Map<Control, String>> errors = new HashMap<>();

    protected Person paidByPerson;
    protected Ticket ticket;

    public TicketTypePane(CreateTicketController controller) {
        super();
        this.controller = controller;
        this.setPadding(new Insets(10, 0, 0, 0));

        this.configureChoiceBoxes();
        this.updatePersonChoiceBox();
        this.renderFields();
    }

    protected abstract void addFieldsForNewPerson(Person person);
    protected abstract void saveTicket();
    protected abstract void verifyField(String fieldName, Control field, Person person);

    /**
     * Go over each person and render their fields
     */
    protected void renderFields() {
        this.getChildren().clear();
        this.add(strategyLabel, 0, 0);
        this.add(paymentStrategyChoiceBox, 1, 0);
        this.add(payerLabel, 0, 1);
        this.add(payerChoiceBox, 1, 1);
        this.add(personLabel, 0, 2);
        this.add(personChoiceBox, 1, 2);
        this.add(new Label(""), 0, 3);

        int row = 4;

        // Go over each person and render the fields, we increment the row each time
        for (Map.Entry<Person, Map<String, Control>> entry : fieldsPerPerson.entrySet()) {
            // Get all the errors for this person
            Map<Control, String> personErrors = errors.get(entry.getKey());
            // Configure the name label
            Label nameLabel = new Label(entry.getKey().getFullName());
            nameLabel.setStyle("-fx-font-weight: bold;");
            this.add(nameLabel, 0, row, 2, 1);

            row++;

            // Go over all the fields and add them to the grid
            for (Map.Entry<String, Control> field : entry.getValue().entrySet()) {
                this.add(new Label(field.getKey()), 0, row);
                this.add(field.getValue(), 1, row);

                // If there are errors for this field, add them to the grid
                if (personErrors != null && personErrors.containsKey(field.getValue())) {
                    this.add(new Label(personErrors.get(field.getValue())), 2, row);
                }

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
        this.add(saveButton, 0, row, 2, 1);
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

    private void configureChoiceBoxes() {
        // Select the person who pays
        payerChoiceBox.setConverter(Person.getConverter());
        payerChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.paidByPerson = newValue;
        });
        payerChoiceBox.getItems().addAll(this.controller.getPeople());

        // Select a person to add choice box
        personChoiceBox.setConverter(Person.getConverter());
        personChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.addFieldsForNewPerson(newValue);
            }

            // Reset the choice box
            personChoiceBox.getSelectionModel().clearSelection();
        });
        paymentStrategyChoiceBox.getItems().addAll(PaymentStrategiesEnum.values());
        paymentStrategyChoiceBox.setConverter(PaymentStrategiesEnum.getConverter());
    }

    protected PaymentStrategiesEnum getSelectedStrategy() {
        return paymentStrategyChoiceBox.getSelectionModel().getSelectedItem();
    }

    /**
     * For each field get the values per person
     */
    protected Map<String, Map<Person, Float>> getValuesPerField() {
        Map<String, Map<Person, Float>> values = new HashMap<>();

        for (Map.Entry<Person, Map<String, Control>> entry : fieldsPerPerson.entrySet()) {
            for (Map.Entry<String, Control> field : entry.getValue().entrySet()) {
                // If the field is not yet in the values map, add it
                if (!values.containsKey(field.getKey())) {
                    values.put(field.getKey(), new HashMap<>());
                }

                float value = 0;
                try {
                    value = Float.parseFloat(((TextField) field.getValue()).getText());
                } catch (NumberFormatException ignored) {}

                // Only add if the value is not 0
                if (value != 0) {
                    values.get(field.getKey()).put(entry.getKey(), value);
                }
            }
        }

        return values;
    }

    /**
     * Verify all the fields for every person
     * @return True if all the fields are valid, false otherwise
     */
    protected boolean verifyFields() {
        // Clear all the errors
        errors.clear();

        // Go over each person and verify their fields
        for (Map.Entry<Person, Map<String, Control>> entry : fieldsPerPerson.entrySet()) {
            // Go over each field and verify it
            for (Map.Entry<String, Control> field : entry.getValue().entrySet()) {
                this.verifyField(field.getKey(), field.getValue(), entry.getKey());
            }
        }

        return this.errors.isEmpty();
    }

    /**
     * A default verificator for a cost field
     * @param fieldName The name of the field
     * @param field the field to verify
     * @param person the person that is associated with the field
     * @author Sigfried
     */
    protected void defaultCostFieldVerification(String fieldName, Control field, Person person) {
        TextField costField = (TextField) field;

        try {
            // Check if the value of the field is empty, otherwise verify it
            if (costField.getText().trim().isEmpty()) {
                costField.setText("0");
            } else {
                Float.parseFloat(costField.getText());
            }
        } catch (NumberFormatException e) {
            addErrorForControlToPerson(person, field, fieldName + " must be a valid number");
        }
    }

    /**
     * Add an error to a control
     * @param person The person that is associated with the control
     * @param control The control to add the error to
     * @param error The error to add
     */
    protected void addErrorForControlToPerson(Person person, Control control, String error) {
        if (!errors.containsKey(person)) {
            errors.put(person, new HashMap<>());
        }

        errors.get(person).put(control, error);
    }
}
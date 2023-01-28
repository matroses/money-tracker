package moneytracker.view.tickets.TicketTypePanes;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;
import moneytracker.model.tickets.Ticket;
import moneytracker.view.View;

import java.util.*;

public abstract class TicketTypePane extends GridPane implements View {

    protected final CreateTicketController controller;

    protected final ChoiceBox<Person> personChoiceBox = new ChoiceBox<>(), payerChoiceBox = new ChoiceBox<>();
    protected final ChoiceBox<PaymentStrategiesEnum> paymentStrategyChoiceBox = new ChoiceBox<>();
    protected final Label personLabel = new Label("Select a person to add: "), strategyLabel = new Label("Select a strategy: "), payerLabel = new Label("Select a payer: ");
    protected final Map<Person, Map<String, Control>> fieldsPerPerson = new HashMap<>();
    protected final Map<Person, Map<Control, String>> errors = new HashMap<>();
    protected final Label genericErrorLabel = new Label();

    protected Person paidByPerson;
    protected Ticket ticket;

    public TicketTypePane(CreateTicketController controller, Ticket ticket) {
        super();
        this.controller = controller;
        this.setPadding(new Insets(10, 0, 0, 0));

        this.configureChoiceBoxes();
        this.updatePersonChoiceBox();

        // If the ticket is not null, it means that the user is editing a ticket
        if (ticket != null) {
            this.ticket = ticket;
            this.paidByPerson = this.ticket.getPaidBy();
            this.payerChoiceBox.setValue(this.paidByPerson);
            this.paymentStrategyChoiceBox.setValue(this.ticket.getPaymentStrategy());
        }

        this.genericErrorLabel.setStyle("-fx-text-fill: red");
        GridPane.setMargin(this.genericErrorLabel, new Insets(10, 0, 0, 0));

        this.renderFields();
    }

    protected abstract void addFieldsForNewPerson(Person person, boolean renderFields);
    protected abstract Ticket createTicket(Map<String, Map<Person, Float>> values);
    protected abstract void verifyField(String fieldName, Control field, Person person);
    protected abstract Map<String, Map<Person, Float>> getTicketValues();

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
        this.add(this.genericErrorLabel, 0, 3);


        // If we are editing a ticket, get the values of the ticket
        Map<String, Map<Person, Float>> ticketValues = null;
        if (this.ticket != null) {
            ticketValues = this.getTicketValues();

            // For each person in the ticket values, add their fields
            for (Map.Entry<String, Map<Person, Float>> entry : ticketValues.entrySet()) {
                Map<Person, Float> personValues = entry.getValue();

                for (Map.Entry<Person, Float> personEntry : personValues.entrySet()) {
                    Person person = personEntry.getKey();

                    if (!this.fieldsPerPerson.containsKey(person)) {
                        this.addFieldsForNewPerson(person, false);
                    }

                    // Set the value of the field
                    ((TextField) this.fieldsPerPerson.get(person).get(entry.getKey())).setText(String.valueOf(personEntry.getValue()));
                }
            }
        }

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
                    Label errorLabel = new Label(personErrors.get(field.getValue()));
                    errorLabel.setStyle("-fx-text-fill: red");
                    this.add(errorLabel, 2, row);

                    // If we are editing a ticket, set the value of the field
                    if (this.ticket != null) {
                        field.getValue().setUserData(ticketValues.get(field.getKey()).get(entry.getKey()));
                    }
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
        // Set top margin
        GridPane.setMargin(saveButton, new Insets(10, 0, 0, 0));
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
                this.addFieldsForNewPerson(newValue, true);
                updatePersonChoiceBox();
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

    protected void saveTicket() {
        if (!this.verifyFields()) {
            this.renderFields();
            return;
        }

        // Get values from fields
        Map<String, Map<Person, Float>> values = this.getValuesPerField();

        if (this.ticket != null) {
            this.controller.deleteTicket(this.ticket);
        }

        this.ticket = this.createTicket(values);
    }

    /**
     * Verify all the fields for every person
     * @return True if all the fields are valid, false otherwise
     */
    protected boolean verifyFields() {
        boolean areGenericFieldsValid = false;
        // Clear all the errors
        errors.clear();

        // Check if the strategy, payer are selected
        if (this.getSelectedStrategy() == null) {
            this.genericErrorLabel.setText("Please select a payment strategy");
        } else if (this.paidByPerson == null) {
            this.genericErrorLabel.setText("Please select a person who pays");
        } else if (this.fieldsPerPerson.isEmpty()) {
            this.genericErrorLabel.setText("Please select at least one person");
        } else {
            this.genericErrorLabel.setText("");
            areGenericFieldsValid = true;
        }

        // Go over each person and verify their fields
        for (Map.Entry<Person, Map<String, Control>> entry : fieldsPerPerson.entrySet()) {
            // Go over each field and verify it
            for (Map.Entry<String, Control> field : entry.getValue().entrySet()) {
                this.verifyField(field.getKey(), field.getValue(), entry.getKey());
            }
        }

        return areGenericFieldsValid && this.errors.isEmpty();
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

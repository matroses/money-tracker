package main.java.moneytracker.view.people;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.controller.people.PeopleOverviewController;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.view.View;

public class PersonShowcasePane extends GridPane implements View {

    private final PeopleOverviewController controller;
    private Person person;
    private final Label titleLabel, firstNameLabel, lastNameLabel, isDeletedLabel;
    private final TextField firstNameField, lastNameField;
    private final Button saveButton, removeButton, reactivateButton;
    private final Dialog<ButtonType> dialog;
    private final ButtonType confirmButtonType;

    public PersonShowcasePane(PeopleOverviewController controller) {
        this.controller = controller;
        titleLabel = new Label("");
        titleLabel.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        firstNameLabel = new Label("First name:");
        lastNameLabel = new Label("Last name:");
        firstNameField = new TextField();
        lastNameField = new TextField();
        isDeletedLabel = new Label("");
        saveButton = new Button("Save");
        saveButton.setOnAction(event -> this.updatePerson());
        removeButton = new Button("Remove");
        removeButton.setOnAction(event -> this.showDeletePopup());
        reactivateButton = new Button("Reactivate");
        reactivateButton.setOnAction(event -> this.reactivatePerson());

        dialog = new Dialog<>();
        dialog.setContentText("Are you sure you want to remove this person? The person will still be shown in tickets where they were mentioned.");
        confirmButtonType = new ButtonType("Remove", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, cancelButtonType);

        update();
    }

    @Override
    public void update() {
        this.getChildren().clear();
        this.add(titleLabel, 0, 0, 2, 1);

        // If there is no person, remove all the elements and display a label to select a person
        if (person == null) {
            titleLabel.setText("Select a person to view their details");
            return;
        }

        this.titleLabel.setText("Editing user " + person.getFullName());
        this.firstNameField.setText(person.getFirstName());
        this.lastNameField.setText(person.getLastName());
        this.firstNameField.setDisable(person.isDeleted());
        this.lastNameField.setDisable(person.isDeleted());

        if (person.isDeleted()) {
            this.isDeletedLabel.setText(person.isDeleted() ? "This person is deleted." : "This person is currently active.");
            this.add(isDeletedLabel, 0, 3);
            this.add(reactivateButton, 0, 4);
        } else {
            this.add(firstNameLabel, 0, 1);
            this.add(firstNameField, 1, 1);
            this.add(lastNameLabel, 0, 2);
            this.add(lastNameField, 1, 2);
            this.add(saveButton, 0, 3);
            this.add(removeButton, 1, 3);
        }
    }

    public void setPerson(Person person) {
        // If the current person is the same as the new person, don't update
        if (this.person == person) {
            return;
        }

        this.person = person;
        this.update(); // Now update the view
    }

    public void updatePerson() {
        if (this.person == null) {
            return;
        }

        this.person.setFirstName(this.firstNameField.getText());
        this.person.setLastName(this.lastNameField.getText());
        this.controller.updatePerson(this.person);
    }

    public void showDeletePopup() {
        if (this.person == null) {
            return;
        }

        dialog.showAndWait().ifPresent(response -> {
            if (response == this.confirmButtonType) {
                this.person.setDeleted(true);
                this.controller.updatePerson(this.person);
            }
        });
    }

    public void reactivatePerson() {
        if (this.person == null) {
            return;
        }

        this.person.setDeleted(false);
        this.controller.updatePerson(this.person);
    }
}

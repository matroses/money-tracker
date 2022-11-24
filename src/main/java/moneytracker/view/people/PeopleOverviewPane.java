package main.java.moneytracker.view.people;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.controller.people.PeopleOverviewController;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;
import main.java.moneytracker.view.View;
import main.java.moneytracker.view.tickets.CreateTicketPane;

public class PeopleOverviewPane extends GridPane implements View {

    private final PeopleOverviewController controller;
    private TableView<Person> personTable;

    public PeopleOverviewPane(PeopleOverviewController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("All persons:"), 0, 0, 1, 1);
        this.setupPeopleTable();
        this.update();
        this.add(personTable, 0, 1);
    }

    public void setupPeopleTable() {
        this.personTable = new TableView<>();
        this.personTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Person person = personTable.getSelectionModel().getSelectedItem();
                System.out.println("You clicked on: " + person.getFullName());

                // TODO: update right screen with user info
            }
        });

        TableColumn<Person, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(300);
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        personTable.getColumns().add(colName);
    }

    @Override
    public void update() {
        personTable.setItems(FXCollections.observableArrayList(controller.getPeople()));
        personTable.refresh();
    }
}

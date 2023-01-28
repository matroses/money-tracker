package moneytracker.view.people;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import moneytracker.controller.people.PeopleOverviewController;
import moneytracker.model.Person;
import moneytracker.view.View;

public class PeopleOverviewPane extends GridPane implements View {

    private final PeopleOverviewController controller;
    private final PersonShowcasePane personShowcasePane;
    private TableView<Person> personTable;
    private Button createPersonButton;

    public PeopleOverviewPane(PeopleOverviewController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.personShowcasePane = new PersonShowcasePane(controller);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.createPersonButton = new Button("Create person");
        this.createPersonButton.setOnAction(event -> {
            this.personShowcasePane.setPerson(null);
        });

        this.add(new Label("All people:"), 0, 0, 1, 1);
        this.setupPeopleTable();
        this.update();
        this.add(personTable, 0, 1);
        this.add(personShowcasePane, 1, 1);
        this.add(createPersonButton, 0, 2);
    }

    public void setupPeopleTable() {
        this.personTable = new TableView<>();
        this.personTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                this.personShowcasePane.setPerson(
                    this.personTable.getSelectionModel().getSelectedItem()
                );
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
        personShowcasePane.update();
    }
}

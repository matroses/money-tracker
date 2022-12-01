package main.java.moneytracker.view.general;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.view.View;
import main.java.moneytracker.controller.OverviewController;

import java.lang.invoke.LambdaConversionException;
import java.util.Map;

public class OverviewPane extends GridPane implements View {

    private final OverviewController controller;

    Label titleLabel, totalPriceLabel;
    private TableView<Map<Person, Double>> personTable;

    public OverviewPane(OverviewController controller) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        this.titleLabel = new Label("Overview");
        this.totalPriceLabel = new Label("Total price: ");
        this.personTable = new TableView<Map<Person, Double>>();

        this.add(new Label("All tickets"), 0, 0, 1, 1);
        this.setupPersonTable();
        this.update();
        this.add(personTable, 0, 1);
    }

    public void setupPersonTable() {
        /*this.ticketTable = new TableView<>();

        TableColumn<Ticket, String> colName = new TableColumn<>("Price");
        colName.setMinWidth(300);
        colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("€ " + cellData.getValue().getTotal()));
        ticketTable.getColumns().add(colName);*/

        this.personTable = new TableView<>();

        //TableColumn<Person, Double> colName = new TableColumn<>("Debt");
    }

    public void update() {
        // Update the overview pane, called by the controller
        personTable.setItems(FXCollections.observableArrayList(controller.getDebtPerPerson()));
        personTable.refresh();
    }
}

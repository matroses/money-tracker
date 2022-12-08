package main.java.moneytracker.view.tickets;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.controller.tickets.TicketsOverviewController;
import main.java.moneytracker.model.Ticket;
import main.java.moneytracker.view.View;

public class TicketsOverviewPane extends GridPane implements View {

    private final TicketsOverviewController controller;
    private final CreateTicketPane createTicketPane;
    private TableView<Ticket> ticketTable;

    public TicketsOverviewPane(TicketsOverviewController controller, CreateTicketController createTicketController) {
        this.controller = controller;
        this.controller.setView(this);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("All tickets"), 0, 0, 1, 1);
        this.setupTicketsTable();
        this.update();
        this.add(ticketTable, 0, 1);

        // Create ticket pane
        createTicketPane = new CreateTicketPane(createTicketController);
        this.add(createTicketPane, 1, 0, 2, 2);
    }

    public void setupTicketsTable() {
        this.ticketTable = new TableView<>();

        TableColumn<Ticket, String> colName = new TableColumn<>("Price");
        colName.setMinWidth(300);
        colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("€ " + cellData.getValue().getTotal()));
        ticketTable.getColumns().add(colName);
    }

    @Override
    public void update() {
        ticketTable.setItems(FXCollections.observableArrayList(controller.getTickets()));
        ticketTable.refresh();
    }
}

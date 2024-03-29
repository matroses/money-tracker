package moneytracker.view.tickets;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.controller.tickets.TicketsOverviewController;
import moneytracker.model.tickets.Ticket;
import moneytracker.view.View;

import java.lang.reflect.InvocationTargetException;

public class TicketsOverviewPane extends GridPane implements View {

    private final TicketsOverviewController controller;
    private final CreateTicketController createTicketController;
    private TableView<Ticket> ticketTable;

    public TicketsOverviewPane(TicketsOverviewController controller, CreateTicketController createTicketController) {
        this.controller = controller;
        this.controller.setView(this);
        this.createTicketController = createTicketController;

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("All tickets"), 0, 0, 1, 1);
        this.setupTicketsTable();
        this.update();


        Button createTicketButton = new Button("Create ticket");
        createTicketButton.setOnAction(event -> {
            switchSidePane(new TicketPane(createTicketController));
        });
        Button deleteTicketButton = new Button("Delete ticket");
        deleteTicketButton.setOnAction(event -> {
            Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
            if (selectedTicket != null) {
                switchSidePane(new TicketPane(createTicketController));
                controller.deleteTicket(selectedTicket);
            }
        });

        this.add(ticketTable, 0, 1, 2, 1);
        this.add(createTicketButton, 0, 2);
        this.add(deleteTicketButton, 1, 2);
    }

    public void setupTicketsTable() {
        this.ticketTable = new TableView<>();

        TableColumn<Ticket, String> colName = new TableColumn<>("Price");
        colName.setMinWidth(300);
        colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPaidBy().getFullName() + " - € " + cellData.getValue().getTotal()));
        ticketTable.getColumns().add(colName);

        ticketTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
                
                if (selectedTicket != null) {
                    try {
                        switchSidePane(new TicketPane(createTicketController, selectedTicket));
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    @Override
    public void update() {
        ticketTable.setItems(FXCollections.observableArrayList(controller.getTickets()));
        ticketTable.refresh();
    }

    public void switchSidePane(GridPane pane) {
        // Remove the current side pane
        this.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 2);

        // Add the new side pane
        this.add(pane, 2, 0, 2, 2);
    }
}

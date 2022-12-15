package main.java.moneytracker.view.tickets;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.model.factories.TicketPaneFactory;
import main.java.moneytracker.model.tickets.Ticket;
import main.java.moneytracker.view.View;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

import java.lang.reflect.InvocationTargetException;

public class TicketPane extends GridPane implements View {

    private final CreateTicketController controller;
    private final ChoiceBox<TicketTypeEnum> ticketTypeChoiceBox;
    private TicketTypePane ticketTypePane;
    private Label ticketTitleLabel = new Label("Create new ticket");

    public TicketPane(CreateTicketController controller) {
        this.controller = controller;
        this.controller.setView(this);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        // Add create new ticket label
        ticketTitleLabel.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        this.add(ticketTitleLabel, 0, 0, 2, 1);

        // Add ticket type choice box and label
        this.add(new Label("Select ticket type"), 0, 1, 2, 1);
        this.ticketTypeChoiceBox = new ChoiceBox<>();
        this.ticketTypeChoiceBox.getItems().addAll(TicketTypeEnum.values());
        this.ticketTypeChoiceBox.setConverter(TicketTypeEnum.getConverter());
        // When the user selects a ticket type, the ticket type pane will be updated
        this.ticketTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.setTicketType(newValue, null);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        this.add(this.ticketTypeChoiceBox, 0, 2, 2, 1);
    }

    public TicketPane(CreateTicketController controller, Ticket ticket) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        this(controller);
        ticketTitleLabel.setText("Edit ticket");

        // Remove the ticket type choice box
        this.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
        this.setTicketType(TicketTypeEnum.getEnumByTicket(ticket), ticket);
    }

    @Override
    public void update() {
        this.ticketTypePane.update();
    }

    public void setTicketType(TicketTypeEnum ticketType, Ticket ticket) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        this.ticketTypePane = TicketPaneFactory.createTicketPane(ticketType, this.controller);

        // Delete the node at row 3 if it exists
        this.getChildren().removeIf(node -> getRowIndex(node) == 3);

        this.add(this.ticketTypePane, 0, 3, 3, 3);
    }
}

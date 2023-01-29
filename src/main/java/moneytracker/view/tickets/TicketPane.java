package moneytracker.view.tickets;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.model.enums.TicketTypeEnum;
import moneytracker.model.factories.TicketPaneFactory;
import moneytracker.model.tickets.Ticket;
import moneytracker.view.View;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

import java.lang.reflect.InvocationTargetException;

public class TicketPane extends GridPane implements View {

    private final CreateTicketController controller;
    private final ChoiceBox<TicketTypeEnum> ticketTypeChoiceBox;
    private TicketTypePane ticketTypePane;
    private final Label ticketTitleLabel = new Label("Create new ticket");

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

    /**
     * This constructor is used when the user wants to edit an existing ticket
     * @param controller
     * @param ticket
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public TicketPane(CreateTicketController controller, Ticket ticket) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        this(controller);
        ticketTitleLabel.setText("Edit ticket");

        // Remove the ticket type choice box
        this.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
        this.setTicketType(TicketTypeEnum.getEnumByTicket(ticket), ticket);
    }

    @Override
    public void update() {
        if (this.ticketTypePane != null) {
            this.ticketTypePane.update();
        }
    }

    /**
     * This method is used to set the ticket type pane, it will create a new pane
     * @param ticketType The ticket type
     * @param ticket The ticket to edit if the user wants to edit an existing ticket, can be null
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void setTicketType(TicketTypeEnum ticketType, Ticket ticket) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        this.ticketTypeChoiceBox.setValue(ticketType);
        this.ticketTypeChoiceBox.setDisable(ticket != null); // Disable the choice box if the user is editing an existing ticket
        this.ticketTypePane = TicketPaneFactory.createTicketPane(ticketType, this.controller, ticket);

        // Delete the node at row 3 if it exists
        this.getChildren().removeIf(node -> getRowIndex(node) == 3);
        this.add(this.ticketTypePane, 0, 3, 3, 3);
    }
}

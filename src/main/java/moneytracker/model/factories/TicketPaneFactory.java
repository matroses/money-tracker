package main.java.moneytracker.model.factories;

import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.view.tickets.TicketTypePanes.AirplaneTicketPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

public class TicketPaneFactory {

    public static TicketTypePane createTicketPane(TicketTypeEnum ticketType, CreateTicketController controller) {
        if (ticketType.getTicketTypePaneClass().equals(AirplaneTicketPane.class)) {
            return new AirplaneTicketPane(controller);
        }

        return null;
    }
}

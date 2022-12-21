package main.java.moneytracker.model.factories;

import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.model.tickets.Ticket;
import main.java.moneytracker.view.tickets.TicketTypePanes.AirplaneTicketPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.GenericTicketPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

/**
 * Factory for creating TicketTypePane objects.
 *
 * @author Sigfried
 */
public class TicketPaneFactory {

    public static TicketTypePane createTicketPane(TicketTypeEnum ticketType, CreateTicketController controller, Ticket ticket) {
        Class<? extends TicketTypePane> ticketTypePaneClass = ticketType.getTicketTypePaneClass();

        if (ticketTypePaneClass.equals(AirplaneTicketPane.class)) {
            return new AirplaneTicketPane(controller, ticket);
        }

        if (ticketTypePaneClass.equals(GenericTicketPane.class)) {
            return new GenericTicketPane(controller, ticket);
        }

        return null;
    }
}

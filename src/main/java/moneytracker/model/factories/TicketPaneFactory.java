package main.java.moneytracker.model.factories;

import main.java.moneytracker.controller.tickets.CreateTicketController;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.view.tickets.TicketTypePanes.AirplaneTicketPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.GenericTicketPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

/**
 * Factory for creating TicketTypePane objects.
 *
 * @author Sigfried
 */
public class TicketPaneFactory {

    public static TicketTypePane createTicketPane(TicketTypeEnum ticketType, CreateTicketController controller) {
        Class<? extends TicketTypePane> ticketTypePaneClass = ticketType.getTicketTypePaneClass();

        if (ticketTypePaneClass.equals(AirplaneTicketPane.class)) {
            return new AirplaneTicketPane(controller);
        }

        if (ticketTypePaneClass.equals(GenericTicketPane.class)) {
            return new GenericTicketPane(controller);
        }

        return null;
    }
}

package moneytracker.model.factories;

import moneytracker.controller.tickets.CreateTicketController;
import moneytracker.model.enums.TicketTypeEnum;
import moneytracker.model.tickets.Ticket;
import moneytracker.view.tickets.TicketTypePanes.AirplaneTicketPane;
import moneytracker.view.tickets.TicketTypePanes.GenericTicketPane;
import moneytracker.view.tickets.TicketTypePanes.RestaurantTicketPane;
import moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

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

        if (ticketTypePaneClass.equals(RestaurantTicketPane.class)) {
            return new RestaurantTicketPane(controller, ticket);
        }

        return null;
    }
}

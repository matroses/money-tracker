package moneytracker.model.enums;

import javafx.util.StringConverter;
import moneytracker.model.tickets.*;
import moneytracker.view.tickets.TicketTypePanes.*;

public enum TicketTypeEnum {
    GENERIC_TICKET("Generic ticket", GenericTicket.class, GenericTicketPane.class),
    AIRPLANE_TICKET("Airplane ticket", AirplaneTicket.class, AirplaneTicketPane.class),
    RESTAURANT_TICKET("Restaurant ticket", RestaurantTicket.class, RestaurantTicketPane.class);

    private final String name;
    private final Class<? extends Ticket> ticketClass;
    private final Class<? extends TicketTypePane> ticketTypePaneClass;

    TicketTypeEnum(String name, Class<? extends Ticket> ticketClass, Class<? extends TicketTypePane> ticketTypePaneClass) {
        this.name = name;
        this.ticketClass = ticketClass;
        this.ticketTypePaneClass = ticketTypePaneClass;
    }
    public String getName() {
        return name;
    }

    public Class<? extends Ticket> getTicketClass() {
        return ticketClass;
    }

    public Class<? extends TicketTypePane> getTicketTypePaneClass() {
        return ticketTypePaneClass;
    }

    /**
     * Finds the enum value by the associated ticket class.
     * @author Sigfried
     * @param ticketClass The ticket class to find the enum value for. Must be a subclass of Ticket.
     * @return The enum value associated with the ticket class. Null if no enum value is associated with the ticket class.
     */
    public static TicketTypeEnum getEnumByTicketClass(Class<? extends Ticket> ticketClass) {
        for (TicketTypeEnum typeEnum : values()) {
            if (typeEnum.getTicketClass() == ticketClass) {
                return typeEnum;
            }
        }

        return null;
    }

    public static TicketTypeEnum getEnumByTicket(Ticket ticket) {
        return getEnumByTicketClass(ticket.getClass());
    }

    /**
     * Returns a StringConverter for this enum, used in a ChoiceBox for JavaFX
     * @return StringConverter
     * @author Sigfried
     */
    public static StringConverter<TicketTypeEnum> getConverter() {
        return new StringConverter<TicketTypeEnum>() {
            @Override
            public String toString(TicketTypeEnum ticketTypeEnum) {
                return ticketTypeEnum.getName();
            }

            @Override
            public TicketTypeEnum fromString(String s) {
                return null;
            }
        };
    }
}

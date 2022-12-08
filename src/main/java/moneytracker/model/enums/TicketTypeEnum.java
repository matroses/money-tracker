package main.java.moneytracker.model.enums;

import javafx.util.StringConverter;
import main.java.moneytracker.view.tickets.TicketTypePanes.AirplaneTicketPane;
import main.java.moneytracker.view.tickets.TicketTypePanes.TicketTypePane;

public enum TicketTypeEnum {
    AIRPLANE_TICKET("Airplane ticket", AirplaneTicketPane.class);
    //RestaurantTicket,
    //TaxiTicket,
    //ConcertTicket,
    //ShoppingTicket;

    private final String name;
    private final Class<? extends TicketTypePane> ticketTypePaneClass;

    TicketTypeEnum(String name, Class<? extends TicketTypePane> ticketTypePaneClass) {
        this.name = name;
        this.ticketTypePaneClass = ticketTypePaneClass;
    }
    public String getName() {
        return name;
    }

    public Class<? extends TicketTypePane> getTicketTypePaneClass() {
        return ticketTypePaneClass;
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

package main.java.moneytracker.model.db;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.tickets.Ticket;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TicketDB extends Database {

    private static TicketDB instance;

    private final Map<Person, Map<UUID, Ticket>> tickets = new HashMap<>();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private TicketDB() {}

    public static TicketDB getInstance() {
        if (instance == null) {
            instance = new TicketDB();
        }

        return instance;
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> allTickets = new ArrayList<>();

        for (Map<UUID, Ticket> ticketMap : tickets.values()) {
            allTickets.addAll(ticketMap.values());
        }

        return allTickets;
    }

    public Map<UUID, Ticket> getTicketsByPerson(Person person) {
        Map<UUID, Ticket> ticketsByPerson = tickets.get(person);

        if (ticketsByPerson == null) {
            ticketsByPerson = new HashMap<>();
        }

        return ticketsByPerson;
    }

    public void addTicket(Ticket ticket) {
        Map<UUID, Ticket> ticketsByPerson = getTicketsByPerson(ticket.getPaidBy());
        ticketsByPerson.put(ticket.getId(), ticket);
        tickets.put(ticket.getPaidBy(), ticketsByPerson);

        this.propertyChangeSupport.firePropertyChange("TICKET_ADDED", null, ticket);
    }

    /**
     * Add a property change listener to this model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}

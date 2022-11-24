package main.java.moneytracker.model;

import main.java.moneytracker.model.db.PeopleDB;
import main.java.moneytracker.model.db.TicketDB;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MoneyTrackerApp {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private final PeopleDB peopleDB = PeopleDB.getInstance();
    private final TicketDB ticketDB = TicketDB.getInstance();

    public PeopleDB getPeopleDB() {
        return peopleDB;
    }

    public TicketDB getTicketDB() {
        return ticketDB;
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

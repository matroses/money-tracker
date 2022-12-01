package main.java.moneytracker.model;

import main.java.moneytracker.model.db.PeopleDB;
import main.java.moneytracker.model.db.TicketDB;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MoneyTrackerApp implements PropertyChangeListener {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private final PeopleDB peopleDB = PeopleDB.getInstance();
    private final TicketDB ticketDB = TicketDB.getInstance();

    public PeopleDB getPeopleDB() {
        return peopleDB;
    }

    public TicketDB getTicketDB() {
        return ticketDB;
    }

    public MoneyTrackerApp() {
        peopleDB.addPropertyChangeListener(this);
        ticketDB.addPropertyChangeListener(this);
    }

    /**
     * Add a property change listener to this model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Pass through updates in sub-models to listeners of this model (E.g.: database to controller so that the view can be updated)
        for (PropertyChangeListener listener : propertyChangeSupport.getPropertyChangeListeners()) {
            listener.propertyChange(evt);
        }
    }
}

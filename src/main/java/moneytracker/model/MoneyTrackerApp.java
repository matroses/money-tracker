package main.java.moneytracker.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MoneyTrackerApp {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add a property change listener to this model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}

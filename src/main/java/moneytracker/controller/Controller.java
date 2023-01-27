package moneytracker.controller;

import moneytracker.model.MoneyTrackerApp;
import moneytracker.view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class Controller implements PropertyChangeListener {

    protected View view;
    protected final MoneyTrackerApp app; // So the view can access the application

    public Controller(MoneyTrackerApp moneyTracker) {
        this.app = moneyTracker;
        this.app.addPropertyChangeListener(this);
    }

    public void setView(View view) {
        this.view = view;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        view.update();
    }
}

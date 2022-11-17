package main.java.moneytracker.controller;

import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.view.general.OverviewPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OverviewController extends Controller implements PropertyChangeListener {

    private final MoneyTrackerApp moneyTracker;
    private OverviewPane view;

    public OverviewController(MoneyTrackerApp moneyTracker) {
        this.moneyTracker = moneyTracker;
        this.moneyTracker.addPropertyChangeListener(this);
    }

    public void setView(OverviewPane view) {
        this.view = view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        view.refresh();
    }
}

package main.java.moneytracker.controller;

import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.view.general.OverviewPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class OverviewController extends Controller {

    public OverviewController(MoneyTrackerApp moneyTracker) {
        super(moneyTracker);
    }

    public ArrayList<String> validateTicket() {
        ArrayList<String> errors = new ArrayList<>();

        // Validate the ticket


        return errors;
    }
}

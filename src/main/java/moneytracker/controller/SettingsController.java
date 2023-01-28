package moneytracker.controller;

import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;
import moneytracker.model.tickets.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sigfried Seldeslachts
 */
public class SettingsController extends Controller {

    private final MoneyTrackerApp moneyTracker;

    public SettingsController(MoneyTrackerApp moneyTracker) {
        super(moneyTracker);
        this.moneyTracker = moneyTracker;
    }

    public void saveDatabase() {
        boolean peopleDBSave = this.moneyTracker.getPeopleDB().saveDatabase();
        boolean ticketDBSave = this.moneyTracker.getTicketDB().saveDatabase();

        // Perhaps add a message to the user if it failed? Is an extra feature when we have time
    }
}

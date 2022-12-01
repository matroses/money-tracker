package main.java.moneytracker.controller.tickets;

import main.java.moneytracker.controller.Controller;
import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TicketsOverviewController extends Controller {

    public TicketsOverviewController(MoneyTrackerApp app) {
        super(app);
    }

    public ArrayList<Ticket> getTickets() {
        return app.getTicketDB().getAllTickets();
    }
}

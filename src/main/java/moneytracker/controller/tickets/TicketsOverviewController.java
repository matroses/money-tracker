package moneytracker.controller.tickets;

import moneytracker.controller.Controller;
import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;
import moneytracker.model.tickets.AirplaneTicket;
import moneytracker.model.tickets.Ticket;

import java.util.ArrayList;
import java.util.Map;

public class TicketsOverviewController extends Controller {

    public TicketsOverviewController(MoneyTrackerApp app) {
        super(app);
    }

    public ArrayList<Ticket> getTickets() {
        return app.getTicketDB().getAllTickets();
    }

    public void deleteTicket(Ticket ticket) {
        app.getTicketDB().deleteTicket(ticket);
    }
}

package main.java.moneytracker.controller;

import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.tickets.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OverviewController extends Controller {

    public OverviewController(MoneyTrackerApp moneyTracker) {
        super(moneyTracker);

    }

    public ArrayList<String> validateTicket() {
        ArrayList<String> errors = new ArrayList<>();

        // Validate the ticket


        return errors;
    }

    // TODO: Map<Person (needs to pay/receive), Map<Person (from), Double (amount)>>
    public Map<Person, Double> getDebtPerPerson() {
        ArrayList<Ticket> tickets = this.app.getTicketDB().getAllTickets();
        Map<Person, Double> returnMap = new HashMap<>();
        /*
        for (Ticket ticket: tickets) {
            for (Person person: ticket.getCostPerPerson().keySet()) {
                if (!returnMap.containsKey(person)) returnMap.put(person, 0.0);

                if (person == ticket.getPaidBy()) {
                    returnMap.put(person, returnMap.get(person) - ticket.getTotal());
                } else {
                    returnMap.put(person, returnMap.get(person) + ticket.getCostPerPerson().get(person));
                }
            }
        }*/

        return returnMap;
    }
}

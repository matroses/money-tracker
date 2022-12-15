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

    // Returns map of the form Map<Debtee, Map<Debtor, Debt>> (debtMap<debtee, debtorMap<debtor, debt>>)
    public Map<Person, Map<Person, Float>> createDebtMap() {
        ArrayList<Person> people = this.app.getPeopleDB().getAllPeople();
        Map<Person, Float> debtorMap = new HashMap<>();
        Map<Person, Map<Person, Float>> debtMap = new HashMap<>();

        for (Person person: people) {
            debtorMap.put(person, 0f);
        }
        for (Person person: people) {
            debtMap.put(person, debtorMap);
        }

        return debtMap;
    }

    // Returns full map of debtees, debtors and debts
    public Map<Person, Map<Person, Float>> getDebtPerPerson() {
        ArrayList<Ticket> tickets = this.app.getTicketDB().getAllTickets();
        Map<Person, Map<Person, Float>> debtMap = this.createDebtMap();

        // Get all deptors, with debts for each debtee
        for (Ticket ticket: tickets) {
            Map<Person, Float> costPerPerson = ticket.getCostPerPerson();
            Person debtee = ticket.getPaidBy();
            Map<Person, Float> debtorMap = debtMap.get(debtee);

            for (Person person: costPerPerson.keySet()) {
                if (person != debtee) debtorMap.put(person, debtorMap.get(person) + costPerPerson.get(person));
            }
            debtMap.put(debtee, debtorMap);
        }

        // Recalculate differences between debtee & debtors
        // for (Person debtee: )

        return debtMap;
    }

    // Returns simplified map of debtees, debtors and debts, where differences between people are calculated are
    public Map<Person, Map<Person, Float>> getSimplifiedDebtPerPerson() {
        Map<Person, Map<Person, Float>> debtMap = this.getDebtPerPerson();
        Map<Person, Map<Person, Float>> simplifiedDebtMap = this.createDebtMap();



        return simplifiedDebtMap;
    }
}

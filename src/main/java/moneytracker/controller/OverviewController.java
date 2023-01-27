package main.java.moneytracker.controller;

import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.tickets.Ticket;

import java.awt.*;
import java.awt.datatransfer.FlavorMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OverviewController extends Controller {

    public OverviewController(MoneyTrackerApp moneyTracker)
    {
        super(moneyTracker);

    }

    public ArrayList<String> validateTicket()
    {
        ArrayList<String> errors = new ArrayList<>();

        // Validate the ticket

        return errors;
    }

    // Returns Empty map of the form Map<Debtee, Map<Debtor, Debt>> (debtMap<debtee, debtorMap<debtor, debt>>)
    public Map<Person, Map<Person, Float>> createEmptyDebtMap()
    {
        ArrayList<Person> people = this.app.getPeopleDB().getAllPeople();

        Map<Person, Map<Person, Float>> debtMap = new HashMap<>();

        for (Person debtee: people)
        {
            Map<Person, Float> debtorMap = new HashMap<>();

            for (Person debtor: people) {
                //if (debtor != debtee) debtorMap.put(debtor, 0f);
                debtorMap.put(debtor, 0f);
            }
            debtMap.put(debtee, debtorMap);
        }

        return debtMap;
    }

    // Returns full map of debtees, debtors and debts
    public Map<Person, Map<Person, Float>> getDebtMap()
    {
        ArrayList<Ticket> tickets = this.app.getTicketDB().getAllTickets();
        Map<Person, Map<Person, Float>> debtMap = this.createEmptyDebtMap();

        // Get all deptors, with debts for each debtee
        for (Ticket ticket: tickets) {
            Person debtee = ticket.getPaidBy();

            for (Map.Entry<Person, Float> entry: ticket.getCostPerPerson().entrySet()) {
                Map<Person, Float> debtorMap = debtMap.get(entry.getKey());

                debtorMap.put(debtee, debtorMap.get(debtee) + entry.getValue());
            }
        }

        // Recalculate differences between debtee & debtors
        for (Map.Entry<Person, Map<Person, Float>> debtee1: debtMap.entrySet()) {
            for (Map.Entry<Person, Map<Person, Float>> debtee2: debtMap.entrySet()) {
                Float debt1 = debtee1.getValue().get(debtee2.getKey());
                Float debt2 = debtee2.getValue().get(debtee1.getKey());
                System.out.println(debt1 + ", " + debt2);

                if (debt1 != 0f && debt2 != 0f) {
                    if (debt1 >= debt2) {
                        debtee1.getValue().put(debtee2.getKey(), debt1 - debt2);
                        debtee2.getValue().put(debtee1.getKey(), 0f);
                    }
                    else if (debt1 <= debt2) {
                        debtee1.getValue().put(debtee2.getKey(), 0f);
                        debtee2.getValue().put(debtee1.getKey(), debt2 - debt1);
                    }
                }
            }
        }

        return debtMap;
    }

    // Returns simplified map of debtees, debtors and debts, where differences between people are calculated are
    /*public Map<Person, Map<Person, Float>> getSimplifiedDebtPerPerson() {
        // Map<Person, Map<Person, Float>> debtMap = this.getDebtPerPerson();
        // Map<Person, Map<Person, Float>> simplifiedDebtMap = this.createDebtMap();



        return simplifiedDebtMap;
    }*/

    public void logDebtMap(Map<Person, Map<Person, Float>> debtMap) {
        for (Map.Entry<Person, Map<Person, Float>> entry: debtMap.entrySet()) {
            System.out.println("Debtee: " + entry.getKey().getFullName());
            for(Map.Entry<Person, Float> debtor: entry.getValue().entrySet()) {
                System.out.println("Debtor: " + debtor.getKey().getFullName());
                System.out.println("Debtor: " + debtor.getValue().toString());
            }
            System.out.println("#################################################");
        }
    }
}

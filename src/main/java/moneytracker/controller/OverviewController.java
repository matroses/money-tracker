package moneytracker.controller;

import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;
import moneytracker.model.tickets.Ticket;

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
            ticket.updateOwedPerPerson(); // Calculate owed per person

            for (Map.Entry<Person, Float> entry: ticket.getOwedPerPerson().entrySet()) {
                Map<Person, Float> debtorMap = debtMap.get(entry.getKey());

                debtorMap.put(debtee, debtorMap.get(debtee) + entry.getValue());
            }
        }

        // Calculate mutual debts between debtees & debtors (one to one)
        for (Map.Entry<Person, Map<Person, Float>> debtee1: debtMap.entrySet()) {
            for (Map.Entry<Person, Map<Person, Float>> debtee2: debtMap.entrySet()) {
                Float debt1 = debtee1.getValue().get(debtee2.getKey());
                Float debt2 = debtee2.getValue().get(debtee1.getKey());
                // System.out.println(debt1 + ", " + debt2);

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

    // Calculate mutual debts between debtees & debtors (one to two) -> reduces transactions to pay each other back
    public Map<Person, Map<Person, Float>> getSimplifiedDebtMap(Map<Person, Map<Person, Float>> debtMap)
    {
        // One debtor to two debtees
        for (Map.Entry<Person, Map<Person, Float>> p1: debtMap.entrySet())
        {
            for (Map.Entry<Person, Map<Person, Float>> p2: debtMap.entrySet())
            {
                for (Map.Entry<Person, Map<Person, Float>> p3: debtMap.entrySet())
                {
                    // All mutual debts
                    Float debt1 = p1.getValue().get(p2.getKey());
                    Float debt2 = p2.getValue().get(p1.getKey());
                    Float debt3 = p2.getValue().get(p3.getKey());
                    Float debt4 = p3.getValue().get(p2.getKey());
                    Float debt5 = p1.getValue().get(p3.getKey());
                    Float debt6 = p3.getValue().get(p1.getKey());

                    // Floats for formulas
                    float x = 0f;
                    float y = 0f;
                    float z = 0f;

                    // Entries to assign new values
                    Map.Entry<Person, Map<Person, Float>> a = null;
                    Map.Entry<Person, Map<Person, Float>> b = null;
                    Map.Entry<Person, Map<Person, Float>> c = null;

                    // p1 owes p2 & p3
                    if (debt1 > 0 && debt5 > 0)
                    {
                        a = p1;
                        // p2 owes p3
                        if (debt3 > debt4) {
                            x = debt3;
                            y = debt1;
                            z = debt5;
                            b = p2;
                            c = p3;
                        }
                        // p3 owes p2
                        if (debt3 < debt4) {
                            x = debt4;
                            y = debt5;
                            z = debt1;
                            b = p3;
                            c = p2;
                        }
                    }
                    // p2 owes p1 & p3
                    if (debt2 > 0 && debt3 > 0)
                    {
                        a = p2;
                        // p1 owes p3
                        if (debt5 > debt6) {
                            x = debt5;
                            y = debt2;
                            z = debt3;
                            b = p1;
                            c = p3;
                        }
                        // p3 owes p1
                        if (debt5 < debt6) {
                            x = debt6;
                            y = debt3;
                            z = debt2;
                            b = p3;
                            c = p1;
                        }
                    }
                    // p3 owes p1 & p2
                    if (debt4 > 0 && debt6 > 0)
                    {
                        a = p3;
                        // p1 owes p2
                        if (debt1 > debt2) {
                            x = debt1;
                            y = debt4;
                            z = debt6;
                            b = p1;
                            c = p2;
                        }
                        // p2 owes p1
                        if (debt1 < debt2) {
                            x = debt2;
                            y = debt6;
                            z = debt4;
                            b = p2;
                            c = p1;
                        }
                    }

                    if (!(x == 0f && y == 0f && z == 0f)) {
                        if (x < y) {
                            z += x;
                            y -= x;
                            x = 0;
                        }
                        else if (x > y) {
                            z += y;
                            x -= y;
                            y = 0;
                        }
                        else {
                            z += x;
                            x = 0;
                            y = 0;
                        }

                        if (a != null && b != null && c != null) {
                            b.getValue().put(c.getKey(), x);
                            a.getValue().put(b.getKey(), y);
                            a.getValue().put(c.getKey(), z);
                        }
                    }
                }
            }
        }

        return debtMap;
    }

    public void logDebtMap(Map<Person, Map<Person, Float>> debtMap) {
        for (Map.Entry<Person, Map<Person, Float>> entry: debtMap.entrySet()) {
            System.out.println("Debtor: " + entry.getKey().getFullName());
            for(Map.Entry<Person, Float> debtor: entry.getValue().entrySet()) {
                System.out.println("Debtee: " + debtor.getKey().getFullName());
                System.out.println("Debt: " + debtor.getValue().toString());
            }
            System.out.println("#################################################");
        }
    }
}

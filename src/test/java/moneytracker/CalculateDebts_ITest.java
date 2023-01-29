package moneytracker;

import moneytracker.controller.OverviewController;
import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;
import moneytracker.model.tickets.GenericTicket;
import moneytracker.model.enums.PaymentStrategiesEnum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CalculateDebts_ITest
{
    OverviewController controller;

    // Dummy users
    Person p1;
    Person p2;
    Person p3;

    Map<Person, Map<Person, Float>> debtMap;

    public CalculateDebts_ITest() {}

    @Before
    public void initialize()
    {
        MoneyTrackerApp app = new MoneyTrackerApp();
        controller = new OverviewController(app);

        // Create dummy users
        p1 = new Person("Jens", "De Hoog");
        app.getPeopleDB().addPerson(p1);
        p2 = new Person("Matthieu", "Larose");
        app.getPeopleDB().addPerson(p2);
        p3 = new Person("Sigfried", "Seldeslachts");
        app.getPeopleDB().addPerson(p3);

        // Create dummy tickets
        GenericTicket t1 = new GenericTicket(p1, PaymentStrategiesEnum.EXACT_SPLIT);
        Map<Person, Float> cpp1 = new HashMap<>();
        cpp1.put(p2, 5f);
        cpp1.put(p3, 8f);
        t1.setCostPerPerson(cpp1);
        app.getTicketDB().addTicket(t1);

        GenericTicket t2 = new GenericTicket(p2, PaymentStrategiesEnum.EXACT_SPLIT);
        Map<Person, Float> cpp2 = new HashMap<>();
        cpp2.put(p1, 10f);
        cpp2.put(p3, 16.5f);
        t2.setCostPerPerson(cpp2);
        app.getTicketDB().addTicket(t2);

        GenericTicket t3 = new GenericTicket(p3, PaymentStrategiesEnum.EXACT_SPLIT);
        Map<Person, Float> cpp3 = new HashMap<>();
        cpp3.put(p1, 2.3f);
        cpp3.put(p2, 9f);
        t3.setCostPerPerson(cpp3);
        app.getTicketDB().addTicket(t3);

        // Calculate mutual debts between debtee & debtors
        debtMap = controller.getDebtMap();
    }

    @Test
    public void t_getDebtMap()
    {
        // Calculate mutual debts between debtee & debtors
        float returnValue;

        // p2 should owe p1 5 euro
        returnValue = debtMap.get(p1).get(p2);
        assertEquals("", 5f, returnValue, 0f);

        // p1 should owe p3 5.7 euro
        returnValue = debtMap.get(p3).get(p1);
        assertEquals("", 5.7f, returnValue, 0f);

        // p2 should owe p3 7.5 euro
        returnValue = debtMap.get(p3).get(p2);
        assertEquals("", 7.5f, returnValue, 0f);
    }

    @Test
    public void t_getSimplifiedDebtMap()
    {
        // Calculate mutual debts between debtees & debtors (one to many)
        float returnValue;

        // Simplify debtMap
        Map<Person, Map<Person, Float>> simplifiedDebtMap = controller.getSimplifiedDebtMap(debtMap);

        // p2 should owe p1 0 euro
        returnValue = simplifiedDebtMap.get(p1).get(p2);
        assertEquals("", 0f, returnValue, 0.1f);

        // p1 should owe p3 0.7 euro
        returnValue = simplifiedDebtMap.get(p3).get(p1);
        assertEquals("", 0.7f, returnValue, 0.1f);

        // p2 should owe p3 12.5 euro
        returnValue = simplifiedDebtMap.get(p3).get(p2);
        assertEquals("", 12.5f, returnValue, 0.1f);
    }
}
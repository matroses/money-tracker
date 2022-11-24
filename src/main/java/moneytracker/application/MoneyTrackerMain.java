package main.java.moneytracker.application;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;
import main.java.moneytracker.view.MainStage;

import java.util.HashMap;

public class MoneyTrackerMain extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MoneyTrackerApp moneyTracker = new MoneyTrackerApp();

        // Create dummy user
        Person p1 = new Person("Jens", "De Hoog");
        moneyTracker.getPeopleDB().addPerson(p1);
        Person p2 = new Person("Matthieu", "Larose");
        moneyTracker.getPeopleDB().addPerson(p2);
        Person p3 = new Person("Sigfried", "Seldeslachts");
        moneyTracker.getPeopleDB().addPerson(p3);

        // Create a dummy ticket
        /*Ticket ticket = new Ticket(p2, p3, TicketTypeEnum.RestaurantTicket, PaymentStrategiesEnum.EXACT_SPLIT, new HashMap<Person, Float>() {{
            put(p1, 10.0f);
            put(p2, 20.0f);
            put(p3, 30.0f);
        }});
        moneyTracker.getTicketDB().addTicket(ticket);*/

        // Start UI
        new MainStage(moneyTracker);
    }
}

package moneytracker.application;

import javafx.application.Application;
import javafx.stage.Stage;
import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;
import moneytracker.model.tickets.GenericTicket;
import moneytracker.view.MainStage;

import java.util.HashMap;

public class MoneyTrackerMain extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MoneyTrackerApp moneyTracker = new MoneyTrackerApp();

        // Create dummy user
        Person p1 = new Person("Jens", "De Hoog");
        moneyTracker.getPeopleDB().addPerson(p1);
        Person p2 = new Person("Matthieu", "Larose");
        moneyTracker.getPeopleDB().addPerson(p2);
        Person p3 = new Person("Sigfried", "Seldeslachts");
        moneyTracker.getPeopleDB().addPerson(p3);

        // Create a dummy ticket
        GenericTicket ticket = new GenericTicket(p1, PaymentStrategiesEnum.EXACT_SPLIT);
        ticket.setCostPerPerson(new HashMap<Person, Float>() {{
            put(p2, 10f);
            put(p3, 10f);
        }});
        moneyTracker.getTicketDB().addTicket(ticket);

        // Start UI
        new MainStage(moneyTracker);
    }
}

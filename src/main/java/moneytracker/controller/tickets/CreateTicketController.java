package main.java.moneytracker.controller.tickets;

import main.java.moneytracker.controller.Controller;
import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.model.Person;

import java.util.List;

public class CreateTicketController extends Controller {

    public CreateTicketController(MoneyTrackerApp moneyTracker) {
        super(moneyTracker);
    }

    public List<Person> getPeople() {
        return app.getPeopleDB().getAllPeople();
    }
}

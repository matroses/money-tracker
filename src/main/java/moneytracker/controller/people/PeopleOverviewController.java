package main.java.moneytracker.controller.people;

import main.java.moneytracker.controller.Controller;
import main.java.moneytracker.model.MoneyTrackerApp;
import main.java.moneytracker.model.Person;

import java.util.ArrayList;

public class PeopleOverviewController extends Controller {

    public PeopleOverviewController(MoneyTrackerApp moneyTracker) {
        super(moneyTracker);
    }

    public ArrayList<Person> getPeople() {
        return app.getPeopleDB().getAllPeople();
    }

    public void updatePerson(Person person) {
        app.getPeopleDB().addPerson(person);
    }
}

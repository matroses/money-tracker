package moneytracker.controller.people;

import moneytracker.controller.Controller;
import moneytracker.model.MoneyTrackerApp;
import moneytracker.model.Person;

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

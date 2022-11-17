package main.java.moneytracker.model.db;

import main.java.moneytracker.model.Person;

import java.util.HashMap;

public class PeopleDB {

    private static PeopleDB instance;

    private HashMap<String, Person> people;

    private PeopleDB() {
    }

    public static PeopleDB getInstance() {
        if (instance == null) {
            instance = new PeopleDB();
        }

        return instance;
    }

    public void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    public void removePerson(Person person) {
        people.remove(person.getId());
    }

    public Person getPerson(String id) {
        return people.get(id);
    }

    public Person findPersonByName(String firstName, String lastName) {
        for (Person person : people.values()) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }

        return null;
    }

}

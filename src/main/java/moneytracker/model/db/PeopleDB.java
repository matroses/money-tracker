package main.java.moneytracker.model.db;

import main.java.moneytracker.model.Person;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PeopleDB extends Database  {

    private static PeopleDB instance;

    private final HashMap<UUID, Person> people = new HashMap<>();

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private PeopleDB() {}

    public static PeopleDB getInstance() {
        if (instance == null) {
            instance = new PeopleDB();
        }

        return instance;
    }

    public void addPerson(Person person) {
        people.put(person.getId(), person);

        this.propertyChangeSupport.firePropertyChange("PERSON_ADDED", null, person);
    }

    public void removePerson(Person person) {
        people.remove(person.getId());

        this.propertyChangeSupport.firePropertyChange("PERSON_REMOVED", null, person);
    }

    public Person getPerson(UUID id) {
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

    public ArrayList<Person> getAllPeople() {
        return new ArrayList<>(people.values());
    }

    /**
     * Add a property change listener to this model.
     *
     * @param listener The listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}

package main.java.moneytracker.model.strategies;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomPersonPaysStrategy implements PaymentStrategy {

    @Override
    public Map<Person, Float> pay(Map<Person, Float> people, float total) {
        Map<Person, Float> returnMap = new HashMap<>();

        // Get a random person from the map
        // Set that person's cost to the total cost of the ticket
        // Return the map
        ArrayList<Person> personList = new ArrayList<>(people.keySet());

        // Get a random person from the map
        Random rand = new Random();
        Person randomPerson = personList.get((new Random()).nextInt(personList.size()));

        for (Person person: people.keySet()) {
            if(person == randomPerson) {
                returnMap.put(person, total);
            }
            else returnMap.put(person, 0f);
        }

        return returnMap;
    }
}

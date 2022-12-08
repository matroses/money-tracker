package main.java.moneytracker.model.strategies;

import main.java.moneytracker.model.Person;

import java.util.HashMap;
import java.util.Map;

public class EqualSplitStrategy implements PaymentStrategy {

    @Override
    public Map<Person, Float> pay(Map<Person, Float> people, float total) {
        float costPerPerson = total / people.size();
        Map<Person, Float> returnMap = new HashMap<>();

        for (Person person: people.keySet()) {
            returnMap.put(person, costPerPerson);
        }
        return returnMap;
    }
}

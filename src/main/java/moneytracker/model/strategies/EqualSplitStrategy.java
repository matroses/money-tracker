package moneytracker.model.strategies;

import moneytracker.model.Person;

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

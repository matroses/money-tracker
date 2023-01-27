package moneytracker.model.strategies;

import moneytracker.model.Person;

import java.util.Map;

public class ExactSplitStrategy implements PaymentStrategy {

    @Override
    public Map<Person, Float> pay(Map<Person, Float> people, float total) {
        return people;
    }
}

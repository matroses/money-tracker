package main.java.moneytracker.model.strategies;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;

import java.util.Map;

public class ExactSplitStrategy implements PaymentStrategy {

    @Override
    public Map<Person, Float> pay(Map<Person, Float> people, float total) {
        return people;
    }
}

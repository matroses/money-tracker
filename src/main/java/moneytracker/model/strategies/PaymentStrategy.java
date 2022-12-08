package main.java.moneytracker.model.strategies;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.Ticket;

import java.util.Map;

public interface PaymentStrategy {

    Map<Person, Float> pay(Map<Person, Float> enteredAmountPerPerson, float total);

}

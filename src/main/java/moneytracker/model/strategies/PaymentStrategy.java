package moneytracker.model.strategies;

import moneytracker.model.Person;

import java.util.Map;

public interface PaymentStrategy {

    Map<Person, Float> pay(Map<Person, Float> enteredAmountPerPerson, float total);

}

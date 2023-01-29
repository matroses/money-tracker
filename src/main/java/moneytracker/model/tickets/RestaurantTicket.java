package moneytracker.model.tickets;

import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RestaurantTicket extends Ticket {

    private Map<Person, Float> foodPricePerPerson = new HashMap<>(), beveragePricePerPerson = new HashMap<>(), otherCostsPerPerson = new HashMap<>();

    public RestaurantTicket(Person paidBy, PaymentStrategiesEnum paymentStrategyEnum) throws IllegalArgumentException {
        super(paidBy, paymentStrategyEnum);
    }


    @Override
    public Map<Person, Float> getCostPerPerson() {
        Map<Person, Float> costPerPerson = new HashMap<>();

        // Get all the unique people from keysets
        Set<Person> people = new HashSet<>();
        people.addAll(foodPricePerPerson.keySet());
        people.addAll(otherCostsPerPerson.keySet());
        people.addAll(beveragePricePerPerson.keySet());

        // For each person, add the cost of each category
        for (Person person : people) {
            float cost = 0;

            if (foodPricePerPerson.containsKey(person)) {
                cost += foodPricePerPerson.get(person);
            }

            if (otherCostsPerPerson.containsKey(person)) {
                cost += otherCostsPerPerson.get(person);
            }

            if (beveragePricePerPerson.containsKey(person)) {
                cost += beveragePricePerPerson.get(person);
            }

            costPerPerson.put(person, cost);
        }

        return costPerPerson;
    }

    public Map<Person, Float> getFoodPricePerPerson() {
        return foodPricePerPerson;
    }

    public RestaurantTicket setFoodPricePerPerson(Map<Person, Float> foodPricePerPerson) {
        this.foodPricePerPerson = foodPricePerPerson;
        return this;
    }

    public Map<Person, Float> getBeveragePricePerPerson() {
        return beveragePricePerPerson;
    }

    public RestaurantTicket setBeveragePricePerPerson(Map<Person, Float> beveragePricePerPerson) {
        this.beveragePricePerPerson = beveragePricePerPerson;
        return this;
    }

    public Map<Person, Float> getOtherCostsPerPerson() {
        return otherCostsPerPerson;
    }

    public RestaurantTicket setOtherCostsPerPerson(Map<Person, Float> otherCostsPerPerson) {
        this.otherCostsPerPerson = otherCostsPerPerson;
        return this;
    }
}

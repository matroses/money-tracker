package moneytracker.model.tickets;

import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AirplaneTicket extends Ticket {

    private Map<Person, Float> seatPricePerPerson = new HashMap<>(), baggagePricePerPerson = new HashMap<>(), foodPricePerPerson = new HashMap<>(), otherCostsPerPerson = new HashMap<>();

    public AirplaneTicket(Person paidBy, PaymentStrategiesEnum paymentStrategyEnum) throws IllegalArgumentException {
        super(paidBy, paymentStrategyEnum);
    }


    @Override
    public Map<Person, Float> getCostPerPerson() {
        Map<Person, Float> costPerPerson = new HashMap<>();

        // Get all the unique people from keysets
        Set<Person> people = new HashSet<>();
        people.addAll(seatPricePerPerson.keySet());
        people.addAll(baggagePricePerPerson.keySet());
        people.addAll(foodPricePerPerson.keySet());
        people.addAll(otherCostsPerPerson.keySet());

        // For each person, add the cost of each category
        for (Person person : people) {
            float cost = 0;

            if (seatPricePerPerson.containsKey(person)) {
                cost += seatPricePerPerson.get(person);
            }

            if (baggagePricePerPerson.containsKey(person)) {
                cost += baggagePricePerPerson.get(person);
            }

            if (foodPricePerPerson.containsKey(person)) {
                cost += foodPricePerPerson.get(person);
            }

            if (otherCostsPerPerson.containsKey(person)) {
                cost += otherCostsPerPerson.get(person);
            }

            costPerPerson.put(person, cost);
        }

        return costPerPerson;
    }

    @Override
    public float getTotal() {
        Map<Person, Float> costPerPerson = getCostPerPerson();

        float total = 0;
        for (Float cost : costPerPerson.values()) {
            total += cost;
        }

        return total;
    }

    public Map<Person, Float> getSeatPricePerPerson() {
        return seatPricePerPerson;
    }

    public AirplaneTicket setSeatPricePerPerson(Map<Person, Float> seatPricePerPerson) {
        this.seatPricePerPerson = seatPricePerPerson;
        return this;
    }

    public Map<Person, Float> getBaggagePricePerPerson() {
        return baggagePricePerPerson;
    }

    public AirplaneTicket setBaggagePricePerPerson(Map<Person, Float> baggagePricePerPerson) {
        this.baggagePricePerPerson = baggagePricePerPerson;
        return this;
    }

    public Map<Person, Float> getFoodPricePerPerson() {
        return foodPricePerPerson;
    }

    public AirplaneTicket setFoodPricePerPerson(Map<Person, Float> foodPricePerPerson) {
        this.foodPricePerPerson = foodPricePerPerson;
        return this;
    }

    public Map<Person, Float> getOtherCostsPerPerson() {
        return otherCostsPerPerson;
    }

    public AirplaneTicket setOtherCostsPerPerson(Map<Person, Float> otherCostsPerPerson) {
        this.otherCostsPerPerson = otherCostsPerPerson;
        return this;
    }
}

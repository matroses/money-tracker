package moneytracker.model.tickets;

import moneytracker.model.Person;
import moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.HashMap;
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
        /*Set<Person> people = seatPricePerPerson.keySet()

        // Get all people involved
        for (Person person : seatPricePerPerson.keySet()) {
            costPerPerson.put(person, 0f);
        }
        for (Person person : baggagePricePerPerson.keySet()) {
            costPerPerson.put(person, 0f);
        }
        for (Person person : foodPricePerPerson.keySet()) {
            costPerPerson.put(person, 0f);
        }
        for (Person person : otherCostsPerPerson.keySet()) {
            costPerPerson.put(person, 0f);
        }*/

        // For each person, add the cost of each category


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

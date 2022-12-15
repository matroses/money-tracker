package main.java.moneytracker.model.tickets;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;

import java.util.HashMap;
import java.util.Map;

public class AirplaneTicket extends Ticket {

    private Map<Person, Float> seatPricePerPerson = new HashMap<>(), baggagePricePerPerson, foodPricePerPerson, otherCostsPerPerson;

    public AirplaneTicket(Person paidBy, PaymentStrategiesEnum paymentStrategyEnum) throws IllegalArgumentException {
        super(paidBy, paymentStrategyEnum);
    }


    @Override
    public Map<Person, Float> getCostPerPerson() {
        Map<Person, Float> costPerPerson = new HashMap<>();

        for (Person person : seatPricePerPerson.keySet()) {
            float cost = 0;

            cost += seatPricePerPerson.get(person);
            cost += baggagePricePerPerson.get(person);
            cost += foodPricePerPerson.get(person);
            cost += otherCostsPerPerson.get(person);

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
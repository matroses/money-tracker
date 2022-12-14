package main.java.moneytracker.model.tickets;

import main.java.moneytracker.model.Person;
import main.java.moneytracker.model.enums.PaymentStrategiesEnum;
import main.java.moneytracker.model.enums.TicketTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class AirplaneTicket extends Ticket {

    private Map<Person, Float> seatPricePerPerson, baggagePricePerPerson, foodPricePerPerson, otherCostsPerPerson;

    public AirplaneTicket(
            Person paidBy, Person createdBy, PaymentStrategiesEnum paymentStrategyEnum, Map<Person, Float> seatPricePerPerson,
            Map<Person, Float> baggagePricePerPerson, Map<Person, Float> foodPricePerPerson, Map<Person, Float> otherCostsPerPerson
    ) throws IllegalArgumentException {
        super(paidBy, createdBy, paymentStrategyEnum);
        this.setSeatPricePerPerson(seatPricePerPerson);
        this.setBaggagePricePerPerson(baggagePricePerPerson);
        this.setFoodPricePerPerson(foodPricePerPerson);
        this.setOtherCostsPerPerson(otherCostsPerPerson);
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

    public void setSeatPricePerPerson(Map<Person, Float> seatPricePerPerson) {
        this.seatPricePerPerson = seatPricePerPerson;
    }

    public Map<Person, Float> getBaggagePricePerPerson() {
        return baggagePricePerPerson;
    }

    public void setBaggagePricePerPerson(Map<Person, Float> baggagePricePerPerson) {
        this.baggagePricePerPerson = baggagePricePerPerson;
    }

    public Map<Person, Float> getFoodPricePerPerson() {
        return foodPricePerPerson;
    }

    public void setFoodPricePerPerson(Map<Person, Float> foodPricePerPerson) {
        this.foodPricePerPerson = foodPricePerPerson;
    }

    public Map<Person, Float> getOtherCostsPerPerson() {
        return otherCostsPerPerson;
    }

    public void setOtherCostsPerPerson(Map<Person, Float> otherCostsPerPerson) {
        this.otherCostsPerPerson = otherCostsPerPerson;
    }
}

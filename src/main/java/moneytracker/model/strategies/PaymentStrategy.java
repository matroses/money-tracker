package main.java.moneytracker.model.strategies;

import main.java.moneytracker.model.Ticket;

public interface PaymentStrategy {

    void pay(Ticket ticket);

}

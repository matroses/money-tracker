package main.java.moneytracker.model.enums;

import main.java.moneytracker.model.strategies.EqualSplitStrategy;
import main.java.moneytracker.model.strategies.ExactSplitStrategy;
import main.java.moneytracker.model.strategies.PaymentStrategy;

public enum PaymentStrategiesEnum {

    EQUAL_SPLIT("Evenly split the bill", EqualSplitStrategy.class),
    EXACT_SPLIT("Split the bill exactly", ExactSplitStrategy.class);
    // PERCENTAGE_SPLIT;

    private final String description;
    private final Class<? extends PaymentStrategy> strategyClass;

    PaymentStrategiesEnum(String description, Class<? extends PaymentStrategy> strategyClass) {
        this.description = description;
        this.strategyClass = strategyClass;
    }

    public String getDescription() {
        return description;
    }

    public Class<? extends PaymentStrategy> getStrategyClass() {
        return strategyClass;
    }
}

package moneytracker.model.enums;

import javafx.util.StringConverter;
import moneytracker.model.strategies.EqualSplitStrategy;
import moneytracker.model.strategies.ExactSplitStrategy;
import moneytracker.model.strategies.PaymentStrategy;

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

    /**
     * Converts the enum to a string for a JavaFX ChoiceBox or equivalent
     */
    public static StringConverter<PaymentStrategiesEnum> getConverter() {
        return new StringConverter<PaymentStrategiesEnum>() {
            @Override
            public String toString(PaymentStrategiesEnum object) {
                return object.getDescription();
            }

            @Override
            public PaymentStrategiesEnum fromString(String string) {
                return null;
            }
        };
    }
}

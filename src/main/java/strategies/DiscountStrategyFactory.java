package strategies;

import Enums.PassengerType;
import models.passenger.Passenger;

public class DiscountStrategyFactory {

    public DiscountStrategy getStrategy(PassengerType type)
    {
        switch (type) {
            case STANDARD -> {
                return new StandardDiscountStrategy();
            }
            case GOLD -> {
                return new GoldDiscountStrategy();
            }
            case PREMIUM -> {
                return new PremiumDiscountStrategy();
            }
        };

        throw new RuntimeException("Invalid Passenger Type");
    }
}

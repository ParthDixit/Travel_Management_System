package services;

import Enums.PassengerType;
import strategies.DiscountStrategy;
import strategies.DiscountStrategyFactory;

public class DiscountService {

    private final DiscountStrategyFactory discountStrategyFactory;
    private static DiscountService discountService;

    private DiscountService(){
        this.discountStrategyFactory = new DiscountStrategyFactory();
    }

    public static DiscountService getInstance(){
        if(discountService == null)
        {
            synchronized (DiscountService.class)
            {
                if(discountService == null)
                    discountService = new DiscountService();
            }
        }

        return discountService;
    }

    public Integer discount(PassengerType type, Integer cost)
    {
        DiscountStrategy discountStrategy = discountStrategyFactory.getStrategy(type);

        return discountStrategy.discount(cost);
    }
}

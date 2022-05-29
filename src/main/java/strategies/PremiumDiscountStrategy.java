package strategies;

public class PremiumDiscountStrategy implements DiscountStrategy{

    @Override
    public Integer discount(Integer price) {
        return 0;
    }
}

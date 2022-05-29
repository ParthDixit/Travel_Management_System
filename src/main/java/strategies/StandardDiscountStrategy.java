package strategies;

public class StandardDiscountStrategy implements DiscountStrategy{
    @Override
    public Integer discount(Integer price) {
        return price;
    }
}

package strategies;

public class GoldDiscountStrategy implements DiscountStrategy{
    @Override
    public Integer discount(Integer price) {
        return  (int) (.9*price);
    }
}

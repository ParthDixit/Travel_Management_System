package services;

import Enums.PassengerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    DiscountService service = DiscountService.getInstance();

    @Test
    @DisplayName("Discount Strategy test")
    void discount() {

        Integer cost = service.discount(PassengerType.STANDARD, 100);
        assertEquals(100, cost);

        cost = service.discount(PassengerType.GOLD, 100);
        assertEquals(90, cost);

        cost = service.discount(PassengerType.PREMIUM, 100);
        assertEquals(0, cost);

    }
}
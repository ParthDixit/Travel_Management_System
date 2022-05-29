package repositories;

import Enums.PassengerType;
import models.passenger.Passenger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PassengerRepositoryTest {

    Passenger passenger = Passenger.getBuilder().setName("jon").setBalance(10)
            .setNumber(1).setPassengerType(PassengerType.STANDARD).build();
    PassengerRepository repository = PassengerRepository.getInstance();

    @Test
    @Order(1)
    void save() {
        Passenger returned = repository.save(passenger);
        assertEquals(passenger, returned);
        assertNull(repository.save(passenger));
    }

    @Test
    @Order(2)
    void findPassengerByNumber() {

        Passenger returned = repository.findPassengerByNumber(passenger.getNumber());
        assertEquals(passenger.getNumber(), returned.getNumber());
        assertNull(repository.findPassengerByNumber(123));
    }
}
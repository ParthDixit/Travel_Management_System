package repositories;

import models.destination.Destination;
import models.travelPackage.Package;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DestinationRepositoryTest {

    Destination destination = Destination.getBuilder().setName("London").build();
    DestinationRepository repository = DestinationRepository.getInstance();

    @Test
    @Order(1)
    void save() {
        assertEquals(destination, repository.save(destination));
        assertNull(repository.save(destination));
    }

    @Test
    void findDestinationByName() {
        Destination returned = repository.findDestinationByName(destination.getName());

        assertEquals(destination.getName(), returned.getName());
        assertNull(repository.findDestinationByName("Not present"));
    }
}
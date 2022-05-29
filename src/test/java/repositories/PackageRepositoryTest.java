package repositories;

import Enums.PassengerType;
import models.passenger.Passenger;
import models.travelPackage.Package;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PackageRepositoryTest {

    Package travelPackage =  Package.getBuilder()
            .setName("Combo")
                .setCapacity(10)
                .build();

    PackageRepository repository = PackageRepository.getInstance();

    @Test
    @Order(1)
    void save() {
        assertEquals(travelPackage, repository.save(travelPackage));
        assertNull(repository.save(travelPackage));
    }

    @Test
    @Order(2)
    void findPackageByName() {

        Package returned = repository.findPackageByName(travelPackage.getName());
        assertEquals(travelPackage.getName(), returned.getName());
        assertNull(repository.findPackageByName("Not present"));
    }
}
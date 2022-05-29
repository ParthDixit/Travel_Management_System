package Controllers;

import dtos.*;
import models.passenger.Passenger;
import models.travelPackage.Package;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import services.PackageService;
import statuses.DestinationStatus;
import statuses.PackageStatus;
import statuses.PassengerStatus;

import static org.junit.jupiter.api.Assertions.*;
import static statuses.PassengerStatus.PACKAGE_ALREADY_ENROLLED;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PackageControllerTest {

    PackageController controller = new PackageController();

    @Test
    @Order(1)
    void registerPackage() {
        PackageRequestDTO packageRequestDTO = new PackageRequestDTO();
        packageRequestDTO.setName("Maha");
        packageRequestDTO.setCapacity(20);

        ResponseDTO<PackageResponseDTO> responseDTO = controller.registerPackage(packageRequestDTO);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO = controller.registerPackage(packageRequestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());

        assertEquals(PackageStatus.EXISTS, responseDTO.getMessage());
    }

    @Test
    @Order(2)
    void addPassenger() {
        PackagePassengerAdditionRequestDTO requestDTO
                = new PackagePassengerAdditionRequestDTO();

        new PassengerControllerTest().registerPassenger();

        requestDTO.setPassengerNumber(1);
        requestDTO.setPackageName("Maha");

        ResponseDTO<PackagePassengerAdditionResponseDTO> responseDTO = controller
                        .addPassenger(requestDTO);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO = controller.addPassenger(requestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());

        assertEquals(PACKAGE_ALREADY_ENROLLED, responseDTO.getMessage());
    }

    @Test
    @Order(3)
    void addDestination() {
        PackageDestinationAdditionRequestDTO requestDTO
                = new PackageDestinationAdditionRequestDTO();

        requestDTO.setDestinationName("London");
        requestDTO.setPackageName("Maha");

        new DestinationControllerTest().registerDestination();

        ResponseDTO<PackageDestinationAdditionResponseDTO> responseDTO
                    = controller.addDestination(requestDTO);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO = controller.addDestination(requestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());
        assertEquals(DestinationStatus.EXISTS, responseDTO.getMessage());
    }
}
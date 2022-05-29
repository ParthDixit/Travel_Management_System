package Controllers;

import dtos.*;
import models.activity.Activity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import statuses.ActivityStatus;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivityControllerTest {

    ActivityController controller = new ActivityController();

    @Test
    @Order(1)
    void registerActivity() {
        ActivityRequestDTO requestDTO = new ActivityRequestDTO();
        requestDTO.setName("Boating");
        requestDTO.setCost(25);
        requestDTO.setCapacity(5);
        requestDTO.setDescription("boating in river");

        ResponseDTO<ActivityResponseDTO> responseDTO = controller.registerActivity(requestDTO);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO = controller.registerActivity(requestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());
        assertEquals(ActivityStatus.EXISTS, responseDTO.getMessage());

    }

    @Test
    @Order(2)
    void addPassenger() {

        ActivityPassengerAdditionRequestDTO requestDTO = new ActivityPassengerAdditionRequestDTO();
        requestDTO.setActivityName("Boating");
        requestDTO.setPassengerNumber(1);

        new PackageControllerTest().registerPackage();
        new PackageControllerTest().addPassenger();
        new PackageControllerTest().addDestination();

        DestinationActivityAdditionRequestDTO request = new DestinationActivityAdditionRequestDTO();
        request.setActivityName("Boating");
        request.setDestinationName("London");

        DestinationController destinationController = new DestinationController();

        destinationController.addActivity(request);

        ResponseDTO<ActivityPassengerAdditionResponseDTO> responseDTO
                        = controller.addPassenger(requestDTO);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO = controller.addPassenger(requestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());
        assertEquals(ActivityStatus.EXISTS, responseDTO.getMessage());
    }
}
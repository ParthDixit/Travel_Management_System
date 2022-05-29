package Controllers;

import dtos.*;
import models.activity.Activity;
import models.destination.Destination;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import statuses.ActivityStatus;
import statuses.DestinationStatus;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DestinationControllerTest {

    DestinationController controller = new DestinationController();

    @Test
    @Order(1)
    void registerDestination() {

        DestinationRequestDTO requestDTO = new DestinationRequestDTO();
        requestDTO.setName("London");

        ResponseDTO<DestinationResponseDTO> responseDTO = controller.registerDestination(requestDTO);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO = controller.registerDestination(requestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());
        assertEquals(DestinationStatus.EXISTS, responseDTO.getMessage());
    }

    @Test
    @Order(2)
    void addActivity() {

        DestinationActivityAdditionRequestDTO requestDTO = new DestinationActivityAdditionRequestDTO();
        requestDTO.setActivityName("Boating");
        requestDTO.setDestinationName("London");

        new ActivityControllerTest().registerActivity();

        ResponseDTO<DestinationActivityAdditionResponseDTO> responseDTO
                        = controller.addActivity(requestDTO);


        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());


        responseDTO = controller.addActivity(requestDTO);

        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());
        assertEquals(ActivityStatus.ALREADY_ASSIGNED, responseDTO.getMessage());
    }
}
package Controllers;

import Enums.PassengerType;
import dtos.PassengerRequestDTO;
import dtos.PassengerResponseDTO;
import dtos.ResponseDTO;
import dtos.ResponseStatus;
import models.passenger.Passenger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import statuses.PassengerStatus;

import static org.junit.jupiter.api.Assertions.*;

class PassengerControllerTest {

    @Test
    @DisplayName("Register Passenger.")
    void registerPassenger() {

        PassengerRequestDTO passenger = new PassengerRequestDTO();
        passenger.setName("kamal");
        passenger.setNumber(1);
        passenger.setBalance(50);
        passenger.setPassengerType(PassengerType.STANDARD);
        ResponseDTO<PassengerResponseDTO> responseDTO = new PassengerController().registerPassenger(passenger);

        assertEquals(ResponseStatus.SUCCESS, responseDTO.getStatus());

        responseDTO =  new PassengerController().registerPassenger(passenger);
        assertEquals(ResponseStatus.FAILURE, responseDTO.getStatus());
        assertEquals(PassengerStatus.EXISTS, responseDTO.getMessage());

    }

}
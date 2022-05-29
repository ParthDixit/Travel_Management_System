package dtos;

import lombok.Getter;
import lombok.Setter;
import models.passenger.Passenger;

import java.util.List;

@Getter
@Setter
public class PackagePassengerAdditionResponseDTO {

    private List<Passenger> passengerList;
}

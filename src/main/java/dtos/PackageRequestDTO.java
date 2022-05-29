package dtos;

import lombok.Getter;
import lombok.Setter;
import models.destination.Destination;
import models.passenger.Passenger;

import java.util.List;

@Getter
@Setter
public class PackageRequestDTO {

    private String name;
    private Integer capacity;
    private List<Destination> destinationList;
    private List<Passenger> passengerList;
}

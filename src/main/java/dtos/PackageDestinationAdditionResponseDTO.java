package dtos;

import lombok.Getter;
import lombok.Setter;
import models.destination.Destination;

import java.util.List;

@Getter
@Setter
public class PackageDestinationAdditionResponseDTO {

    private List<Destination> destinationList;
}

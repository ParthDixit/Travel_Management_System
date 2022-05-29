package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackagePassengerAdditionRequestDTO {

    private String packageName;
    private Integer passengerNumber;
}

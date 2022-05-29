package dtos;

import Enums.PassengerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerRequestDTO {

    private String name;
    private Integer number;
    private Integer balance;
    private PassengerType passengerType;
}

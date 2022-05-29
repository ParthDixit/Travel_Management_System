package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityPassengerAdditionRequestDTO {

    private String activityName;
    private Integer passengerNumber;
}

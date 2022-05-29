package dtos;

import lombok.Getter;
import lombok.Setter;
import models.activity.ActivityRecord;
import models.passenger.Passenger;

import java.util.List;

@Getter
@Setter
public class ActivityPassengerAdditionResponseDTO {
    List<ActivityRecord> activityRecordList;
}

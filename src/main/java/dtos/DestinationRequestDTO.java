package dtos;

import lombok.Getter;
import lombok.Setter;
import models.activity.Activity;

import java.util.List;

@Getter
@Setter
public class DestinationRequestDTO {

    private String name;
    private List<Activity> activityList;

}

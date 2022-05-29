package dtos;

import lombok.Getter;
import lombok.Setter;
import models.activity.Activity;

import java.util.List;

@Getter
@Setter
public class DestinationActivityAdditionResponseDTO {

    private List<Activity> activityList;
}

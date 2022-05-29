package models.activity;

import lombok.Getter;
import lombok.Setter;
import models.BaseModel;
import models.destination.Destination;

@Getter
@Setter
public class ActivityRecord extends BaseModel {

    private String name;
    private String destination;
    private Integer pricePaid;
}

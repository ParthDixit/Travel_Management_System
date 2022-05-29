package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityRequestDTO {

    private String name;
    private String description;
    private Integer cost;
    private Integer capacity;
}

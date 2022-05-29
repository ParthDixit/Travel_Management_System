package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageDestinationAdditionRequestDTO {

    private String packageName;
    private String destinationName;
}

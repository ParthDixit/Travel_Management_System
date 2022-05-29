package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO<T> {

    private ResponseStatus status;
    private T data;
    private String message;
}

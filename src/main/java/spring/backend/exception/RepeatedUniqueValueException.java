package spring.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class RepeatedUniqueValueException extends RuntimeException {

    public RepeatedUniqueValueException(String message) {
        super(message);
    }
}

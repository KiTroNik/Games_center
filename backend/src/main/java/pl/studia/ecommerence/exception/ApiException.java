package pl.studia.ecommerence.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class ApiException {
    private final String message;

    public ApiException(String message) {
        this.message = message;
    }
}

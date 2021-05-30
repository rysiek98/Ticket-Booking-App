package pl.touk.ticketBooking.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception){
        return new ResponseEntity<>("INVALID INPUT DATA", BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    private ResponseEntity<String> handleIOException(IOException exception){
        return new ResponseEntity<>("CONNECTION ERROR", BAD_REQUEST);
    }

    @ExceptionHandler(InterruptedException.class)
    private ResponseEntity<String> handleInterruptedException(InterruptedException exception){
        return new ResponseEntity<>("NOT FOUND", NOT_FOUND);
    }

    @ExceptionHandler(DateTimeParseException.class)
    private ResponseEntity<String> handleDateTimeParseException(DateTimeParseException exception){
        return new ResponseEntity<>("INVALID INPUT DATA", BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    private ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception){
        return new ResponseEntity<>("INVALID INPUT DATA", BAD_REQUEST);
    }

}

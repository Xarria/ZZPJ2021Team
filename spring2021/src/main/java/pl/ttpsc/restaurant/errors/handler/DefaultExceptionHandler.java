package pl.ttpsc.restaurant.errors.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.errors.OrderNotFoundException;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({MealNotFoundException.class, OrderNotFoundException.class})
    public ResponseEntity handleNotFoundException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
    }
}

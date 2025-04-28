package rti.glitch.br.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rti.glitch.br.exception.JsonNodeGetException;

@ControllerAdvice
@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(JsonNodeGetException.class)
    public ResponseEntity<?> handleJsonNodeGetException(JsonNodeGetException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

package com.next.travel.auth_service.handler;


import com.next.travel.auth_service.exception.DuplicateException;
import com.next.travel.auth_service.exception.InUseException;
import com.next.travel.auth_service.exception.InvalidException;
import com.next.travel.auth_service.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobeVideExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(InUseException.class)
    public ResponseEntity<?> handleInUseException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInValidException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFountException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

package com.stackroute.muzix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<?> handleTrackAlreadyExistsException(TrackAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<?> handleTrackNotFoundException(TrackNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<?> handleHttpServerException(HttpServerErrorException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }
}

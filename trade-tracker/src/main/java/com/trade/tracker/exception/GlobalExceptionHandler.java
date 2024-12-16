package com.trade.tracker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
    	ex.printStackTrace();
        LOGGER.error("An error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
    	ex.printStackTrace();
        LOGGER.error("Invalid input: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Invalid input provided.", HttpStatus.BAD_REQUEST);
    }

    // Handle validation exceptions (you can also handle specific ones like MethodArgumentNotValidException if needed)
    @ExceptionHandler(org.springframework.validation.BindException.class)
    public ResponseEntity<String> handleValidationException(org.springframework.validation.BindException ex) {
    	ex.printStackTrace();
        LOGGER.error("Validation error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("Input validation failed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

package com.atharva.pwise.exception;

import com.atharva.pwise.entity.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> catchResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ErrorResponse errorResponse = resourceNotFoundException.getErrorResponse();
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> catchDefaultException(Exception exception) {
        LOGGER.error("Internal server error : ", exception);
        return new ResponseEntity<>(ErrorResponse.builder().message(exception.getLocalizedMessage()).errorType(exception.getClass().getName()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

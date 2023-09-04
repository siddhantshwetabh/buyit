package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception interceptor class to handle all the exceptions.
 *
 * @author harshul.rathore
 */

@RestControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceDataException.class)
    public ResponseEntity<Object> handleResourceDataException(ResourceDataException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceServerException.class)
    public ResponseEntity<Object> handleResourceServerException(ResourceServerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MalformedRequestBodyException.class)
    public ResponseEntity<Object> handleMalformedRequestBodyException(MalformedRequestBodyException ex) {
        MalformedRequestSchema malformedRequestErrorResponse = new MalformedRequestSchema(ex.getErrorCode(),
                ex.getMessage(), ex.getFieldsInError());
        return new ResponseEntity<>(malformedRequestErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
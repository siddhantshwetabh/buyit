package com.order.exception;

/**
 * Exception used to indicate that the requested object instance was not found.
 * Exception class.
 *
 * @author harshul.rathore
 */

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode;
    private final String message;

    public ResourceNotFoundException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
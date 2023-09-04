package com.user.exception;

/**
 * Error response class.
 *
 * @author harshul.rathore
 */

public class ErrorResponse {

    private int errorCode;
    private String message;

    // Constructors
    public ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse() {
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getters
    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
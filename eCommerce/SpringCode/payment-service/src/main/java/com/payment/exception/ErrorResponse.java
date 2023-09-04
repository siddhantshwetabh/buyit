package com.payment.exception;

public class ErrorResponse {
    private int errorCode;
    private String message;

    // Constructors
    public ErrorResponse() {
    }

    public ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getters and Setters
    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
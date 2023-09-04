package com.payment.exception;

/**
 * Exception indicating that something went wrong in server.
 *
 * @author raghavaiah.madarasu
 */
public class ResourceServerException extends RuntimeException {
    private static final long serialVersionUID = -7760249187135099565L;

    private final int errorCode;
    private final String message;

    // Constructors
    public ResourceServerException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }



    // Getters and Setters
    public int getErrorCode() {
        return errorCode;
    }


    @Override
    public String getMessage() {
        return message;
    }

}

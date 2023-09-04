package com.payment.exception;

/**
 * Exception indicating bad data was supplied.
 */
public class ResourceDataException extends RuntimeException {
    private static final long serialVersionUID = -428699693116525526L;

    private final int errorCode;
    private final String message;

    public ResourceDataException(int errorCode, String message) {
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

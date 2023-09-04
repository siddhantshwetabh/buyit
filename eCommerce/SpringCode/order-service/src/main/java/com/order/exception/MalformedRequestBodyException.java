package com.order.exception;

import java.util.List;

public class MalformedRequestBodyException extends RuntimeException {

    private static final long serialVersionUID = 1899813094422569639L;

    private final Integer errorCode;
    private final String message;
    private final List<MalformedRequestErrorDetails> fieldsInError;

    public MalformedRequestBodyException(Integer errorCode, String message,
                                         List<MalformedRequestErrorDetails> fieldsInError) {
        this.errorCode = errorCode;
        this.message = message;
        this.fieldsInError = fieldsInError;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<MalformedRequestErrorDetails> getFieldsInError() {
        return fieldsInError;
    }
}

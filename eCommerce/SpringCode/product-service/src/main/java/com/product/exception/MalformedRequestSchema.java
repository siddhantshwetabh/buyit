package com.product.exception;

import java.io.Serializable;
import java.util.List;

public class MalformedRequestSchema implements Serializable {

    private static final long serialVersionUID = 1610598754693517036L;

    private Integer errorCode;
    private String message;
    private List<MalformedRequestErrorDetails> fieldsInError;

    public MalformedRequestSchema(Integer errorCode, String message, List<MalformedRequestErrorDetails> fieldsInError) {
        this.errorCode = errorCode;
        this.message = message;
        this.fieldsInError = fieldsInError;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public List<MalformedRequestErrorDetails> getFieldsInError() {
        return fieldsInError;
    }
}

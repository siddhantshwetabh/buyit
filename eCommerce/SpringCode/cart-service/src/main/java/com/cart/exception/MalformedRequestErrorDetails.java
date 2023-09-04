package com.cart.exception;

import java.io.Serializable;

public class MalformedRequestErrorDetails implements Serializable {

    private static final long serialVersionUID = 5601838121229729630L;

    private String fieldName;
    private String error;
    private String userAction;

    public MalformedRequestErrorDetails(String fieldName, String error, String userAction) {
        this.fieldName = fieldName;
        this.error = error;
        this.userAction = userAction;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getUserAction() {
        return userAction;
    }

    public String getError() {
        return error;
    }
}

package com.julio.web.handler;

public class FieldMandatoryException extends BusinessException {
    public FieldMandatoryException(String field) {
        super("The field %s is mandatory", field);
    }

    public FieldMandatoryException(String message, Object... params) {
        super(message, params);
    }
}

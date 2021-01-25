package com.training.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessages {

    NULL_ID("Id can not be null"),
    NULL_DELIVERY_OBJECT("Delivery can not be null"),
    NULL_STATUS("Status can not be null");

    private final String errorMessage;
    public String getErrorMessage() {
        return errorMessage;
    }

}

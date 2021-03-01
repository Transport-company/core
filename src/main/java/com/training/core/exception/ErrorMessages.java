package com.training.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessages {

    NULL_ID("Id can not be null"),
    NULL_ADDRESS_OBJECT("Address can not be null"),
    NULL_CARGO_OBJECT("Cargo can not be null"),
    NULL_CLIENT_OBJECT("Client can not be null"),
    NULL_DELIVERY_OBJECT("Delivery can not be null"),
    NULL_STATUS("Status can not be null"),
    NULL_TRACKING_NUMBER("Tracking number can not be null"),
    NULL_EMAIL("Email can not be null"),
    NULL_CHEQUE_OBJECT("Cheque can not be null"),
    NULL_DATE("Date can not be null"),
    NULL_TARIFF_OBJECT("Tariff can not be null"),
    NOT_GREATER_ZERO_DISTANCE("Distance must be greater than zero"),
    NOT_GREATER_ZERO_WEIGHT("Weight must be greater than zero"),
    NOT_GREATER_ZERO_VOLUME("Volume must be greater than zero");

    private final String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

}

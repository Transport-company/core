package com.training.core.util;

import com.training.core.dto.request.AddressRequest;

public class TestAddressRequests {
    private TestAddressRequests() {
    }

    public static AddressRequest firstSendingAdress() {
        return AddressRequest.builder()
                .region("Krasnodarskiy kray")
                .city("Krrasnodar")
                .street("Krasnaya")
                .house("12")
                .apartment("1")
                .build();
    }

    public static AddressRequest firstShippingAdress() {
        return AddressRequest.builder()
                .region("Nizhegorodskaya oblast")
                .city("Nizhny Novgorod")
                .street("Artelnaya")
                .house("58")
                .apartment("30")
                .build();
    }

    public static AddressRequest secondShippingAdress() {
        return AddressRequest.builder()
                .region("Kostromskaya oblast")
                .city("Kostroma")
                .street("Lesnaya")
                .house("40")
                .apartment("3")
                .build();
    }
}
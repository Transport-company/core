package com.training.core.util;

import com.training.core.dto.request.ClientRequest;

import java.time.LocalDate;

public class TestClientRequests {
    private TestClientRequests() {
    }

    public static ClientRequest firstSender() {
        return ClientRequest.builder()
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthday(LocalDate.of(1970, 11, 20))
                .phoneNumber("80123456789")
                .email("Ivanov_II@gmail.com")
                .build();
    }

    public static ClientRequest firstRecipient() {
        return ClientRequest.builder()
                .lastName("Romashkin")
                .firstName("Igor")
                .middleName("Valentinovich")
                .birthday(LocalDate.of(1972, 9, 12))
                .phoneNumber("80123450001")
                .email("Romashkin_IV@gmail.com")
                .build();
    }

    public static ClientRequest secondRecipient() {
        return ClientRequest.builder()
                .lastName("Cvetkov")
                .firstName("Vitaliy")
                .middleName("Sergeevich")
                .birthday(LocalDate.of(1980, 4, 27))
                .phoneNumber("89003450002")
                .email("Cvetkov_VS@gmail.com")
                .build();
    }
}

package com.training.core.util;

import com.training.core.model.Delivery;

public class TestDelivery {
    private TestDelivery() {
    }

    public static Delivery first() {
        return Delivery.builder()
                .id(1L)
                .build();
    }

}

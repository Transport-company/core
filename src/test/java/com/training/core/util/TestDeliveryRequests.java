package com.training.core.util;

import com.training.core.dto.request.DeliveryRequest;
import com.training.core.model.DeliveryStatus;

import java.math.BigDecimal;

public class TestDeliveryRequests {
    private TestDeliveryRequests() {
    }

    public static DeliveryRequest first(boolean enabledNotifications) {
        return DeliveryRequest.builder()
                .enabledNotifications(enabledNotifications)
                .sum(new BigDecimal("0.00"))
                .isPaid(false)
                .status(DeliveryStatus.REGISTERED)
                .cargo(TestCargoRequests.first())
                .sender(TestClientRequests.firstSender())
                .recipient(TestClientRequests.firstRecipient())
                .sendingAddress(TestAddressRequests.firstSendingAdress())
                .shippingAddress(TestAddressRequests.firstShippingAdress())
                .build();
    }

    public static DeliveryRequest second() {
        return DeliveryRequest.builder()
                .enabledNotifications(true)
                .sum(new BigDecimal("0.00"))
                .isPaid(false)
                .status(DeliveryStatus.REGISTERED)
                .cargo(TestCargoRequests.second())
                .sender(TestClientRequests.firstSender())
                .recipient(TestClientRequests.secondRecipient())
                .sendingAddress(TestAddressRequests.firstSendingAdress())
                .shippingAddress(TestAddressRequests.secondShippingAdress())
                .build();
    }
}
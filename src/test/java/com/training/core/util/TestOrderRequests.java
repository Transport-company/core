package com.training.core.util;

import com.training.core.dto.request.OrderRequest;

public class TestOrderRequests {

    public TestOrderRequests(){
    }

    public static OrderRequest getOrderRequest(){
        return OrderRequest.builder()
                .enabledNotifications(true)
                .cargo(TestCargoRequests.first())
                .sender(TestClientRequests.firstSender())
                .recipient(TestClientRequests.firstRecipient())
                .sendingAddress(TestAddressRequests.firstSendingAdress())
                .shippingAddress(TestAddressRequests.firstShippingAdress())
                .build();
    }

    public static OrderRequest getOrderRequestForUpdate(){
        return OrderRequest.builder()
                .enabledNotifications(true)
                .cargo(TestCargoRequests.second())
                .sender(TestClientRequests.firstSender())
                .recipient(TestClientRequests.firstRecipient())
                .sendingAddress(TestAddressRequests.firstSendingAdress())
                .shippingAddress(TestAddressRequests.firstShippingAdress())
                .build();
    }
}

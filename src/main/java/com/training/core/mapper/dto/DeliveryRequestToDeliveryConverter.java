package com.training.core.mapper.dto;

import com.training.core.dto.request.DeliveryRequest;
import com.training.core.model.Delivery;
import org.springframework.core.convert.converter.Converter;

public class DeliveryRequestToDeliveryConverter implements Converter<DeliveryRequest, Delivery> {

    @Override
    public Delivery convert(DeliveryRequest deliveryRequest) {
        return Delivery.builder()
                .enabledNotifications(deliveryRequest.getEnabledNotifications())
                .sum(deliveryRequest.getSum())
                .trackingNumber(deliveryRequest.getTrackingNumber())
                .isPaid(deliveryRequest.getIsPaid())
                .status(deliveryRequest.getStatus())
                .cargo(deliveryRequest.getCargo())
                .sender(deliveryRequest.getSender())
                .recipient(deliveryRequest.getRecipient())
                .sendingAddress(deliveryRequest.getSendingAddress())
                .shippingAddress(deliveryRequest.getShippingAddress())
                .build();
    }

}

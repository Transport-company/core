package com.training.core.mapper.model;

import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.Delivery;
import org.springframework.core.convert.converter.Converter;

public class DeliveryToDeliveryResponseConverter implements Converter<Delivery, DeliveryResponse> {

    @Override
    public DeliveryResponse convert(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .enabledNotifications(delivery.getEnabledNotifications())
                .sum(delivery.getSum())
                .trackingNumber(delivery.getTrackingNumber())
                .isPaid(delivery.getIsPaid())
                .status(delivery.getStatus())
                .cargo(delivery.getCargo())
                .sender(delivery.getSender())
                .recipient(delivery.getRecipient())
                .sendingAddress(delivery.getSendingAddress())
                .shippingAddress(delivery.getShippingAddress())
                .created(delivery.getCreated())
                .updated(delivery.getUpdated())
                .build();
    }

}

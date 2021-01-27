package com.training.core.mapper.model;

import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class DeliveryToDeliveryResponseConverter implements Converter<Delivery, DeliveryResponse> {
    private final AddressToAddressResponceConverter toAddressResponceConverter;
    private final CargoToCargoResponseConverter toCargoResponseConverter;
    private final ClientToClientResponseConverter toClientResponseConverter;

    @Override
    public DeliveryResponse convert(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .enabledNotifications(delivery.getEnabledNotifications())
                .sum(delivery.getSum())
                .trackingNumber(delivery.getTrackingNumber())
                .isPaid(delivery.getIsPaid())
                .status(delivery.getStatus())
                .cargo(toCargoResponseConverter.convert(delivery.getCargo()))
                .sender(toClientResponseConverter.convert(delivery.getSender()))
                .recipient(toClientResponseConverter.convert(delivery.getRecipient()))
                .sendingAddress(toAddressResponceConverter.convert(delivery.getSendingAddress()))
                .shippingAddress(toAddressResponceConverter.convert(delivery.getShippingAddress()))
                .created(delivery.getCreated())
                .updated(delivery.getUpdated())
                .build();
    }

}

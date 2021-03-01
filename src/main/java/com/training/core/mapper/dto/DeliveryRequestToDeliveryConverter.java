package com.training.core.mapper.dto;

import com.training.core.dto.request.DeliveryRequest;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryRequestToDeliveryConverter implements Converter<DeliveryRequest, Delivery> {
    private final AddressRequestToAddressConverter toAddressConverter;
    private final CargoRequestToCargoConverter toCargoConverter;
    private final ClientRequestToClientConverter toCliendConverter;

    @Override
    public Delivery convert(DeliveryRequest deliveryRequest) {
        return Delivery.builder()
                .enabledNotifications(deliveryRequest.getEnabledNotifications())
                .sum(deliveryRequest.getSum())
                .trackingNumber(deliveryRequest.getTrackingNumber())
                .isPaid(deliveryRequest.getIsPaid())
                .status(deliveryRequest.getStatus())
                .cargo(toCargoConverter.convert(deliveryRequest.getCargo()))
                .sender(toCliendConverter.convert(deliveryRequest.getSender()))
                .recipient(toCliendConverter.convert(deliveryRequest.getRecipient()))
                .sendingAddress(toAddressConverter.convert(deliveryRequest.getSendingAddress()))
                .shippingAddress(toAddressConverter.convert(deliveryRequest.getShippingAddress()))
                .build();
    }

}

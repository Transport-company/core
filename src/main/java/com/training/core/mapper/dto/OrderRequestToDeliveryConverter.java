package com.training.core.mapper.dto;

import com.training.core.dto.request.OrderRequest;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import static com.training.core.model.DeliveryStatus.REGISTERED;

@RequiredArgsConstructor
public class OrderRequestToDeliveryConverter implements Converter<OrderRequest, Delivery> {
    private final AddressRequestToAddressConverter toAddressConverter;
    private final CargoRequestToCargoConverter toCargoConverter;
    private final ClientRequestToClientConverter toClientConverter;

    @Override
    public Delivery convert(OrderRequest orderRequest) {
        return Delivery.builder()
                .enabledNotifications(orderRequest.isEnabledNotifications())
                .cargo(toCargoConverter.convert(orderRequest.getCargo()))
                .sender(toClientConverter.convert(orderRequest.getSender()))
                .recipient(toClientConverter.convert(orderRequest.getRecipient()))
                .sendingAddress(toAddressConverter.convert(orderRequest.getSendingAddress()))
                .shippingAddress(toAddressConverter.convert(orderRequest.getShippingAddress()))
                .enabledNotifications(false)
                .trackingNumber("")
                .isPaid(false)
                .status(REGISTERED)
                .build();
    }

}

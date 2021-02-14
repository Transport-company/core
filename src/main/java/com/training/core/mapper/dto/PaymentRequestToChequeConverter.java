package com.training.core.mapper.dto;

import com.training.core.dto.request.PaymentRequest;
import com.training.core.model.Cheque;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class PaymentRequestToChequeConverter implements Converter<PaymentRequest, Cheque> {

    @Override
    public Cheque convert(PaymentRequest paymentRequest) {
        return Cheque.builder()
                .sum(paymentRequest.getSum())
                .delivery(Delivery.builder().id(paymentRequest.getDeliveryId()).build())
                .chequeFile(new byte[0])
                .build();
    }
}

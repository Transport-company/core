package com.training.core.mapper.model;

import com.training.core.dto.response.PaymentPageResponse;
import com.training.core.model.Cheque;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChequePageToPaymentPageResponseConverter implements Converter<Page<Cheque>, PaymentPageResponse> {

    private final ChequeToPaymentResponseConverter toPaymentResponseConverter;

    @Override
    public PaymentPageResponse convert(Page<Cheque> page) {
        return PaymentPageResponse.builder()
                .content(page.getContent().stream()
                        .map(toPaymentResponseConverter::convert)
                        .collect(Collectors.toList()))
                .size(page.getSize())
                .number(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}

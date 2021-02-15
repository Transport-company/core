package com.training.core.controller.impl;

import com.training.core.controller.PaymentController;
import com.training.core.dto.request.PaymentRequest;
import com.training.core.dto.response.PaymentPageResponse;
import com.training.core.dto.response.PaymentResponse;
import com.training.core.model.Cheque;
import com.training.core.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {

    public final PaymentService paymentService;
    public final ConversionService conversionService;

    @Override
    public ResponseEntity<PaymentPageResponse> getList(Pageable pageable) {
        Page<Cheque> page = paymentService.getList(pageable);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(
                        page, PaymentPageResponse.class)));
    }

    @Override
    public ResponseEntity<PaymentResponse> getById(Long id) {
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(
                        paymentService.getById(id), PaymentResponse.class)));
    }

    @Override
    public ResponseEntity<PaymentResponse> create(@Valid PaymentRequest paymentRequest) {
        Cheque cheque = paymentService.create(Objects.requireNonNull(conversionService.convert(
                paymentRequest, Cheque.class)));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversionService.convert(cheque, PaymentResponse.class));
    }
}

package com.training.core.controller.impl;

import com.training.core.controller.OrderController;
import com.training.core.dto.request.OrderRequest;
import com.training.core.dto.response.OrderPageResponse;
import com.training.core.dto.response.OrderResponse;
import com.training.core.model.Delivery;
import com.training.core.service.OrderService;
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
public class OrderControllerImpl implements OrderController {

    public final OrderService orderService;
    public final ConversionService conversionService;

    @Override
    public ResponseEntity<OrderPageResponse> getList(Pageable pageable) {
        Page<Delivery> page = orderService.getList(pageable);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(
                        page, OrderPageResponse.class)));
    }

    @Override
    public ResponseEntity<OrderResponse> getById(Long id) {
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(
                        orderService.getById(id), OrderResponse.class)));
    }

    @Override
    public ResponseEntity<OrderResponse> create(@Valid OrderRequest orderRequest) {
        Delivery delivery = orderService.create(Objects.requireNonNull(conversionService.convert(
                orderRequest, Delivery.class)));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conversionService.convert(delivery, OrderResponse.class));
    }

    @Override
    public ResponseEntity<OrderResponse> update(Long id, @Valid OrderRequest orderRequest) {
        Delivery delivery = orderService.update(id, Objects.requireNonNull(conversionService.convert(
                orderRequest, Delivery.class)));

        return ResponseEntity.ok()
                .body(conversionService.convert(delivery, OrderResponse.class));
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
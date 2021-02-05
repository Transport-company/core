package com.training.core.controller.impl;

import com.training.core.controller.DeliveryController;
import com.training.core.dto.request.DeliveryRequest;
import com.training.core.dto.request.DeliveryStatusRequest;
import com.training.core.dto.response.DeliveryPageResponse;
import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeliveryControllerImpl implements DeliveryController {
    private final DeliveryService deliveryService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<DeliveryPageResponse> getList(Pageable pageable) {
        Page<Delivery> page = deliveryService.getList(pageable);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(page, DeliveryPageResponse.class)));
    }

    @Override
    public ResponseEntity<DeliveryResponse> getById(Long id) {
        Delivery delivery = deliveryService.getById(id);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(delivery, DeliveryResponse.class)));
    }

    @Override
    public ResponseEntity<List<DeliveryResponse>> getFilteredList(DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.getByStatus(status).stream()
                .map(e -> conversionService.convert(e, DeliveryResponse.class))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<DeliveryResponse> create(@Valid DeliveryRequest deliveryRequest) {
        Delivery saved = deliveryService.save(
                conversionService.convert(deliveryRequest, Delivery.class));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversionService.convert(saved, DeliveryResponse.class));
    }

    @Override
    public ResponseEntity<DeliveryResponse> update(Long id, @Valid DeliveryRequest deliveryRequest) {
        Delivery updated = deliveryService.update(id,
                conversionService.convert(deliveryRequest, Delivery.class));
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(updated, DeliveryResponse.class)));
    }

    @Override
    public ResponseEntity<DeliveryResponse> changeStatus(Long id, @Valid DeliveryStatusRequest status) {
        Delivery updated = deliveryService.changeStatus(id, status.getStatus());
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(updated, DeliveryResponse.class)));
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        deliveryService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package com.training.core.controller.impl;

import com.training.core.controller.DeliveryController;
import com.training.core.dto.request.DeliveryRequest;
import com.training.core.dto.response.DeliveryPageResponse;
import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryControllerImpl implements DeliveryController {
    private final DeliveryStatus deliveryStatus;

    @Override
    public ResponseEntity<DeliveryPageResponse> getList(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<DeliveryResponse> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DeliveryResponse>> getFilteredList(DeliveryStatus status) {
        return null;
    }

    @Override
    public ResponseEntity<DeliveryResponse> update(Long id, @Valid DeliveryRequest deliveryRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> changeStatus(Long id, @Valid DeliveryStatus status) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        return null;
    }
}

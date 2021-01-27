package com.training.core.controller.Impl;

import com.training.core.controller.OrderController;
import com.training.core.dto.request.OrderRequest;
import com.training.core.dto.response.OrderPageResponse;
import com.training.core.dto.response.OrderResponse;
import com.training.core.model.Delivery;
import com.training.core.service.OrderService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.ObjectUtils.isEmpty;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl  implements OrderController {

    public final OrderService orderService;
    public final ConversionService conversionService;

    @Override
    public ResponseEntity<OrderPageResponse> getList(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<OrderResponse> getById(Long id) throws NotFoundException {
        if(isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(
                        orderService.getById(id), OrderResponse.class)));
    }

    @Override
    public ResponseEntity<OrderResponse> create(OrderRequest orderRequest) {
        if(isEmpty(orderRequest)){
            return ResponseEntity.badRequest().build();
        }

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
        if(isEmpty(id)){
            return ResponseEntity.badRequest().build();
        }
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
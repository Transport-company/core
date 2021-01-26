package com.training.core.service.Impl;

import com.training.core.exception.NotUpdateException;
import com.training.core.model.Delivery;
import com.training.core.service.DeliveryService;
import com.training.core.service.DeliverySumCalculatingService;
import com.training.core.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DeliveryService deliveryService;
    private final DeliverySumCalculatingService deliverySumCalculatingService;


    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Page<Delivery> getList(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Delivery getById(Long id) {
        log.info("There was a Delivery request with id: {}", id);
        return deliveryService.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Delivery create(Delivery delivery) {
        log.info("A new delivery is creating ...");

        BigDecimal sum = deliverySumCalculatingService.getSum();
        delivery.setSum(sum);
        return deliveryService.save(delivery);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Delivery update(Long id, Delivery delivery) {
        Boolean isPaid = deliveryService.isPaid(id);
        if (isPaid){
            new NotUpdateException(String.format("Delivery with id %s can't be updated, because the delivery is paid", id));
            return null;
        }

        BigDecimal sum = deliverySumCalculatingService.getSum();
        delivery.setSum(sum);

        log.info("Delivery with id {} is updated", id);
        return deliveryService.update(id, delivery);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public void delete(Long id) {
        log.info("The delivery with id: {} is deleting ...", id);
        deliveryService.delete(id);
    }
}

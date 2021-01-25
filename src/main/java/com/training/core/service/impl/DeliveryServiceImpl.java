package com.training.core.service.impl;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.DeliveryRepository;
import com.training.core.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Page<Delivery> getList(@NonNull Pageable pageable) {
        log.info("Requested delivery page: {} page, {} size",
                pageable.getPageNumber(), pageable.getPageSize());
        return deliveryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Delivery getById(@NonNull Long id) {
        Assert.notNull(id, "Id can not be null");

        log.info("Requested the delivery with id: {}", id);
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the delivery with id " + id));
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public List<Delivery> getByStatus(@NonNull DeliveryStatus status) {
        Assert.notNull(status, "Status can not be null");

        log.info("Requested a delivery list filtered with status: {}", status);
        return deliveryRepository.findByStatus(status);
    }

    @Transactional
    @Override
    @NonNull
    public Delivery save(@NonNull Delivery delivery) {
        Assert.notNull(delivery, "Delivery can not be null");

        Delivery saved = deliveryRepository.save(delivery);
        log.info("Saved a new delivery with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public Delivery update(@NonNull Long id, @NonNull Delivery delivery) {
        Assert.notNull(id, "Id can not be null");
        Assert.notNull(delivery, "Delivery can not be null");

        Delivery fetched = getById(id);
        delivery.setId(fetched.getId());
        delivery.setCreated(fetched.getCreated());
        Delivery updated = deliveryRepository.save(delivery);
        log.info("Updated the delivery with id: {}", updated.getId());
        return updated;
    }

    @Transactional
    @Override
    @NonNull
    public void delete(@NonNull Long id) {
        Assert.notNull(id, "Id can not be null");

        getById(id);
        deliveryRepository.deleteById(id);
        log.info("Deleted the delivery with id: {}", id);
    }
}

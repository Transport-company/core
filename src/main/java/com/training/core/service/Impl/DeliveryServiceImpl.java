package com.training.core.service.Impl;

import com.training.core.exception.ErrorMessages;
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
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the delivery with id: {}", id);
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the delivery with id " + id));
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public List<Delivery> getByStatus(@NonNull DeliveryStatus status) {
        Assert.notNull(status, ErrorMessages.NULL_STATUS.getErrorMessage());

        log.info("Requested a delivery list filtered with status: {}", status);
        return deliveryRepository.findByStatus(status);
    }

    @Transactional
    @Override
    @NonNull
    public Delivery save(@NonNull Delivery delivery) {
        Assert.notNull(delivery, ErrorMessages.NULL_DELIVERY_OBJECT.getErrorMessage());

        Delivery saved = deliveryRepository.save(delivery);
        log.info("Saved a new delivery with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public Boolean isPaid(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        Delivery delivery = deliveryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found the delivery with id " + id));

        log.info("Get information about the payment for delivery with id: {}", id);
        return delivery.getIsPaid();
    }


    @Transactional
    @Override
    @NonNull
    public Delivery update(@NonNull Long id, @NonNull Delivery delivery) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(delivery, ErrorMessages.NULL_DELIVERY_OBJECT.getErrorMessage());

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
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        getById(id);
        deliveryRepository.deleteById(id);
        log.info("Deleted the delivery with id: {}", id);
    }
}
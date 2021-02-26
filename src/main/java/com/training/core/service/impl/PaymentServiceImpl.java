package com.training.core.service.impl;

import com.training.core.exception.ChequeNotFoundException;
import com.training.core.exception.ErrorMessages;
import com.training.core.model.Cheque;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import com.training.core.repository.ChequeRepository;
import com.training.core.service.DeliveryService;
import com.training.core.service.PaymentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ChequeRepository chequeRepository;
    private final DeliveryService deliveryService;

    @Transactional
    @Override
    @NonNull
    public Cheque create(@NonNull Cheque cheque) {
        Assert.notNull(cheque, ErrorMessages.NULL_CHEQUE_OBJECT.getErrorMessage());

        Delivery delivery = deliveryService.getById(cheque.getDelivery().getId());

        cheque.setDelivery(delivery);
        cheque.setChequeFile(new byte[0]);
        Cheque saved = chequeRepository.save(cheque);

        delivery.setIsPaid(true);
        delivery.setStatus(DeliveryStatus.PAID);
        deliveryService.update(delivery.getId(), delivery);

        log.info("Saved a new cheque with id: {}", saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Page<Cheque> getList(@NonNull Pageable pageable) {
        log.info("Requested cheque page: {} page, {} size",
                pageable.getPageNumber(), pageable.getPageSize());
        return chequeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Cheque getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the cheque with id: {}", id);
        return chequeRepository.findById(id)
                .orElseThrow(() -> new ChequeNotFoundException(
                        "Not found the cheque with id " + id));
    }
}

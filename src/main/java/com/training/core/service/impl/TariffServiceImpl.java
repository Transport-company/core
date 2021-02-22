package com.training.core.service.impl;

import com.training.core.exception.ErrorMessages;
import com.training.core.exception.NotFoundException;
import com.training.core.model.Tariff;
import com.training.core.repository.TariffRepository;
import com.training.core.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Page<Tariff> getList(@NonNull Pageable pageable) {
        log.info("Requested delivery page: {} page, {} size",
                pageable.getPageNumber(), pageable.getPageSize());
        return tariffRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Tariff getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the tariff with id: {}", id);
        return tariffRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the tariff with id " + id));
    }

    @Transactional(readOnly = true)
    @Override
    @NonNull
    public Tariff getForDate(@NonNull LocalDate date) {
        Assert.notNull(date, ErrorMessages.NULL_DATE.getErrorMessage());

        log.info("Requested the tariff for date: {}", date);
        return tariffRepository.findForDate(date)
                .orElseThrow(() -> new NotFoundException("Not found the tariff for date " + date));
    }

    @Transactional
    @Override
    @NonNull
    public Tariff save(@NonNull Tariff tariff) {
        Assert.notNull(tariff, ErrorMessages.NULL_TARIFF_OBJECT.getErrorMessage());

        Tariff saved = tariffRepository.save(tariff);
        log.info("Saved a new tariff with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public Tariff update(@NonNull Long id, @NonNull Tariff tariff) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(tariff, ErrorMessages.NULL_TARIFF_OBJECT.getErrorMessage());

        Tariff fetched = getById(id);
        tariff.setId(fetched.getId());
        tariff.setCreated(fetched.getCreated());

        Tariff updated = tariffRepository.save(tariff);
        log.info("Updated the tariff with id: {}", updated.getId());
        return updated;
    }

    @Transactional
    @Override
    @NonNull
    public void delete(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        getById(id);
        tariffRepository.deleteById(id);
        log.info("Deleted the tariff with id: {}", id);
    }
}

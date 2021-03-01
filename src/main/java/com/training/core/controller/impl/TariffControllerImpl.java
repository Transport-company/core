package com.training.core.controller.impl;

import com.training.core.controller.TariffController;
import com.training.core.dto.request.TariffRequest;
import com.training.core.dto.response.TariffPageResponse;
import com.training.core.dto.response.TariffResponse;
import com.training.core.model.Tariff;
import com.training.core.service.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class TariffControllerImpl implements TariffController {
    private final TariffService tariffService;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<TariffPageResponse> getList(Pageable pageable) {
        Page<Tariff> page = tariffService.getList(pageable);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(page, TariffPageResponse.class)));
    }

    @Override
    public ResponseEntity<TariffResponse> getById(Long id) {
        Tariff tariff = tariffService.getById(id);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(tariff, TariffResponse.class)));
    }

    @Override
    public ResponseEntity<TariffResponse> getForDate(LocalDate date) {
        Tariff tariff = tariffService.getForDate(date);
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(tariff, TariffResponse.class)));
    }

    @Override
    public ResponseEntity<TariffResponse> create(@Valid TariffRequest tariffRequest) {
        Tariff saved = tariffService.save(conversionService.convert(tariffRequest, Tariff.class));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conversionService.convert(saved, TariffResponse.class));
    }

    @Override
    public ResponseEntity<TariffResponse> update(Long id, @Valid TariffRequest tariffRequest) {
        Tariff updated = tariffService.update(id,
                conversionService.convert(tariffRequest, Tariff.class));
        return ResponseEntity.ok(
                Objects.requireNonNull(conversionService.convert(updated, TariffResponse.class)));
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        tariffService.delete(id);
        return ResponseEntity.ok().build();
    }
}

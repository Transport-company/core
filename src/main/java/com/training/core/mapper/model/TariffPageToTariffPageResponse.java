package com.training.core.mapper.model;

import com.training.core.dto.response.TariffPageResponse;
import com.training.core.model.Tariff;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TariffPageToTariffPageResponse
        implements Converter<Page<Tariff>, TariffPageResponse> {
    private final TariffToTariffResponseConverter toTariffResponseConverter;

    @Override
    public TariffPageResponse convert(Page<Tariff> page) {
        return TariffPageResponse.builder()
                .content(page.getContent().stream()
                        .map(toTariffResponseConverter::convert)
                        .collect(Collectors.toList()))
                .size(page.getSize())
                .number(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}

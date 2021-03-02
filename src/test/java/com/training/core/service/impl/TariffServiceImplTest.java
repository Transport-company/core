package com.training.core.service.impl;

import com.training.core.exception.TariffNotFoundException;
import com.training.core.model.Tariff;
import com.training.core.repository.TariffRepository;
import com.training.core.util.TestTariffs;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TariffServiceImplTest extends BaseTest {

    @InjectMocks
    private TariffServiceImpl tariffService;

    @Mock
    private TariffRepository tariffRepository;

    @Test
    void givenPageSizeAndPageNumber_whenGetList_thenReturnPageWithList() {
        int pageSize = 10;
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Tariff> testData = TestTariffs.list();

        when(tariffRepository.findAll(pageRequest))
                .thenReturn(
                        new PageImpl<>(
                                pageSize < testData.size()
                                        ? testData.stream()
                                        .limit(pageSize)
                                        .collect(Collectors.toList())
                                        : testData,
                                pageRequest,
                                testData.size()));

        Page<Tariff> page = tariffService.getList(pageRequest);

        assertNotNull(page);
        assertEquals(Math.min(pageSize, testData.size()), page.getContent().size());
        assertEquals(testData.size(), page.getTotalElements());
    }

    @Test
    void givenId_whenGetById_thenReturnTariff() {
        Long id = 1L;
        Tariff tariff = TestTariffs.first();

        when(tariffRepository.findById(id))
                .thenReturn(Optional.of(tariff));

        Tariff fetched = tariffService.getById(id);

        assertNotNull(fetched);
        assertEquals(tariff, fetched);
    }

    @Test
    void givenNotExistingId_whenGetById_thenThrowTariffNotFoundException() {
        Long id = 999L;

        when(tariffRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(TariffNotFoundException.class, () -> tariffService.getById(id));
    }

    @Test
    void givenDate_whenGetForDate_thenReturnTariff() {
        LocalDate date = LocalDate.now();
        Tariff tariff = TestTariffs.first();

        when(tariffRepository.findForDate(date))
                .thenReturn(Optional.of(tariff));

        Tariff fetched = tariffService.getForDate(date);

        assertNotNull(fetched);
        assertEquals(tariff, fetched);
    }

    @Test
    void givenTariff_whenSave_thenReturnSavedTariff() {
        Tariff tariff = TestTariffs.first();

        when(tariffRepository.save(tariff))
                .thenReturn(tariff);

        assertNotNull(tariffService.save(tariff));
    }

    @Test
    void givenIdAndTariff_whenUpdate_thenReturnUpdatedTariff() {
        Long id = 1L;
        Tariff tariff = TestTariffs.first();

        when(tariffRepository.findById(id))
                .thenReturn(Optional.of(tariff));
        when(tariffRepository.save(tariff))
                .thenReturn(tariff);

        assertNotNull(tariffService.update(id, tariff));
    }

    @Test
    void givenNotExistingIdAndTariff_whenUpdate_thenThrowTariffNotFoundExceptionf() {
        Long id = 1L;
        Tariff tariff = TestTariffs.first();

        when(tariffRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(TariffNotFoundException.class, () -> tariffService.update(id, tariff));
    }

    @Test
    void givenId_whenDelete_thenNoReturn() {
        Long id = 1L;
        Tariff tariff = TestTariffs.first();

        when(tariffRepository.findById(id))
                .thenReturn(Optional.of(tariff));

        tariffService.delete(id);

        verify(tariffRepository, times(1)).deleteById(id);
    }

    @Test
    void givenNotExistingId_whenDelete_thenThrowTariffNotFoundException() {
        Long id = 1L;

        when(tariffRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(TariffNotFoundException.class, () -> tariffService.delete(id));
    }
}
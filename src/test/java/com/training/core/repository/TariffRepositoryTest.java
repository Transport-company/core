package com.training.core.repository;

import com.training.core.model.Tariff;
import com.training.core.util.TestTariffs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TariffRepositoryTest {

    @Autowired
    private TariffRepository tariffRepository;

    @Test
    void givenDate_whenFindForDate_thenRetunTariff() {
        Tariff first = TestTariffs.first();
        Tariff from2020 = TestTariffs.from2020();
        tariffRepository.save(first);
        tariffRepository.save(from2020);
        tariffRepository.flush();

        Optional<Tariff> optionalTariff = tariffRepository.findForDate(LocalDate.of(2019, 1, 1));

        assertTrue(optionalTariff.isPresent());
        Tariff tariff = optionalTariff.get();
        assertAll("The fetched tariff does not match the expected one",
                () -> assertEquals(first.getOrderSum(), tariff.getOrderSum()),
                () -> assertEquals(first.getCourierSum(), tariff.getCourierSum()),
                () -> assertEquals(first.getDistancePrice(), tariff.getDistancePrice()),
                () -> assertEquals(first.getMinDistance(), tariff.getMinDistance()),
                () -> assertEquals(first.getDistanceThreshold(), tariff.getDistanceThreshold()),
                () -> assertEquals(first.getReductionFactor(), tariff.getReductionFactor()),
                () -> assertEquals(first.getWeightUnit(), tariff.getWeightUnit()),
                () -> assertEquals(first.getWeightThreshold(), tariff.getWeightThreshold()),
                () -> assertEquals(first.getWeightRatioIncrease(),
                        tariff.getWeightRatioIncrease()),
                () -> assertEquals(first.getVolumeUnit(), tariff.getVolumeUnit()),
                () -> assertEquals(first.getVolumeThreshold(), tariff.getVolumeThreshold()),
                () -> assertEquals(first.getVolumeRatioIncrease(),
                        tariff.getVolumeRatioIncrease())
        );
    }
}
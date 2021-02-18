package com.training.core.service.impl;

import com.training.core.service.DeliveryService;
import com.training.core.util.RandomGenerator;
import com.training.core.util.TestDelivery;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrackingNumberGeneratingServiceImplTest extends BaseTest {

    @InjectMocks
    TrackingNumberGeneratingServiceImpl trackingNumberGeneratingService;

    @Mock
    DeliveryService deliveryService;

    @Mock
    RandomGenerator randomGenerator;

    private RandomGenerator realRandomGenerator = new RandomGenerator();

    @Test
    void givenAnyParameter_whenGenarate_thenReturnValueNotNull() {
        final int letterNumber = 4;
        final int digitNumber = 8;

        when(randomGenerator.generateCapitalLetterLine(letterNumber))
                .thenReturn(realRandomGenerator.generateCapitalLetterLine(letterNumber));
        when(randomGenerator.generateDigitLine(digitNumber))
                .thenReturn(realRandomGenerator.generateDigitLine(digitNumber));
        when(deliveryService.existsTrackingNumber(any()))
                .thenReturn(false);

        String generated = trackingNumberGeneratingService.genarate(TestDelivery.first());

        assertNotNull(generated);
        verify(deliveryService, times(1)).existsTrackingNumber(any());
        verify(randomGenerator, times(1)).generateCapitalLetterLine(letterNumber);
        verify(randomGenerator, times(1)).generateDigitLine(digitNumber);
    }

    @Test
    void givenAnyParameter_whenTwiceGenarate_thenReturnValueNotNull() {
        final int letterNumber = 4;
        final int digitNumber = 8;

        when(randomGenerator.generateCapitalLetterLine(letterNumber))
                .thenReturn(realRandomGenerator.generateCapitalLetterLine(letterNumber));
        when(randomGenerator.generateDigitLine(digitNumber))
                .thenReturn(realRandomGenerator.generateDigitLine(digitNumber));
        when(deliveryService.existsTrackingNumber(any()))
                .thenReturn(true)
                .thenReturn(false);

        String generated = trackingNumberGeneratingService.genarate(TestDelivery.first());

        assertNotNull(generated);
        verify(deliveryService, times(2)).existsTrackingNumber(any());
        verify(randomGenerator, times(2)).generateCapitalLetterLine(letterNumber);
        verify(randomGenerator, times(2)).generateDigitLine(digitNumber);
    }
}
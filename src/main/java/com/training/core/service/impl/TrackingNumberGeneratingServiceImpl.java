package com.training.core.service.impl;

import com.training.core.model.Delivery;
import com.training.core.service.DeliveryService;
import com.training.core.service.TrackingNumberGeneratingService;
import com.training.core.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrackingNumberGeneratingServiceImpl implements TrackingNumberGeneratingService {
    private final RandomGenerator randomGenerator;
    private final DeliveryService deliveryService;

    @Override
    public String genarate(Delivery delivery) {
        String trackingNumber = null;
        while (trackingNumber == null || deliveryService.existsTrackingNumber(trackingNumber)) {
            trackingNumber =
                    randomGenerator.generateCapitalLetterLine(4) +
                    randomGenerator.generateDigitLine(8);
        }

        log.info("Generated the tracking number {} for the delivery with id: {}",
                trackingNumber, delivery.getId());
        return trackingNumber;
    }

}

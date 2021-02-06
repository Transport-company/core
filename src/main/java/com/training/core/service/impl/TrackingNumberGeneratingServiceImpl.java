package com.training.core.service.impl;

import com.training.core.service.DeliveryService;
import com.training.core.service.TrackingNumberGeneratingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrackingNumberGeneratingServiceImpl implements TrackingNumberGeneratingService {

    private final DeliveryService deliveryService;

    @Override
    public String generateStringTrackingNumber() {

        String trackingNumber;
        do {
            trackingNumber = UUID.randomUUID().toString();
            log.info("Generates a tracking number");
            if (deliveryService.existsTrackNumber(trackingNumber)) {
                trackingNumber = UUID.randomUUID().toString();
            }
        } while (trackingNumber == null);
        return trackingNumber;
    }
}

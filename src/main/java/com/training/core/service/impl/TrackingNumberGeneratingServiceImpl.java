package com.training.core.service.impl;

import com.training.core.service.TrackingNumberGeneratingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class TrackingNumberGeneratingServiceImpl implements TrackingNumberGeneratingService {

    @Override
    public String generateStringTrackingNumber() {
        String trackingNumber = UUID.randomUUID().toString();
        log.info("Generates a tracking number");
        Random randomTrackingNumber = new Random();
        boolean existsInTheDatabase = randomTrackingNumber.equals(trackingNumber);
        if (existsInTheDatabase == true) {
            String newTrackingNumber = UUID.randomUUID().toString();
            return newTrackingNumber;
        } else {
            log.info("Track number recorded in base");
            return trackingNumber;
        }
    }
}

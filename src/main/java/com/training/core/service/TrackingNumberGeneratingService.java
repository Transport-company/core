package com.training.core.service;


import java.util.Random;
import java.util.UUID;

/**
 * Generates a tracking number of a delivery.
 */

public interface TrackingNumberGeneratingService {
    public static String generateStringTrackingNumber() {

        String trackingNumber = UUID.randomUUID().toString(); // Generates a tracking number
        Random randomTrackingNumber = new Random(); // Plug, track number existing in the database
        boolean b = randomTrackingNumber.equals(trackingNumber);
        if (b == true) {
            String newTrackingNumber = UUID.randomUUID().toString();
            return newTrackingNumber;
        } else {
            return trackingNumber;
        }
    }
}



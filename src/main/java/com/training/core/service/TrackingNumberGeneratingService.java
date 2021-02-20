package com.training.core.service;

import com.training.core.model.Delivery;

/**
 * Generates a tracking number of a delivery.
 */
public interface TrackingNumberGeneratingService {

    /**
     * Generates a tracking number of a delivery.
     *
     * @param delivery {@link Delivery delivery} for which a tracking number is generated
     * @return a generated tracking number
     */
    String genarate(Delivery delivery);

}

package com.training.core.service;

import com.training.core.model.Delivery;

/**
 * Calculates the distance to which the cargo must be delivered.
 */
public interface DeliveryDistanceCalculatingService {

    /**
     * Method for calculating the distance to which the cargo must be delivered.
     *
     * @param delivery {@link Delivery delivery} for which the calculation is made
     * @return the calculated distance
     */
    int getDistance(Delivery delivery);

}

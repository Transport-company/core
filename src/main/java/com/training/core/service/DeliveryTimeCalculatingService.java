package com.training.core.service;

/**
 * Calculates delivery time.
 */
public interface DeliveryTimeCalculatingService {

    /**
     * Method for calculating duration of the delivery
     *
     * @return the time of delivery
     */
    Long getTime();
}

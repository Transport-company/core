package com.training.core.service;

import java.math.BigDecimal;

/**
 * Calculates a delivery sum.
 */
public interface DeliverySumCalculatingService {

    /**
     *
     * @return delivery cost for the client
     */
    BigDecimal getSum();
}

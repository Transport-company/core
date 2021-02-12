package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * An object for trancferring data from a controller to a response about a payment.
 */
@Data
@Builder
public class PaymentResponse {

    /**
     * Delivery cost
     */
    @Schema(description = "cost")
    private final BigDecimal sum;

    /**
     * Delivery information
     */
    @Schema(description = "delivery information")
    private final DeliveryResponse deliveryResponse;
}

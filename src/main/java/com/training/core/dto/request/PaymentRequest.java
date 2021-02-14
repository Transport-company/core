package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * An object for transferring data from a request to a controller about a payment.
 */
@Data
@Builder
public class PaymentRequest {

    /**
     * Delivery cost
     */
    @Schema(description = "cost", required = true,
            example = "450.50")
    @NotNull(message = "The field is required")
    private final BigDecimal sum;

    /**
     * Delivery id
     */
    @Schema(description = "delivery id", required = true)
    @NotNull(message = "The field is required")
    private final Long deliveryId;
}

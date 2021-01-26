package com.training.core.dto.response;

import com.training.core.model.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;


/**
 * An object for trancferring data from a controller to a response about an order for delivery.
 */
@Data
@Builder
public class OrderResponse {

    /**
     * Unique identifier of the delivery
     */
    @Schema(description = "Unique identifier of the delivery",
            example = "1")
    private final Long id;

    /**
     * Cost of delivery
     */
    @Schema(description = "Cost of delivery",
            example = "1.5")
    private final BigDecimal sum;

    /**
     *  Cargo information
     */
    private final Cargo cargo;

    /**
     * Information about the sender
     */
    private Client sender;

    /**
     * Information about the recipient
     */
     private Client recipient;

    /**
     * Sending address
     */
    private final Address sendingAddress;

    /**
     * Shipping address
     */
    private final Address shippingAddress;
}

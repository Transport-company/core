package com.training.core.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


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
     * Cargo information
     */
    @Schema(description = "a cargo to send")
    private final CargoResponse cargo;

    /**
     * Cost of delivery
     */
    @Schema(description = "Cost of delivery",
            example = "1.5")
    private final BigDecimal sum;

    /**
     * Information about the sender
     */
    @Schema(description = "the sender of the cargo")
    private final ClientResponse sender;

    /**
     * Information about the recipient
     */
    @Schema(description = "the recipient of the cargo")
    private final ClientResponse recipient;

    /**
     * Sending address
     */
    @Schema(description = "an adress from which the cargo is sent")
    private final AddressResponse sendingAddress;

    /**
     * Shipping address
     */
    @Schema(description = "an adress of receipt of the cargo")
    private final AddressResponse shippingAddress;

    /**
     * Time of object creation
     */
    @Schema(description = "a date of delivery creation (filled in automatically)")
    private final LocalDateTime created;

    /**
     * Update time
     */
    @Schema(description = "a date of delivery update (filled in automatically)")
    private final LocalDateTime updated;
}

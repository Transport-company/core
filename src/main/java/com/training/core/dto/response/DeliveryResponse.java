package com.training.core.dto.response;

import com.training.core.model.Address;
import com.training.core.model.Cargo;
import com.training.core.model.Client;
import com.training.core.model.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * An object for trancferring data from a controller to a response about a delivery.
 */
@Data
@Builder
public class DeliveryResponse {

    /**
     * Unique identifier of the delivery
     */
    @Schema(description = "an unique identifier",
            example = "1")
    private final Long id;

    /**
     * Information about the client's desire to receive notifications
     */
    @Schema(description = "to send or not send notifications to the client",
            example = "true")
    private final Boolean enabledNotifications;

    /**
     * Cost of delivery
     */
    @Schema(description = "cost of the delivery",
            example = "89.90")
    private final BigDecimal sum;

    /**
     * Unique delivery number
     */
    @Schema(description = "a generated unique string",
            example = "mdu4n286ndgTwGGspr8hw5D7sErW5Mgcz")
    private final String trackingNumber;

    /**
     * Information about payment for cargo transit
     */
    @Schema(description = "is the delivery paid for or not",
            example = "true")
    private final Boolean isPaid;

    /**
     * Information about the delivery stage (cargo status)
     */
    @Schema(description = "a delivery status",
            example = "REGISTERED")
    @Enumerated(EnumType.STRING)
    private final DeliveryStatus status;

    /**
     * Cargo information
     */
    @Schema(description = "a cargo to send")
    private final Cargo cargo;

    /**
     * Information about the sender
     */
    @Schema(description = "the sender of the cargo")
    private final Client sender;

    /**
     * Information about the recipient
     */
    @Schema(description = "the recipient of the cargo")
    private final Client recipient;

    /**
     * Sending address
     */
    @Schema(description = "an adress from which the cargo is sent")
    private final Address sendingAddress;

    /**
     * Shipping address
     */
    @Schema(description = "an adress of receipt of the cargo")
    private final Address shippingAddress;

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

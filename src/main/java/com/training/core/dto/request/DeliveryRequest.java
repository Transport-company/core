package com.training.core.dto.request;

import com.training.core.model.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * An object for transferring data from a request to a controller about a delivery.
 */
@Data
@Builder
public class DeliveryRequest {

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
    private final CargoRequest cargo;

    /**
     * Information about the sender
     */
    @Schema(description = "the sender of the cargo")
    private final ClientRequest sender;

    /**
     * Information about the recipient
     */
    @Schema(description = "the recipient of the cargo")
    private final ClientRequest recipient;

    /**
     * Sending address
     */
    @Schema(description = "an adress from which the cargo is sent")
    private final AddressRequest sendingAddress;

    /**
     * Shipping address
     */
    @Schema(description = "an adress of receipt of the cargo")
    private final AddressRequest shippingAddress;

}

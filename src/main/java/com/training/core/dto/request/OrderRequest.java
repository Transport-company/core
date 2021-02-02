package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * An object for transferring data from a request to a controller about an order for delivery.
 */
@Data
@Builder
public class OrderRequest {

    /**
     * Information about the client's desire to receive notifications
     */
    @Schema(description = "to send or not send notifications to the client", required = true,
            example = "true")
    @NotNull(message = "The field is required")
    private final boolean enabledNotifications;

    /**
     * Cargo information
     */
    @Schema(description = "Cargo information", required = true)
    @NotNull(message = "The field is required")
    private final CargoRequest cargo;

    /**
     * Information about the sender
     */
    @Schema(description = "Information about the sender", required = true)
    @NotNull(message = "The field is required")
    private final ClientRequest sender;

    /**
     * Information about the recipient
     */
    @Schema(description = "Information about the recipient", required = true)
    @NotNull(message = "The field is required")
    private final ClientRequest recipient;

    /**
     * Sending address
     */
    @Schema(description = "Sending address", required = true)
    @NotNull(message = "The field is required")
    private final AddressRequest sendingAddress;

    /**
     * Shipping address
     */
    @Schema(description = "Shipping address", required = true)
    @NotNull(message = "The field is required")
    private final AddressRequest shippingAddress;

}

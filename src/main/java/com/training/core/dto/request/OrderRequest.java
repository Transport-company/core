package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An object for transferring data from a request to a controller about an order for delivery.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    /**
     *  Cargo information
     */
    @Schema(description = "Cargo information")
    private CargoRequest cargo;

    /**
     * Information about the sender
     */
    @Schema(description = "Information about the sender")
    private ClientRequest sender;

    /**
     * Information about the recipient
     */
    @Schema(description = "Information about the recipient")
    private ClientRequest recipient;

    /**
     * Sending address
     */
    @Schema(description = "Sending address")
    private AddressRequest sendingAddress;

    /**
     * Shipping address
     */
    @Schema(description = "Shipping address")
     private AddressRequest shippingAddress;
}

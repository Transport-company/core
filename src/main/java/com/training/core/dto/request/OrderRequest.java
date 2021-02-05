package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    @Schema(description = "Cargo information", required = true)
    @NotNull(message = "The field is required")
    private CargoRequest cargo;

    /**
     * Information about the sender
     */
    @Schema(description = "Information about the sender", required = true)
    @NotNull(message = "The field is required")
    private ClientRequest sender;

    /**
     * Information about the recipient
     */
    @Schema(description = "Information about the recipient", required = true)
    @NotNull(message = "The field is required")
    private ClientRequest recipient;

    /**
     * Sending address
     */
    @Schema(description = "Sending address", required = true)
    @NotNull(message = "The field is required")
    private AddressRequest sendingAddress;

    /**
     * Shipping address
     */
    @Schema(description = "Shipping address", required = true)
    @NotNull(message = "The field is required")
     private AddressRequest shippingAddress;
}

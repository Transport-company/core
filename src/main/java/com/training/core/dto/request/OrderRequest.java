package com.training.core.dto.request;

import com.training.core.model.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * An object for transferring data from a request to a controller about an order for delivery.
 */
@Data
@Builder
public class OrderRequest {

//    /**
//     *  Cargo information
//     */
//    @Schema(description = "Cargo information")
//    private Cargo cargo;

//    /**
//     * Information about the sender
//     */
//    @Schema(description = "Information about the sender")
//    private Client sender;
//
//    /**
//     * Information about the recipient
//     */
//    @Schema(description = "Information about the recipient")
//    private Client recipient;
//
//    /**
//     * Sending address
//     */
//    @Schema(description = "Sending address")
//    private Address sendingAddress;
//
//    /**
//     * Shipping address
//     */
//    @Schema(description = "Shipping address")
//     private Address shippingAddress;
}

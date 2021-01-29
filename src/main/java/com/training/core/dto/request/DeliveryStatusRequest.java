package com.training.core.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.training.core.model.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * An object for transferring data from a request to a controller about a new delivery status.
 */
@Data
public class DeliveryStatusRequest {

    /**
     * A new status.
     */
    @Schema(description = "a new status", required = true,
            example = "DELIVERED")
    @NotNull(message = "The field is required")
    private final DeliveryStatus status;

    @JsonCreator
    public DeliveryStatusRequest(DeliveryStatus status) {
        this.status = status;
    }
}

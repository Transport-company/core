package com.training.core.dto.request;

import com.training.core.model.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * An object for transferring data from a request to a controller about a new delivery status.
 */
@Data
@Builder
public class DeliveryStatusRequest {

    /**
     * A new status.
     */
    @Schema(description = "a new status",
            example = "DELIVERED")
    @NotBlank(message = "The field is required")
    private final DeliveryStatus status;
}

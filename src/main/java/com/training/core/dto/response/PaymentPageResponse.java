package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a page of payments.
 */
@Data
@Builder
public class PaymentPageResponse {

    /**
     * List of payments.
     */
    @Schema(description = "list of payments.")
    private final List<PaymentResponse> content;

    /**
     * Page size.
     */
    @Schema(description = "page size")
    private final int size;

    /**
     * Page number.
     */
    @Schema(description = "page number")
    private final int number;

    /**
     * Total number of pages.
     */
    @Schema(description = "total number of pages")
    private final int totalPages;

    /**
     * Total number of elements.
     */
    @Schema(description = "total number of elements")
    private final long totalElements;
}

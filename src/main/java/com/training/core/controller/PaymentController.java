package com.training.core.controller;

import com.training.core.Urls;
import com.training.core.dto.request.PaymentRequest;
import com.training.core.dto.response.PaymentPageResponse;
import com.training.core.dto.response.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Paying for delivery")
@RequestMapping(Urls.Payments.FULL)
public interface PaymentController {
    String ID_PATH_VARIABLE = "/{id}";

    @Operation(summary = "get a page of payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PaymentPageResponse.class)))
    })
    @GetMapping
    ResponseEntity<PaymentPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @Operation(summary = "get a payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<PaymentResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id  of the payment to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id);

    @Operation(summary = "add a new payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "a payment created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class)))
    })
    @PostMapping
    ResponseEntity<PaymentResponse> create(
            @Parameter(
                    description = "the payment to add. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = PaymentRequest.class))
            @Valid @RequestBody PaymentRequest paymentRequest);

    @Operation(summary = "update an existing payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<PaymentResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the payment to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the payment to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = PaymentRequest.class))
            @Valid @RequestBody PaymentRequest paymentRequest);

    @Operation(summary = "deletes a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @DeleteMapping(ID_PATH_VARIABLE)
    ResponseEntity<String> delete(
            @Parameter(
                    name = "id",
                    description = "id of the payment to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}

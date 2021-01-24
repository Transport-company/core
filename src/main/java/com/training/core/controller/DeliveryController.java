package com.training.core.controller;

import com.training.core.Urls;
import com.training.core.dto.request.DeliveryRequest;
import com.training.core.dto.request.DeliveryStatusRequest;
import com.training.core.dto.response.DeliveryPageResponse;
import com.training.core.dto.response.DeliveryResponse;
import com.training.core.model.DeliveryStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Delivery")
@RequestMapping(Urls.Deliveries.FULL)
public interface DeliveryController {
    String ID_PATH_VARIABLE = "/{id}";

    @Operation(summary = "get a page of deliveries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DeliveryPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<DeliveryPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @Operation(summary = "get a delivery by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DeliveryResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<DeliveryResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id  of the delivery to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id);

    @Operation(summary = "get a delivery list filtered by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DeliveryResponse.class)))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(Urls.Deliveries.Filter.PART)
    ResponseEntity<List<DeliveryResponse>> getFilteredList(
            @Parameter(
                    description = "the delivery status to filter. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = DeliveryStatus.class))
            @PathVariable DeliveryStatus status);

    @Operation(summary = "update an existing delivery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DeliveryResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<DeliveryResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the delivery to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the delivery to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = DeliveryRequest.class))
            @Valid @RequestBody DeliveryRequest deliveryRequest);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PatchMapping(ID_PATH_VARIABLE + "/" + Urls.Deliveries.Status.PART)
    ResponseEntity<String> changeStatus(
            @Parameter(
                    name = "id",
                    description = "id of the delivery to be changed status. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "status data. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = DeliveryStatusRequest.class))
            @Valid @RequestBody DeliveryStatus status);

    @Operation(summary = "deletes a delivery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @DeleteMapping(ID_PATH_VARIABLE)
    ResponseEntity<String> delete(
            @Parameter(
                    name = "id",
                    description = "id of the delivery to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}

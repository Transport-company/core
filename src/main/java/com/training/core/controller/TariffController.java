package com.training.core.controller;

import com.training.core.Urls;
import com.training.core.dto.request.TariffRequest;
import com.training.core.dto.response.TariffPageResponse;
import com.training.core.dto.response.TariffResponse;
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
import java.time.LocalDate;

@Tag(name = "Tariff")
@RequestMapping(Urls.Tariffs.FULL)
public interface TariffController {
    String ID_PATH_VARIABLE = "/{id}";
    String DATE_PATH_VARIABLE = "/" + Urls.Tariffs.Dates.PART + "/{date}";

    @Operation(summary = "get a page of tariffs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TariffPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)
    })
    @GetMapping
    ResponseEntity<TariffPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @Operation(summary = "get a tariff by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TariffResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<TariffResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id of the tariff to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id);

    @Operation(summary = "get a tariff for some date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TariffResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)
    })
    @GetMapping(DATE_PATH_VARIABLE)
    ResponseEntity<TariffResponse> getForDate(
            @Parameter(
                    name = "date",
                    description = "date the tariff to be obtained. Cannot be null",
                    required = true)
            @PathVariable LocalDate date);

    @Operation(summary = "create a new tariff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "a tariff created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TariffResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)
    })
    @PostMapping
    ResponseEntity<TariffResponse> create(
            @Parameter(
                    description = "the tariff to add. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = TariffRequest.class))
            @Valid @RequestBody TariffRequest tariffRequest);

    @Operation(summary = "update an existing tariff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TariffResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)
    })
    @PutMapping
    ResponseEntity<TariffResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the tariff to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the tariff to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = TariffRequest.class))
            @Valid @RequestBody TariffRequest tariffRequest);

    @Operation(summary = "deletes a tariff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)
    })
    @DeleteMapping
    ResponseEntity<String> delete(
            @Parameter(
                    name = "id",
                    description = "id of the tariff to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}

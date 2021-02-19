package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * An object for trancferring data from a controller to a response about a client.
 */
@Data
@Builder
public class ClientResponse {

    /**
     * Unique identifier of client
     */
    @Schema(description = "an unique identifier")
    private Long id;

    /**
     * The last name of the client
     */
    @Schema(description = "The last name of the client")
    private String lastName;

    /**
     * The first name of the client
     */
    @Schema(description = "The first name of the client")
    private String firstName;

    /**
     * The middle name of the client
     */
    @Schema(description = "The middle name of the client")
    private String middleName;

    /**
     * Date of birth
     */
    @Schema(description = "Date of birth")
    private LocalDate birthday;

    /**
     * The phone number of the client
     */
    @Schema(description = "The phone number of the client")
    private String phoneNumber;

    /**
     * The email of the client
     */
    @Schema(description = "The email of the clientt")
    private String email;

    /**
     * Time of object creation
     */
    @Schema(description = "time of creation")
    private LocalDateTime created;

    /**
     * Update time
     */
    @Schema(description = "update time")
    private LocalDateTime updated;
}

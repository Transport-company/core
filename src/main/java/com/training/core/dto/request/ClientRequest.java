package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * An object for transferring data from a request to a controller about a client.
 */
@Data
@Builder
public class ClientRequest {

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

}

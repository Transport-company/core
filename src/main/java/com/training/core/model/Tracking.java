package com.training.core.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for cargo tracking
 */

@Entity
@Table(name = "tracking")
@Data
public class Tracking {

    /**
     * Unique identifier of the tracking
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date and time of cargo registration at the sorting point
     */
    @Column(name = "registration_time")
    private LocalDateTime registrationTime;

    /**
     * City where the cargo is located
     */
    @Column(name = "city")
    private String city;

    /**
     * Time of object creation
     */
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    /**
     * Update time
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}

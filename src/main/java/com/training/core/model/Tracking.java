package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    /**
     * Update time
     */
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}

package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for the address
 */
@Entity
@Table
@Data
public class Address {

    /**
     * Unique identifier of the address
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Subject of the country of sending or delivery
     */
    @Column(name = "region")
    private String region;

    /**
     * City of sending or delivery
     */
    @Column(name = "city")
    private String city;

    /**
     * Street of sending or delivery
     */
    @Column(name = "street")
    private String street;

    /**
     * House number of sending or delivery
     */
    @Column(name = "house")
    private String house;

    /**
     * Flat number of sending or delivery
     */
    @Column(name = "apartment")
    private String apartment;

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

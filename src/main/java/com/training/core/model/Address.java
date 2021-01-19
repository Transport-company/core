package com.training.core.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for the address
 */

@Entity
@Table(name = "address")
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
    @Column(name = "flat")
    private String flat;

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

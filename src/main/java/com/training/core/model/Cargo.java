package com.training.core.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity for cargo
 */

@Entity
@Table(name = "саrgo")
@Data
public class Cargo {

    /**
     * Unique identifier of the cargo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Weight of the cargo
     */
    @Column(name = "weight")
    private Float weight;

    /**
     * Price of the cargo
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * Cargo packing length
     */
    @Column(name = "length")
    private Float length;

    /**
     * Cargo packing width
     */
    @Column(name = "width")
    private Float width;

    /**
     * Cargo packing height
     */
    @Column(name = "height")
    private Float height;

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

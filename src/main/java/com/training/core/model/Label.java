package com.training.core.model;

import lombok.Data;
import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;

/**
 * Entity for the label
 */

@Entity
@Table(name = "label")
@Data
public class Label {

    /**
     * Unique identifier of the label
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Information on delivery
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    /**
     * File with label
     */
    @Column(name = "label")
    private byte[] label;

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

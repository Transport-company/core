package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    /**
     * File with label
     */
    @Column(name = "labelFile")
    private byte[] labelFile;

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

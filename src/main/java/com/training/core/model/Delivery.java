package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for delivery of cargo
 */

@Entity
@Table(name = "delivery")
@Data
public class Delivery {

    /**
     * Unique identifier of the delivery
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tracking number of the cargo
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tracking_id", referencedColumnName = "id")
    private Tracking trackingNumber;

    /**
     * Information about payment for cargo transit
     */
    @Column(name = "is_paid")
    private Boolean isPaid;

    /**
     * Information about the delivery stage (cargo status)
     */
    @Column(name = "status")
    private Enum status;

    /**
     *  Cargo information
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    /**
     * Information about the client
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    /**
     * Sending address
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sending_address_id", referencedColumnName = "id")
    private Address sendingAddress;

    /**
     * Shipping address
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

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

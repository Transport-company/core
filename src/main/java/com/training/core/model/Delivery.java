package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

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
     * Information about the client's desire to receive notifications
     */
    @Column(name = "enabled_notifications")
    private Boolean enabledNotifications;

    /**
     * Cost of delivery
     */
    @Column(name = "sum")
    private BigDecimal sum;

    /**
     * Unique delivery number
     */
    @Column(name = "tracking_number")
    private String trackingNumber;

    /**
     * Tracking number of the cargo
     */
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL,  orphanRemoval=true, fetch = FetchType.LAZY)
    private Set<Tracking> tracking;

    /**
     * Information about payment for cargo transit
     */
    @Column(name = "is_paid")
    private Boolean isPaid;

    /**
     * Information about the delivery stage (cargo status)
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    /**
     *  Cargo information
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    /**
     * Information about the client
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Sending address
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sending_address_id", referencedColumnName = "id")
    private Address sendingAddress;

    /**
     * Shipping address
     */
    @OneToOne(cascade = CascadeType.PERSIST)
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
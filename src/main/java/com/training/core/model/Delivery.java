package com.training.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    /**
     * Unique identifier of the delivery
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Information about the client's desire to enable notifications
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
    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    /**
     * Information about the sender
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private Client sender;

    /**
     * Information about the recipient
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    private Client recipient;

    /**
     * Sending address
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "sending_address_id")
    private Address sendingAddress;

    /**
     * Shipping address
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id")
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
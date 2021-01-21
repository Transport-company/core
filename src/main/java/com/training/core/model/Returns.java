package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for return of cargo
 */
@Entity
@Table(name = "returns")
@Data
public class Returns {

    /**
     * Unique identifier of return of cargo
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
     * Reason for return of cargo
     */
    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    private ReturnReason reason;

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

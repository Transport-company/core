package com.training.core.model;

import lombok.Data;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery-id", referencedColumnName = "id")
    private Delivery delivery;

    /**
     * Reason for return of cargo
     */
    @Column(name = "reason")
    private String reason;

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

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

/**
 * Entity for a delivery cheque
 */
@Entity
@Table(name = "cheque")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {

    /**
     * Unique identifier of the cheque
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cost of delivery
     */
    @Column(name = "sum")
    private BigDecimal sum;

    /**
     * Information on delivery
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    /**
     * File with the delivery payment cheque
     */
    @Column(name = "cheque_file")
    private byte[] chequeFile;

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

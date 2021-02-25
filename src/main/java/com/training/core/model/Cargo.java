package com.training.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity for cargo
 */
@Entity
@Table(name = "cargo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cargo {

    /**
     * Unique identifier of the cargo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Weight of the cargo (kg)
     */
    @Column(name = "weight")
    private Float weight;

    /**
     * Price of the cargo
     */
    @Column(name = "declared_value")
    private BigDecimal declaredValue;

    /**
     * Cargo packing length (cm)
     */
    @Column(name = "length")
    private Float length;

    /**
     * Cargo packing width (cm)
     */
    @Column(name = "width")
    private Float width;

    /**
     * Cargo packing height (cm)
     */
    @Column(name = "height")
    private Float height;

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

    /**
     * Cargo volume (m3).
     *
     * @return cargo volume  (m3)
     */
    public float getVolume() {
        return length * width * height / 1000f;
    }
}

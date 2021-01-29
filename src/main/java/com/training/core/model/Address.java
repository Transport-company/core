package com.training.core.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity for the address
 */
@Entity
@Table(name = "address")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    /**
     * Unique identifier of the address
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Subject of the country of sending or delivery
     */
    @Column(name = "region")
    private String region;

    /**
     * City of sending or delivery
     */
    @Column(name = "city")
    private String city;

    /**
     * Street of sending or delivery
     */
    @Column(name = "street")
    private String street;

    /**
     * House number of sending or delivery
     */
    @Column(name = "house")
    private String house;

    /**
     * Flat number of sending or delivery
     */
    @Column(name = "apartment")
    private String apartment;

    /**
     * The code of address entity for quick search in the database.
     */
    @Column(name = "code")
    private int code;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getRegion().equals(address.getRegion())
                && getCity().equals(address.getCity())
                && Objects.equals(getStreet(), address.getStreet())
                && getHouse().equals(address.getHouse())
                && Objects.equals(getApartment(), address.getApartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegion(), getCity(), getStreet(), getHouse(), getApartment());
    }
}

package com.training.core.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for the client
 */
@Entity
@Table(name = "client")
@Data
public class Client {

    /**
     * Unique identifier of client
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The last name of the client
     */
    @Column(name = "last_name")
    private String lastname;

    /**
     * The first name of the client
     */
    @Column(name = "first_name")
    private String firstname;

    /**
     * The patronymic of the client
     */
    @Column(name = "middle_name")
    private String middlename;

    /**
     *
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * The email of the client
     */
    @Column(name = "email")
    private String email;

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

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
     * The surname of the client
     */
    @Column(name = "surname")
    private String surname;

    /**
     * The name of the client
     */
    @Column(name = "name")
    private String name;

    /**
     * The patronymic of the client
     */
    @Column(name = "patronymic")
    private String patronymic;

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

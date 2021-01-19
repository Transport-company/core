package com.training.core.model;

import lombok.Data;
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
     * The name of the client
     */
    @Column(name = "name")
    private String name;

    /**
     * The email of the client
     */
    @Column(name = "email")
    private String email;

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

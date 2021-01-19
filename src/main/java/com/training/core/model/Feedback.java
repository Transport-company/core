package com.training.core.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity to feedback on the delivery
 */

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

    /**
     * Unique identifier of feedback about the delivery
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Information on delivery
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    /**
     * Contents of the feedback
     */
    @Column(name = "content")
    private String content;

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

package com.training.core.model;

public enum DeliveryStatus {
    /**
     * Delivery record is open.
     */
    REGISTERED,

    /**
     * Delivery is paid.
     */
    PAID,

    /**
     * Cargo in transfer.
     */
    IN_TRANSFER,

    /**
     * Cargo is in the destination city. Forwarded to courier delivery.
     */
    ON_COURIER_DELIVERY,

    /**
     * The cargo is on the way back to the sender.
     */
    ON_RETURN,

    /**
     *  The cargo was delivered. Shipping is closed.
     */
    DELIVERED,

    /**
     * The cargo was returned to the sender.
     */
    RETURNED,

    /**
     * The cargo was lost
     */
    LOST
}

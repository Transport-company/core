package com.training.core.model;

public enum DeliveryStatus {
    /**
     * The order for delivery is registered.
     */
    REGISTERED,

    /**
     * The delivery is paid.
     */
    PAID,

    /**
     * The cargo is in transfer.
     */
    IN_TRANSFER,

    /**
     * The cargo is in the destination city. Forwarded to courier delivery.
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
     * The cargo was returned to the sender. Shipping is closed.
     */
    RETURNED,

    /**
     * The cargo was lost. Shipping is closed.
     */
    LOST
}

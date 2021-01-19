package com.training.core;

public interface Urls {
    String ROOT = "/";

    interface OrderController {
        String PART = "orders";
        String FULL = ROOT + "/" + PART;
    }

    interface PaymentController {
        String PART = "payments";
        String FULL = ROOT + "/" + PART;
    }

    interface TrackingController {
        String PART = "traking";
        String FULL = ROOT + "/" + PART;
    }

    interface ReturnController {
        String PART = "returns";
        String FULL = ROOT + "/" + PART;
    }

    interface FeedbackController {
        String PART = "feedbacks";
        String FULL = ROOT + "/" + PART;
    }
}

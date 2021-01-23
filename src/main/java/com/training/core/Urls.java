package com.training.core;

public interface Urls {
    String ROOT = "";

    interface Orders {
        String PART = "orders";
        String FULL = ROOT + "/" + PART;
    }

    interface Payments {
        String PART = "payments";
        String FULL = ROOT + "/" + PART;
    }

    interface Tracking {
        String PART = "tracking";
        String FULL = ROOT + "/" + PART;
    }

    interface Returns {
        String PART = "returns";
        String FULL = ROOT + "/" + PART;
    }

    interface Feedbacks {
        String PART = "feedbacks";
        String FULL = ROOT + "/" + PART;
    }
}

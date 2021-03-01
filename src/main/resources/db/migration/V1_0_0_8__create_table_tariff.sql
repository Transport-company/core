CREATE TABLE IF NOT EXISTS tariff
(
    id                    bigserial PRIMARY KEY,
    effective_date        date           NOT NULL,
    order_sum             numeric(19, 2) NOT NULL,
    courier_sum           numeric(19, 2) NOT NULL,
    distance_price        numeric(19, 2) NOT NULL,
    min_distance          int4           NOT NULL,
    distance_threshold    numeric(19, 2) NOT NULL,
    reduction_factor      numeric(19, 2) NOT NULL,
    weight_unit           float          NOT NULL DEFAULT 1,
    weight_threshold      float          NOT NULL DEFAULT 1000,
    weight_ratio_increase float          NOT NULL DEFAULT 0,
    volume_unit           float          NOT NULL DEFAULT 1,
    volume_threshold      float          NOT NULL DEFAULT 1000,
    volume_ratio_increase float          NOT NULL DEFAULT 0,
    created               timestamp      NOT NULL,
    updated               timestamp      NOT NULL,
        CONSTRAINT effective_date_unique UNIQUE (effective_date)
);

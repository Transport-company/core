DELETE
FROM tariff;

INSERT INTO tariff(id, effective_date, order_sum, courier_sum, distance_price,
                   min_distance, distance_threshold, reduction_factor,
                   weight_unit, weight_threshold, weight_ratio_increase,
                   volume_unit, volume_threshold, volume_ratio_increase,
                   created, updated)
VALUES (1, '1900-01-01', 100.00, 500.0, 0.50,
        10, 800, 0.50,
        1.0, 1.0, 0.1,
        0.1, 0.125, 0.1,
        '2020-12-12', '2020-12-12');

INSERT INTO tariff(id, effective_date, order_sum, courier_sum, distance_price,
                   min_distance, distance_threshold, reduction_factor,
                   weight_unit, weight_threshold, weight_ratio_increase,
                   volume_unit, volume_threshold, volume_ratio_increase,
                   created, updated)
VALUES (2, '2020-01-01', 110.00, 490.0, 0.50,
        12, 1000, 0.50,
        1.0, 1.0, 0.1,
        0.1, 0.125, 0.1,
        '2020-12-12', '2020-12-12');

INSERT INTO tariff(id, effective_date, order_sum, courier_sum, distance_price,
                   min_distance, distance_threshold, reduction_factor,
                   weight_unit, weight_threshold, weight_ratio_increase,
                   volume_unit, volume_threshold, volume_ratio_increase,
                   created, updated)
VALUES (3, '2020-12-31', 99.00, 490.0, 0.50,
        9, 999, 0.50,
        1.0, 1.0, 0.1,
        0.1, 0.125, 0.1,
        '2020-12-12', '2020-12-12');

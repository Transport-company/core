DELETE FROM delivery;
DELETE FROM address;
DELETE FROM client;
DELETE FROM cargo;
DELETE FROM tariff;

INSERT INTO cargo (id, weight, declared_value, length, width, height, created, updated)
VALUES (1, 54, 55, 5, 6, 23, '2020-12-12', '2020-12-12');
INSERT INTO client (id, last_name, first_name, middle_name, phone_number, email, created, updated, birthday)
VALUES (1, 'Sidorov','Ivan', 'Ivanovich','89356398565', 'Sidorov@gmail.com', '2020-12-12', '2020-12-12', '2020-11-11');
INSERT INTO client (id, last_name, first_name, middle_name, phone_number, email, created, updated, birthday)
VALUES (2, 'Ivanov','Ivan', 'Ivanovich','99999999999', 'Ivan@gmail.com', '2020-12-12', '2020-12-12', '2020-12-12');
INSERT INTO address (id, region, city, street, house, apartment, created, updated, code)
VALUES (1, 'Ulyanovsk region', 'Ulyanovsk', 'Goncharova', '1', '1', '2020-12-12', '2020-12-12', 1234);
INSERT INTO address (id, region, city, street, house, apartment, created, updated, code)
VALUES (2, 'Ulyanovsk region', 'Ulyanovsk', 'Lenina', '1', '1', '2020-12-12', '2020-12-12', 4321);
INSERT INTO delivery (id, sum, enabled_notifications, tracking_number, is_paid, status, cargo_id, sender_id, recipient_id,  sending_address_id, shipping_address_id, created, updated)
VALUES (1, 5.12, true, 'sdf44', true, 'REGISTERED', 1, 1, 2, 1, 2, '2020-12-12', '2020-12-12');
INSERT INTO tariff (id, effective_date, order_sum, courier_sum, distance_price, min_distance, distance_threshold, reduction_factor, created, updated)
VALUES (1, '1900-01-01', 100.00, 500.00, 0.50, 10, 800, 0.5, '2020-12-12', '2020-12-12');


delete from delivery;
delete from address;
delete from client;
delete from cargo;

INSERT INTO cargo (id, weight, declared_value, length, width, height, created, updated)
VALUES (1, 54, 55, 5, 6, 23, parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'));
INSERT INTO client (id, first_name, phone_number, email, created, updated, birthday)
VALUES (1, 'Alex', '897654321', 'A@.gmail.com', parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'));
INSERT INTO client (id, first_name, phone_number, email, created, updated, birthday)
VALUES (2, 'Tim', '899994321', 'T@.gmail.com', parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'));
INSERT INTO address (id, region, city, street, house, apartment, created, updated, code)
VALUES (1, 'Astrakhan region', 'Astrakhan', 'Lenina', '11A', '12', parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'), 1234);
INSERT INTO address (id, region, city, street, house, apartment, created, updated, code)
VALUES (2, 'Astrakhan region', 'Astrakhan', 'Karl Marx', '1', '102', parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'), 4321);
INSERT INTO delivery (id, sum, enabled_notifications, tracking_number, is_paid, status, cargo_id, sender_id, recipient_id,  sending_address_id, shipping_address_id, created, updated)
VALUES (1, 5.12, true, 'sdf44', true, 'REGISTERED', 1, 1, 2, 1, 2, parsedatetime('12-12-2020', 'dd-MM-yyyy'), parsedatetime('12-12-2020', 'dd-MM-yyyy'));


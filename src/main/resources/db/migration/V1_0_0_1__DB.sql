CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start 1000 increment 1;

CREATE TABLE IF NOT EXISTS address
(
    id                    serial PRIMARY KEY,
    region       varchar(255)       NOT NULL,
    city         varchar(255)       NOT NULL,
    street       varchar(255)       NOT NULL,
    house        varchar(255)       NOT NULL,
    apartment    varchar(255)       NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL
);

CREATE TABLE IF NOT EXISTS cargo
(
    id                     serial PRIMARY KEY,
    weight              float        NOT NULL,
    declareited_value   decimal      NOT NULL,
    length              float        NOT NULL,
    width               float        NOT NULL,
    height              float        NOT NULL,
    created             date         NOT NULL,
    updated             date         NOT NULL
);

CREATE TABLE IF NOT EXISTS cheque
(
    id                    serial PRIMARY KEY,
    sum          decimal            NOT NULL,
    delivery_id  int8               NOT NULL,
    cheque_file  varbit             NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS client
(
    id                    serial PRIMARY KEY,
    name         varchar(255)       NOT NULL,
    email        varchar(255)       NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery
(
    id                              serial PRIMARY KEY,
    enabled_notifications boolean             NOT NULL,
    sum                   decimal             NOT NULL,
    tracking_id           int8                NOT NULL,
    is_paid               boolean             NOT NULL,
    status                delivery_status     NOT NULL,
    cargo_id              int8                NOT NULL,
    client_id             int8                NOT NULL,
    sending_address_id    int8                NOT NULL,
    shipping_address_id   int8                NOT NULL,
    created               date                NOT NULL,
    updated               date                NOT NULL,
    FOREIGN KEY (tracking_id) REFERENCES tracking (id),
    FOREIGN KEY (cargo_id) REFERENCES cargo (id),
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (sending_address_id) REFERENCES address (id),
    FOREIGN KEY (shipping_address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS feedback
(
    id                    serial PRIMARY KEY,
    delivery_id  int8               NOT NULL,
    content      varchar(255)       NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS label
(
    id                    serial PRIMARY KEY,
    delivery_id  int8               NOT NULL,
    label        varbit             NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS returns
(
    id                    serial PRIMARY KEY,
    delivery_id  int8               NOT NULL,
    reason       varchar(255)       NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS tracking
(
    id                    serial PRIMARY KEY,
    city         varchar(255)       NOT NULL,
    created      date               NOT NULL,
    updated      date               NOT NULL
);

CREATE TYPE delivery_status AS ENUM ('PICKED_UP', 'ON_DELIVERY', 'DELIVERED', 'FAILED', 'LOST');
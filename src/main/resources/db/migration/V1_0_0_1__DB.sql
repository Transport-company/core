CREATE TABLE IF NOT EXISTS address
(
    id           bigserial          PRIMARY KEY,
    region       varchar(50)        NOT NULL,
    city         varchar(28)        NOT NULL,
    street       varchar(95)        ,
    house        varchar(25)        NOT NULL,
    apartment    varchar(25)        ,
    created      timestamp          NOT NULL,
    updated      timestamp          NOT NULL
);

CREATE TABLE IF NOT EXISTS cargo
(
    id                  bigserial    PRIMARY KEY,
    weight              float        NOT NULL,
    declared_value      decimal      NOT NULL,
    length              float        NOT NULL,
    width               float        NOT NULL,
    height              float        NOT NULL,
    created             timestamp    NOT NULL,
    updated             timestamp    NOT NULL
);

CREATE TABLE IF NOT EXISTS client
(
    id           bigserial          PRIMARY KEY,
    surname      varchar(50)        ,
    name         varchar(50)        NOT NULL,
    patronymic   varchar(50)        ,
    email        varchar(62)        NOT NULL,
    created      timestamp          NOT NULL,
    updated      timestamp          NOT NULL
);

CREATE TABLE IF NOT EXISTS delivery
(
    id                    bigserial          PRIMARY KEY,
    enabled_notifications boolean            NOT NULL,
    sum                   numeric(19,2)      NOT NULL,
    tracking_number       varchar(30)        NOT NULL,
    is_paid               boolean            NOT NULL,
    status                varchar(10)        NOT NULL,
    cargo_id              bigserial          NOT NULL,
    client_id             bigserial          NOT NULL,
    sending_address_id    bigserial          NOT NULL,
    shipping_address_id   bigserial          NOT NULL,
    created               timestamp          NOT NULL,
    updated               timestamp          NOT NULL,
    FOREIGN KEY (cargo_id) REFERENCES cargo (id),
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (sending_address_id) REFERENCES address (id),
    FOREIGN KEY (shipping_address_id) REFERENCES address (id)
);

CREATE TABLE IF NOT EXISTS cheque
(
    id           bigserial          PRIMARY KEY,
    sum          numeric(19,2)      NOT NULL,
    delivery_id  bigserial          NOT NULL,
    cheque_file  varbit             NOT NULL,
    created      timestamp          NOT NULL,
    updated      timestamp          NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);


CREATE TABLE IF NOT EXISTS feedback
(
    id           bigserial          PRIMARY KEY,
    delivery_id  bigserial          NOT NULL,
    message      varchar(255)       NOT NULL,
    created      timestamp          NOT NULL,
    updated      timestamp          NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS label
(
    id           bigserial          PRIMARY KEY,
    delivery_id  bigserial          NOT NULL,
    label_file   varbit             NOT NULL,
    created      timestamp          NOT NULL,
    updated      timestamp          NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS returns
(
    id           bigserial          PRIMARY KEY,
    delivery_id  bigserial          NOT NULL,
    reason       varchar(13)        NOT NULL,
    created      timestamp          NOT NULL,
    updated      timestamp          NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);

CREATE TABLE IF NOT EXISTS tracking
(
    id              bigserial          PRIMARY KEY,
    tracking_number varchar(30)        NOT NULL,
    delivery_id     bigserial          NOT NULL,
    city            varchar(28)        NOT NULL,
    created         timestamp          NOT NULL,
    updated         timestamp          NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery (id)
);
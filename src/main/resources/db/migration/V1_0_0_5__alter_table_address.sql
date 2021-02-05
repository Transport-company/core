ALTER TABLE address
    ADD COLUMN code int4;

CREATE INDEX ON address (code);

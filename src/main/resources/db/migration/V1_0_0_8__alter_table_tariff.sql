ALTER TABLE tariff
    ADD COLUMN created timestamp NOT NULL,
    ADD COLUMN updated timestamp NOT NULL;
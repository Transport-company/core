ALTER TABLE delivery
    ALTER COLUMN tracking_number DROP NOT NULL;

ALTER TABLE delivery
    ADD CONSTRAINT delivery_tracking_number_unique UNIQUE (tracking_number);

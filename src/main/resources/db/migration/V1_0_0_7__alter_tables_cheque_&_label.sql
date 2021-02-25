ALTER TABLE cheque
    DROP COLUMN cheque_file;

ALTER TABLE cheque
    ADD COLUMN cheque_file bytea NOT NULL;

ALTER TABLE label
    DROP COLUMN label_file;

ALTER TABLE label
    ADD COLUMN label_file bytea NOT NULL;

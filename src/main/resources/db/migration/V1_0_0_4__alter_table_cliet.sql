ALTER TABLE client
    DROP CONSTRAINT client_phone_number_key;

ALTER TABLE client
    ADD CONSTRAINT client_email_key UNIQUE (email);
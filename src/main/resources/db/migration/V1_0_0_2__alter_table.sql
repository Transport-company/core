ALTER TABLE client RENAME column surname       to last_name;
ALTER TABLE client RENAME column name          to first_name;
ALTER TABLE client RENAME column patronymic    to middle_name;
ALTER TABLE client add column    phone_number  varchar (20);

ALTER TABLE delivery DROP CONSTRAINT delivery_client_id_fkey;
ALTER TABLE delivery RENAME column client_id      to sender_id;
ALTER TABLE delivery add column    recipient_id  int8 NOT NULL;
ALTER TABLE delivery add FOREIGN KEY (recipient_id) REFERENCES client (id);
ALTER TABLE delivery add FOREIGN KEY (sender_id) REFERENCES client (id);





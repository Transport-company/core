ALTER TABLE tariff
    ADD COLUMN weight_unit           float NOT NULL DEFAULT 1,
    ADD COLUMN weight_threshold      float NOT NULL DEFAULT 1000,
    ADD COLUMN weight_ratio_increase float NOT NULL DEFAULT 0,
    ADD COLUMN volume_unit           float NOT NULL DEFAULT 1,
    ADD COLUMN volume_threshold      float NOT NULL DEFAULT 1000,
    ADD COLUMN volume_ratio_increase float NOT NULL DEFAULT 0;
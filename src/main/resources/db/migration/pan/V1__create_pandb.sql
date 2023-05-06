DROP TABLE IF EXISTS credit_card_pan;

CREATE TABLE credit_card_pan
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    credit_card_number VARCHAR(30),
    PRIMARY KEY (id)
);
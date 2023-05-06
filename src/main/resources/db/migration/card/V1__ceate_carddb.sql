DROP TABLE IF EXISTS credit_card;

CREATE TABLE credit_card (
    id              BIGINT NOT NULL AUTO_INCREMENT,
    cvv             VARCHAR(30),
    expiration_date VARCHAR(30),
    PRIMARY KEY (id)
);

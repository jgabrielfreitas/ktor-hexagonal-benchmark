CREATE TABLE users
(
    id                varchar(50)        NOT NULL,
    email             varchar(255)       NOT NULL,
    name              varchar(255)       NOT NULL,
    sent_notification bit(1)             NOT NULL,
    idempotency_id    varchar(50) UNIQUE NOT NULL,
    PRIMARY KEY (id)
)

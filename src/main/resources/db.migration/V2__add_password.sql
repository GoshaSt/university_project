ALTER TABLE customers
    ADD COLUMN password VARCHAR(255) NOT NULL;

CREATE TABLE user_roles
(
    user_id BIGINT      NOT NULL,
    role    VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT user_roles_fk FOREIGN KEY (user_id) REFERENCES customers (id)
);
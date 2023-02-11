CREATE TABLE customers
(
    customer_id SERIAL PRIMARY KEY,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    email       VARCHAR(100) NOT NULL
);

CREATE TABLE orders
(
    order_id    SERIAL PRIMARY KEY,
    order_date  DATE    NOT NULL,
    customer_id INTEGER NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

CREATE TABLE products
(
    product_id   SERIAL PRIMARY KEY,
    product_name VARCHAR(50)    NOT NULL,
    price        NUMERIC(10, 2) NOT NULL
);

CREATE TABLE order_items
(
    order_item_id SERIAL PRIMARY KEY,
    order_id      INTEGER NOT NULL,
    product_id    INTEGER NOT NULL,
    quantity      INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE shipping_addresses
(
    shipping_address_id SERIAL PRIMARY KEY,
    order_id            INTEGER      NOT NULL,
    address             VARCHAR(100) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);
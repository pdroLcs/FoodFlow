CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(12,2) NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_products_category
      FOREIGN KEY (category_id)
          REFERENCES categories (id)
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_value NUMERIC(12,2) NOT NULL,
    CONSTRAINT chk_orders_status
        CHECK (status IN ('PENDING', 'IN_PREPARATION', 'READY', 'DELIVERED'))
);

CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL,
    unit_price NUMERIC(12,2) NOT NULL,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_order_items_product
     FOREIGN KEY (product_id)
         REFERENCES products (id),
    CONSTRAINT fk_order_items_order
     FOREIGN KEY (order_id)
         REFERENCES orders (id)
         ON DELETE CASCADE
);

CREATE INDEX idx_products_category_id ON products (category_id);
CREATE INDEX idx_order_items_product_id ON order_items (product_id);
CREATE INDEX idx_order_items_order_id ON order_items (order_id);
CREATE INDEX idx_orders_status ON orders (status);
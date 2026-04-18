CREATE TABLE tables (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL UNIQUE,
    number INTEGER NOT NULL UNIQUE,
    free BOOLEAN NOT NULL DEFAULT TRUE
);

ALTER TABLE orders
ADD COLUMN table_id BIGINT;

ALTER TABLE orders
ADD CONSTRAINT fk_orders_table
    FOREIGN KEY (table_id)
    REFERENCES tables(id)
    ON DELETE SET NULL;

CREATE INDEX idx_orders_table_id ON orders(table_id);
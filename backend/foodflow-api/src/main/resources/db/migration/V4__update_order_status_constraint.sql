ALTER TABLE orders
DROP CONSTRAINT chk_order_status

ALTER TABLE orders
ADD CONSTRAINT chk_order_status
CHECK ( status IN ('PENDING', 'READY'))
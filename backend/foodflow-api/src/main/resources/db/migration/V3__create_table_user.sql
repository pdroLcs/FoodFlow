CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    email varchar(255),
    password varchar(255),
    role varchar(5) NOT NULL DEFAULT 'ADMIN'
)
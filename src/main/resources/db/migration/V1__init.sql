CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255) UNIQUE ,
    display_name VARCHAR(255),
    password     VARCHAR(255),
    avatar       VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
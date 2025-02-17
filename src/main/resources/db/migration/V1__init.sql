CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255) UNIQUE,
    display_name VARCHAR(255),
    password     VARCHAR(255),
    avatar       VARCHAR(255),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chats
(
    id         SERIAL PRIMARY KEY,
    user1_id   INT,
    user2_id   INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user1_id) REFERENCES users (id),
    FOREIGN KEY (user2_id) REFERENCES users (id)
)
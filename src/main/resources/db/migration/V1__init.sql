CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(255) UNIQUE,
    display_name VARCHAR(255),
    password     VARCHAR(255),
    avatar       VARCHAR(255),
    roles        VARCHAR(255)             DEFAULT 'ROLE_USER',
    created_at   TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    updated_at   TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7')
);

CREATE TABLE chats
(
    id         SERIAL PRIMARY KEY,
    user1_id   INT,
    user2_id   INT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    FOREIGN KEY (user1_id) REFERENCES users (id),
    FOREIGN KEY (user2_id) REFERENCES users (id)
);

CREATE TABLE attachments
(
    id         SERIAL PRIMARY KEY,
    url        VARCHAR(255),
    name       VARCHAR(255),
    type       VARCHAR(255),
    size       INT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7')
);

CREATE TABLE metadata
(
    id         SERIAL PRIMARY KEY,
    title      VARCHAR(255),
    image      VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7')
);

CREATE TABLE messages
(
    id            SERIAL PRIMARY KEY,
    chat_id       INT,
    sender_id     INT,
    message       TEXT,
    attachment_id INT,
    metadata_id   INT,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT (NOW() AT TIME ZONE 'GMT-7'),
    FOREIGN KEY (chat_id) REFERENCES chats (id),
    FOREIGN KEY (sender_id) REFERENCES users (id),
    FOREIGN KEY (attachment_id) REFERENCES attachments (id),
    FOREIGN KEY (metadata_id) REFERENCES metadata (id)
);

CREATE OR REPLACE FUNCTION update_chat_updated_at()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE chats
    SET updated_at = CURRENT_TIMESTAMP
    WHERE id = NEW.chat_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_chat_updated_at
    AFTER INSERT
    ON messages
    FOR EACH ROW
EXECUTE FUNCTION update_chat_updated_at();
-- Insert user
INSERT INTO users (username, display_name, password, avatar)
VALUES
('hien1', 'Hien1', '1', null),
('hien2', 'Hien2', '2', null),
('hien3', 'Hien3', '3', null),
('hien4', 'Hien4', '4', null),
('hien5', 'Hien5', '5', null);

-- Insert chat
INSERT INTO chats (user1_id, user2_id)
VALUES
(1, 2),
(1, 3),
(1, 4),
(3, 4);

-- Insert message
INSERT INTO messages (chat_id, sender_id, message)
VALUES
(1, 1, 'Hello, Hien2!'),
(2, 3, 'Hello, Hien3!');
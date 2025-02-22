-- Insert user
INSERT INTO users (username, display_name, password, avatar)
VALUES ('hien', 'Hien', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa', 'https://i.pravatar.cc/150?img=1'),
       ('hien1', 'Hien1', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa', 'https://i.pravatar.cc/150?img=2'),
       ('hien2', 'Hien2', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa', 'https://i.pravatar.cc/150?img=3'),
       ('hien3', 'Hien3', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa', 'https://i.pravatar.cc/150?img=4'),
       ('hien4', 'Hien4', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa', 'https://i.pravatar.cc/150?img=5'),
       ('hien5', 'Hien5', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa', 'https://i.pravatar.cc/150?img=6');

-- Insert chat
INSERT INTO chats (user1_id, user2_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (3, 4);

-- Insert message
INSERT INTO messages (chat_id, sender_id, message)
VALUES (1, 1, 'Hello, Hien2!');

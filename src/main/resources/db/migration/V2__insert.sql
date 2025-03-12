-- Insert user
INSERT INTO users (username, display_name, password, avatar)
VALUES ('hien', 'Hien', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa',
        'https://fastly.picsum.photos/id/2/150/150.jpg?hmac=PEdsKdTGwPruqCOY5M8AGPdqPl0gWiXkIhxvyC9WWio'),
       ('test', 'Test account', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa',
        'https://fastly.picsum.photos/id/12/150/150.jpg?hmac=Lyk1fcMd8dMA5pdvS8scSpGQQkJoqmjYnT_fbASpwnI'),
       ('admin', 'Admin', '$2a$10$4f/J2nSmZMWljuY7r0bjeOHZSjC..4vN46gjFjRf43vQPI5coLiRa',
        null);
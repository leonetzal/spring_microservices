INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('leonardo', '$2a$10$f5fGCkRR7TdzlhTlYBxTGuG1nuA97dyScG8C.a.8GRdciRVJcTWxy', 1, 'Leonardo', 'Lucio', 'leon.etzal@hotmail.com');
INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('admin', '$2a$10$8qe.2L0gDsw1vi2iZEPdVelr3gZ3ts.7.Qk5xmEeG8CQbU0CtHstG', 1, 'John', 'Doe', 'john.doe@example.com');

INSERT INTO roles (name) VALUES('ROLE_USER');
INSERT INTO roles (name) VALUES('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
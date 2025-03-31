USE db_menciones;


INSERT IGNORE INTO roles (id,name) VALUES (1, 'USER');
INSERT IGNORE INTO roles (id,name) VALUES (2, 'ADMIN');

INSERT IGNORE INTO users_roles(role_id, user_id) VALUES (2,1);
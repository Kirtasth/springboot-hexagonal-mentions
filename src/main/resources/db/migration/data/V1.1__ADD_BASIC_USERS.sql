INSERT IGNORE INTO roles (id,name) VALUES (1, 'USER');
INSERT IGNORE INTO roles (id,name) VALUES (2, 'ADMIN');

INSERT IGNORE INTO users (id, username, email, password, enabled, account_non_expired, account_non_locked,
                          credentials_non_expired, created_at, updated_at) VALUES (1, 'Kirtasth',
                    'davidsotoarb@gmail.com', '$2a$10$OBX.51s4nYRyvWUmIQqXcuA1q3i7hxiNAOqhowyjdQTOgOdRnzNEu',
                  true, true, true, true,
                '2025-04-14 18:47:38', '2025-04-14 18:47:38');

INSERT IGNORE INTO users_roles (role_id, user_id) VALUES (2,1);
INSERT IGNORE INTO users_roles (role_id, user_id) VALUES (1,1);
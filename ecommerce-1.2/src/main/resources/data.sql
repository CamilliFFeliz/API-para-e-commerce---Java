
-- Usuários (senhas com bcrypt para 'password' geradas com BCrypt)
INSERT INTO users (id, username, password, role) VALUES (1, 'admin', '$2a$10$7sXK1QJu9sF6s0qf6iYqeOZ3x1kQqj5g0GQkV1h3G6C6qzYf8aG9W', 'ADMIN');
INSERT INTO users (id, username, password, role) VALUES (2, 'cliente', '$2a$10$7sXK1QJu9sF6s0qf6iYqeOZ3x1kQqj5g0GQkV1h3G6C6qzYf8aG9W', 'USER');

-- Produtos
INSERT INTO product (id, name, description, price, stock) VALUES (1, 'Camiseta', 'Camiseta 100% algodão', 39.9, 100);
INSERT INTO product (id, name, description, price, stock) VALUES (2, 'Caneca', 'Caneca cerâmica 300ml', 24.5, 50);
INSERT INTO product (id, name, description, price, stock) VALUES (3, 'Boné', 'Boné com logo', 29.0, 30);

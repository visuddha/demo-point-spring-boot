-- creating demo roles
CREATE TABLE IF NOT EXISTS roles (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255) NOT NULL);
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');



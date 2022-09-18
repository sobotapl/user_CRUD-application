CREATE DATABASE workshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE workshop;

CREATE TABLE users (
                       id INT(11) AUTO_INCREMENT,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(60),
                       PRIMARY KEY (id)

);
DROP SCHEMA IF EXISTS example_db;
CREATE SCHEMA example_db;
USE example_db;

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    `id` BIGINT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book;
CREATE TABLE book (
    `id` BIGINT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    borrowed BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS borrowed_book;
CREATE TABLE borrowed_book (
    employee_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (employee_id, book_id)
);

-- SETUP TEST DATA
INSERT INTO employee (id, name) VALUES (1, "TEST USER A");
INSERT INTO employee (id, name) VALUES (2, "TEST USER B");
INSERT INTO employee (id, name) VALUES (3, "TEST USER C");
INSERT INTO employee (id, name) VALUES (4, "TEST USER D");
INSERT INTO employee (id, name) VALUES (5, "TEST USER E");

INSERT INTO book (id, title) VALUES (1, "TEST BOOK A");
INSERT INTO book (id, title) VALUES (2, "TEST BOOK B");
INSERT INTO book (id, title) VALUES (3, "TEST BOOK C");
INSERT INTO book (id, title) VALUES (4, "TEST BOOK D");
INSERT INTO book (id, title) VALUES (5, "TEST BOOK E");

--ALTER TABLE employee     drop CONSTRAINT IF EXISTS fk_employee_address;
--ALTER TABLE person     drop CONSTRAINT IF EXISTS fk_person_passport;
--ALTER TABLE books drop CONSTRAINT IF EXISTS fk_books_authors;

--drop table if exists employee;
--drop table if exists address;

--drop table if exists passport;
--drop table if exists person;

--drop table if exists artists;
--drop table if exists books;


--OneToOne unidirectional
create table if not exists address
(
    id     INT NOT NULL AUTO_INCREMENT,
    city   VARCHAR(50),
    street VARCHAR(50),
    PRIMARY KEY (id)
);

create table if not exists employee
(
    id         INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    address_id INT NOT NULL,
    PRIMARY KEY (id)
);

--OneToOne bidirectional
create table if not exists passport
(
    id        INT NOT NULL AUTO_INCREMENT,
    number    VARCHAR(50),
    country   VARCHAR(50),
    person_id INT NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists person
(
    id          INT NOT NULL AUTO_INCREMENT,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authors
(
    id   INT NOT NULL,
    name VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS books
(
    id        INT NOT NULL,
    author_id INT NOT NULL,
    title     VARCHAR(50),
    isbn      VARCHAR(50),
    PRIMARY KEY (id)
);

--ALTER TABLE employee     ADD CONSTRAINT IF NOT EXISTS fk_employee_address FOREIGN KEY (address_id) REFERENCES address (id);
--ALTER TABLE person     ADD CONSTRAINT IF NOT EXISTS fk_person_passport FOREIGN KEY (passport_id) REFERENCES passport (id);
--ALTER TABLE books     ADD CONSTRAINT IF NOT EXISTS fk_books_authors FOREIGN KEY (author_id) REFERENCES artists (id);
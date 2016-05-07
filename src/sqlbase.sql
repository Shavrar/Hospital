CREATE DATABASE hospital DEFAULT CHARACTER SET utf8;
USE hospital;
CREATE TABLE users (
    id INTEGER NOT NULL AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO users
(id, login,  password, role)
VALUES
(1, "admin", "12345", "admin"),
(2, "manager", "12345", "manager");

CREATE TABLE specialtys (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    rate INTEGER NOT NULL,
	narrow BOOLEAN,
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO specialtys
(id, name,  rate, narrow)
VALUES
(1, "A", 12, true),
(2, "B", 15, false);

CREATE TABLE doctors (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    workday DATE NOT NULL,
    area INTEGER NOT NULL,
    salary INTEGER NOT NULL,   
	sex VARCHAR(255) NOT NULL,
	specialty_id INTEGER NOT NULL,
	domain_name VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (specialty_id) REFERENCES specialtys(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO doctors
(id, name,  birthday, workday, area, salary, sex, specialty_id)
VALUES
(1, "Mike", "1993-12-12", "2015-12-13", 23, 15, "M", 1);





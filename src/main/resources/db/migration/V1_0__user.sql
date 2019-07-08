CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT,
    first_name varchar(50),
    name varchar(50),
    email varchar(50),
    password varchar(256),
    gender varchar (20)
);

INSERT INTO user(id, first_name, name, email, password, gender)
VALUES (1, 'Schueler', 'Schueler', 'schueler@test.de', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'mw');

INSERT INTO user(id, first_name, name, email, password, gender)
VALUES (2, 'Lehrer', 'Lehrer', 'lehrer@test.de', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'mw');

CREATE TABLE state (
    id bigint NOT NULL AUTO_INCREMENT,
    state_name varchar(10),
    PRIMARY KEY (id)
);

INSERT INTO state (id, state_name) VALUES (1, 'online');
INSERT INTO state (id, state_name) VALUES (2, 'offline');

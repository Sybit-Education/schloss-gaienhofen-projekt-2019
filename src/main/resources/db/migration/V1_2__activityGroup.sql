CREATE TABLE activity_group (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50),
    ag_leader_id bigint,
    description varchar(2048),
    start_date DATE ,
    end_date DATE ,
    max_participants tinyint
);

INSERT INTO activity_group (id, name, ag_leader_id, description, start_date, end_date, max_participants)
VALUES (1, 'AG1', 2, 'AG1 Herzlich Willkommen', '2019-09-01', '2020-09-01', 30);

INSERT INTO activity_group (id, name, ag_leader_id, description, start_date, end_date, max_participants)
VALUES (2, 'AG2', 2, 'AG2 Herzlich Willkommen', '2019-09-01', '2020-09-01', 20);

INSERT INTO activity_group (id, name, ag_leader_id, description, start_date, end_date, max_participants)
VALUES (3, 'AG3', 2, 'AG3 Herzlich Willkommen', '2019-09-01', '2020-09-01', 80);

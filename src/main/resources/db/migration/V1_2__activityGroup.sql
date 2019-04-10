CREATE TABLE activity_group (
    id bigint NOT NULL AUTO_INCREMENT,
    title varchar(50),
    ag_leader varchar(40),
    description varchar(2048),
    start_date DATE ,
    end_date DATE ,
    max_participants tinyint,
    ag_state varchar(10)
);

INSERT INTO activity_group (id, title, ag_leader, description, start_date, end_date, max_participants, ag_state)
VALUES (1, 'AG1', '1', 'AG1 Herzlich Willkommen', '2019-09-01', '2020-09-01', 30, 'online');

INSERT INTO activity_group (id, title, ag_leader, description, start_date, end_date, max_participants, ag_state)
VALUES (2, 'AG2', '2', 'AG2 Herzlich Willkommen', '2019-09-01', '2020-09-01', 20, 'online');

INSERT INTO activity_group (id, title, ag_leader, description, start_date, end_date, max_participants, ag_state)
VALUES (3, 'AG3', '3', 'AG3 Herzlich Willkommen', '2019-09-01', '2020-09-01', 80, 'draft');

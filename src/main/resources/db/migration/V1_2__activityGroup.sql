CREATE TABLE event (
    id bigint NOT NULL AUTO_INCREMENT,
    title varchar(50),
    leader_id varchar(40),
    summary varchar(256),
    description varchar(2048),
    start_date DATE ,
    end_date DATE ,
    max_participants tinyint,
    ag_state varchar(10),
    location varchar(50),
    type varchar(50),
);

INSERT INTO event (id, title, leader_id, summary, description, start_date, end_date, max_participants, ag_state, location, type)
VALUES (1, 'Lerntierchen-Zucht', '1', 'Lerntierchen züchten!', 'Wir werden in der AG im 14-tägigen Turnus kleine Lerntierchen züchten. Wichtig ist, dass du eine Streichholzschachtel für deine Zucht hast und dich regelmässig um die Tiere kümmern kannst. Sie brauchen regelmässigen Lernstoff.', '2019-09-01', '2020-09-01', 30, 'online', 'E2', 'AG');

INSERT INTO event (id, title, leader_id,  summary, description, start_date, end_date, max_participants, ag_state, location, type)
VALUES (2, 'AG2', '2', 'Summary 2', 'AG2 Herzlich Willkommen', '2019-09-01', '2020-09-01', 20, 'online', 'E2', 'AG');

INSERT INTO event (id, title, leader_id,  summary, description, start_date, end_date, max_participants, ag_state, location, type)
VALUES (3, 'AG3', '3', 'Summary 3', 'AG3 Herzlich Willkommen', '2019-09-01', '2020-09-01', 80, 'draft', 'E2', 'AG');

CREATE TABLE activity_group (
    id bigint NOT NULL AUTO_INCREMENT,
    title varchar(50),
    ag_leader varchar(40),
    summary varchar(256),
    description varchar(2048),
    start_date DATE ,
    end_date DATE ,
    max_participants tinyint,
    ag_state varchar(10)
);

INSERT INTO activity_group (id, title, ag_leader, summary, description, start_date, end_date, max_participants, ag_state)
VALUES (1, 'Lerntierchen-Zucht', '1', 'Lerntierchen züchten!', 'Wir werden in der AG im 14-tägigen Turnus kleine <b>Lerntierchen</b> züchten. Wichtig ist, dass du eine Streichholzschachtel für deine Zucht hast und dich regelmässig um die Tiere kümmern kannst. Sie brauchen regelmässigen Lernstoff.', '2019-09-01', '2020-09-01', 30, 'online');

INSERT INTO activity_group (id, title, ag_leader,  summary, description, start_date, end_date, max_participants, ag_state)
VALUES (2, 'AG2', '2', 'Summary 2', 'AG2 Herzlich Willkommen', '2019-09-01', '2020-09-01', 20, 'online');

INSERT INTO activity_group (id, title, ag_leader,  summary, description, start_date, end_date, max_participants, ag_state)
VALUES (3, 'AG3', '3', 'Summary 3', 'AG3 Herzlich Willkommen', '2019-09-01', '2020-09-01', 80, 'draft');

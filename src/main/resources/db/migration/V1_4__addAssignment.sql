
CREATE TABLE attendee (
    id bigint NOT NULL AUTO_INCREMENT,
    attendee_id bigint,
    ag_id bigint,
    assignemt_date DATE
);


INSERT INTO attendee (id, attendee_id, ag_id, assignemt_date)
VALUES (1, 1,1 , '2019-09-01');

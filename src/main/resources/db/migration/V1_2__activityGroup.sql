CREATE TABLE activity_group (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50),
    ag_leader_id bigint,
    description varchar(2048),
    start_date datetime,
    end_date datetime,
    max_participants tinyint
);

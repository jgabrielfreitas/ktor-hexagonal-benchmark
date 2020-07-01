CREATE TABLE events (
                        id            varchar(50)     NOT NULL,
                        json_payload  LONGTEXT        NOT NULL,
                        user_id       varchar(50)     NOT NULL,
                        PRIMARY KEY (id)
);

alter table events add constraint events_to_user foreign key (user_id) references users (id);
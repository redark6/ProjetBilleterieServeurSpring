--liquibase formatted sql

--changeset nvoxland:1
create table rating (
    id SERIAL PRIMARY KEY,
    user_id varchar(50),
    event_id int,
    rating integer,
     CONSTRAINT fk_user_id_rating
          FOREIGN KEY(user_id)
          REFERENCES users(username),
     CONSTRAINT fk_event_id_rating
           FOREIGN KEY(event_id)
           REFERENCES events(id)
);
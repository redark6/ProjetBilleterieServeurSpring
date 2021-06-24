--liquibase formatted sql

--changeset nvoxland:6
create table participation (
    id SERIAL PRIMARY KEY,
    user_id varchar(50),
    event_id int,
    boughtticket int,
     CONSTRAINT fk_user_id_participation
          FOREIGN KEY(user_id)
    	  REFERENCES users(username),
     CONSTRAINT fk_event_id_participation
           FOREIGN KEY(event_id)
           REFERENCES events(id)
);
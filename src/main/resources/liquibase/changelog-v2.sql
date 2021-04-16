--liquibase formatted sql

--changeset id:1

insert into categories values(1,'music');
insert into categories values(2,'sport');


--changeset id:2
Insert into events(title, category, description, region, creationDate, startDate,endDate , price, nbOfTicket ) values ('Wejdene', 1, 'musique géniale', 'Bercy', '2020-11-03','2019-07-03','2019-07-03', 25, 150);
Insert into events(title, category, description, region, creationDate, startDate,endDate , price, nbOfTicket ) values ('Indochine', 2, 'Concert dIndochine', 'Stade de France', '2021-12-03','2019-07-03','2019-07-03', 25, 250);
Insert into events(title, category, description, region, creationDate, startDate,endDate , price, nbOfTicket ) values ('Match Football', 1, 'ALLEZ LES BLEUS', 'Cannes', '2019-07-03','2019-07-03','2019-07-03', 25, 50);

--changeset nvoxland:3
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

--changeset nvoxland:4
Insert into rating(user_id, event_id, rating) values ('test@test.test',1,3);
Insert into rating(user_id, event_id, rating) values ('test2@test.test',2,5);

--changeset nvoxland:5
Insert into rating(user_id, event_id, rating) values ('test2@test.test',1,5);

--changeset nvoxland:5
Insert into rating(user_id, event_id, rating) values ('test2@test.test',1,5);
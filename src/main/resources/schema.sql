CREATE TABLE usersinfo(id SERIAL PRIMARY KEY NOT NULL,firstName varchar(50) NOT NULL, lastName varchar(50) NOT NULL, birthDate Date NOT NULL, userName varchar(50) NOT NULL, email varchar(50) NOT NULL ,createdDate Date default CURRENT_DATE);
CREATE TABLE users(username varchar(50) PRIMARY KEY NOT NULL, password varchar(256) NOT NULL, enabled boolean);
create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

CREATE TABLE event (
    id integer NOT NULL,
    titre character varying,
    type character varying,
    description character varying,
    region character varying,
    date_creation date,
    prix numeric,
    nmb_ticket integer
);
Insert into event(titre, type, description, region, date_creation, prix, nmb_ticket) values ('Match Football', 'Sport', 'ALLEZ LES BLEUS', 'Cannes', '2019-07-03', 25, 50)
Insert into event(titre, type, description, region, date_creation, prix, nmb_ticket) values ('Avengers', 'Cinema', 'ALLEZ LES BLEUS', 'Cannes', '2019-07-03', 25, 50)
CREATE TABLE users (
	username varchar(50) PRIMARY KEY NOT NULL,
 	password varchar(256) NOT NULL,
 	enabled boolean
	);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
	);

create unique index ix_auth_username on authorities (username,authority);

CREATE TABLE usersinfo (
	id SERIAL PRIMARY KEY NOT NULL,
	firstName varchar(50) NOT NULL,
	lastName varchar(50) NOT NULL,
	birthDate Date NOT NULL,
	userName varchar(50) NOT NULL,
	email varchar(50) NOT NULL ,
	createdDate Date default CURRENT_DATE,
	CONSTRAINT fk_email FOREIGN KEY(email) REFERENCES users(username)
	);

CREATE TABLE categories (
    	id integer PRIMARY KEY NOT NULL,
	category varchar(20) NOT NULL
	);

CREATE TABLE events (
	id SERIAL PRIMARY KEY NOT NULL,
    	title varchar(100) NOT NULL,
    	category integer,
    	description varchar(4000) NOT NULL,
    	region varchar(50),
    	creationDate Date default CURRENT_DATE,
	startDate Date NOT NULL,
	endDate Date NOT NULL,
    	price numeric NOT NULL,
    	nbOfTicket integer NOT NULL,
	CONSTRAINT fk_category FOREIGN KEY(category) REFERENCES categories(id)
	);

Insert into categories(id,category) values (1,'Cour et Atelier');
Insert into categories(id,category) values (2,'Festival');
Insert into categories(id,category) values (3,'Salon');
Insert into categories(id,category) values (4,'Action solidaire');
Insert into categories(id,category) values (5,'Concert');
Insert into categories(id,category) values (6,'Gastronomie');
Insert into categories(id,category) values (7,'Conférence et Forum');
Insert into categories(id,category) values (8,'Musée et Exposition');
Insert into categories(id,category) values (9,'Spectacle et Théâtre');
Insert into categories(id,category) values (10,'Autres');

Insert into events(title, category, description, region, creationDate, startDate,endDate , price, nbOfTicket ) values ('Match Football', 1, 'ALLEZ LES BLEUS', 'Cannes', '2019-07-03','2019-07-03','2019-07-03', 25, 50)
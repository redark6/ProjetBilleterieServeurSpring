CREATE TABLE usersinfo(id SERIAL PRIMARY KEY NOT NULL,firstName varchar(50) NOT NULL, lastName varchar(50) NOT NULL, birthDate Date NOT NULL, userName varchar(50) NOT NULL, email varchar(50) NOT NULL ,createdDate Date default CURRENT_DATE);
CREATE TABLE users(username varchar(50) PRIMARY KEY NOT NULL, password varchar(256) NOT NULL, enabled boolean);
create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
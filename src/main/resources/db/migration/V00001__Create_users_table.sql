CREATE TABLE users (id bigint generated always as identity, name varchar(30), role varchar(30));
INSERT into users(name, role) values ('admin', 'admin');
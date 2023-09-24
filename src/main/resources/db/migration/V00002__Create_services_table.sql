CREATE TABLE services (id bigint generated always as identity, name varchar(30));
INSERT into services (name) values ('Мойка'),('Уборка салона'), ('Уборка багажника'), ('Обработка воском'), ('Люкс')
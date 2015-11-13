alter table user modify column fullname varchar(64) character set utf8 collate utf8_general_ci;
ALTER TABLE user CONVERT TO CHARACTER SET utf8 collate utf8_general_ci;

alter table placesave modify column title varchar(64) character set utf8 collate utf8_general_ci;
ALTER TABLE placesave CONVERT TO CHARACTER SET utf8 collate utf8_general_ci;
alter table place modify column information mediumtext character set utf8 collate utf8_general_ci;
alter table placesave modify column information mediumtext character set utf8 collate utf8_general_ci;


alter table place modify column address mediumtext character set utf8 collate utf8_general_ci;
alter table placesave modify column address mediumtext character set utf8 collate utf8_general_ci;

var1 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL

alter table place convert to character set utf8mb4 collate utf8mb4_unicode_ci
alter table placesave convert to character set utf8mb4 collate utf8mb4_unicode_ci

java.sql.SQLException: Incorrect string value: '\xE1\xBB\x85n \xC4...'
alter table place modify information MEDIUMTEXT;
alter table placesave modify placeid bigint(20) default null;

alter table placebackup add created_by_user varchar(255) DEFAULT NULL;
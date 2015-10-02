#!/bin/sh
#
# Setup database for map project
# Create a new databse and a new user
# Initialize data from server
#

MYSQL_OPTS="mysql -u root"

mysql ${MYSQL_OPTS} <<EOF 
	select user, host from mysql.user;
	create database map;
	create user 'map'@'localhost' identified by 'map';
	grant all privileges on * . * to 'map'@'localhost';
EOF
source /Users/quocanh/backup/mysql/get_mysql_backup.sh 

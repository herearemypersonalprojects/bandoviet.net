#!/bin/bash

# modify the following to suit your environment
export DB_BACKUP="/home/quocanh/backup/mysql"
export DB_USER="map"
export DB_PASSWD="map"

# title and version
echo ""
echo "Backup and rotate all mysql databases"
echo "--------------------------"

rm -rf $DB_BACKUP/04
mv $DB_BACKUP/03 $DB_BACKUP/04
mv $DB_BACKUP/02 $DB_BACKUP/03
mv $DB_BACKUP/01 $DB_BACKUP/02
mkdir $DB_BACKUP/01

echo "* Creating backup..."
mysqldump --user=$DB_USER --password=$DB_PASSWD --all-databases | bzip2 > $DB_BACKUP/01/mysql-`date +%Y-%m-%d`.bz2

echo "----------------------"

echo "* Sending to ftp .."
FTP_HOST=dedibackup-dc3.online.net
FTP_USER=sd-83500
FTP_PASS=ngocanh81

# Call 1. Uses the ftp command with the -inv switches. 
#-i turns off interactive prompting. 
#-n Restrains FTP from attempting the auto-login feature. 
#-v enables verbose and progress. 

ftp -pinv $FTP_HOST << EOF

user $FTP_USER $FTP_PASS

cd backup/mysql/01

lcd $DB_BACKUP/01

mput *.bz2

bye
EOF

echo "Done"


#exit 0


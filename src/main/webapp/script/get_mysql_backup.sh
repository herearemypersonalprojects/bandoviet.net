#!/bin/bash

# login


sftp quocanh@bandoviet.net:backup/mysql/01/mysql-`date +%Y-%m-%d`.bz2
export PATH=$PATH:/usr/local/mysql/bin/
bunzip2 < mysql-`date +%Y-%m-%d`.bz2 | mysql -umap -pmap map 


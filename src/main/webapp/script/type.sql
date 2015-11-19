CREATE TABLE `type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  'code' varchar(255) unique,
  `nhom` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `security_level` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8; 



mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Bản đồ cá nhân', 'PERSONAL', curdate(), 'quocanh263@gmail.com', 1);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Sự kiện', 'PERSONAL', curdate(), 'quocanh263@gmail.com', 1); 
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Tin tức', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Nhà hàng', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Cơ quan hành chính', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Công ty, doanh nghiệp', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,01 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Hiệp hội, tổ chức liên quan đến VN', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Địa điểm thể thao, văn hóa cộng đồng', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,01 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Giáo dục, học tập', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Y tế, sức khỏe', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3); 
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Chợ, cửa hàng thực phẩm Việt', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Dịch vụ, mua bán cho người Việt', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Địa điểm du lịch, di tích lịch sử', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Tôn giáo, đền chùa, nhà thờ', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Thông tin việc làm, nhà cửa', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Người Việt tiêu biểu', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);
Query OK, 1 row affected (0,00 sec)

mysql> insert into type (name, nhom, created_date, created_by_user, security_level) values ('Thông tin hữu ích', 'PUBLIC', curdate(), 'quocanh263@gmail.com', 3);

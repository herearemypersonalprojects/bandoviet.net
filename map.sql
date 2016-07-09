-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: map
-- ------------------------------------------------------
-- Server version	5.6.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `body` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`body`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `continents`
--

DROP TABLE IF EXISTS `continents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `continents` (
  `code` varchar(2) NOT NULL DEFAULT '',
  `name` varchar(15) NOT NULL DEFAULT '',
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `code` varchar(2) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL DEFAULT '',
  `native` varchar(50) NOT NULL DEFAULT '',
  `phone` varchar(15) NOT NULL DEFAULT '',
  `continent` varchar(2) NOT NULL DEFAULT '',
  `capital` varchar(50) NOT NULL DEFAULT '',
  `currency` varchar(30) NOT NULL DEFAULT '',
  `languages` varchar(30) NOT NULL DEFAULT '',
  UNIQUE KEY `code` (`code`),
  KEY `continent` (`continent`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `message` mediumtext,
  `name` varchar(255) DEFAULT NULL,
  `send_from_ip` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `geom`
--

DROP TABLE IF EXISTS `geom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geom` (
  `g` geometry NOT NULL,
  SPATIAL KEY `g` (`g`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `linkedin`
--

DROP TABLE IF EXISTS `linkedin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linkedin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `foundtimes` float DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `request` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `send_from_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8046 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `from_user` bigint(20) DEFAULT NULL,
  `to_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nodes`
--

DROP TABLE IF EXISTS `nodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nodes` (
  `id` bigint(20) DEFAULT NULL,
  `geom` geometry NOT NULL,
  `user` varchar(50) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `timestamp` varchar(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `changeset` int(11) DEFAULT NULL,
  `tags` text,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  UNIQUE KEY `i_nodeids` (`id`),
  SPATIAL KEY `i_geomidx` (`geom`),
  FULLTEXT KEY `tags` (`tags`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `created_by_user` varchar(50) DEFAULT NULL,
  `created_by_user_fullname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`),
  FULLTEXT KEY `subtitle` (`subtitle`,`title`)
) ENGINE=MyISAM AUTO_INCREMENT=97864 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place2`
--

DROP TABLE IF EXISTS `place2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `geom` point NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`)
) ENGINE=MyISAM AUTO_INCREMENT=953 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place_individual`
--

DROP TABLE IF EXISTS `place_individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place_individual` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`)
) ENGINE=MyISAM AUTO_INCREMENT=88155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place_restaurant`
--

DROP TABLE IF EXISTS `place_restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place_restaurant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`)
) ENGINE=MyISAM AUTO_INCREMENT=88316 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place_vietnam`
--

DROP TABLE IF EXISTS `place_vietnam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place_vietnam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`)
) ENGINE=MyISAM AUTO_INCREMENT=88156 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `place_yelp`
--

DROP TABLE IF EXISTS `place_yelp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place_yelp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `created_by_user_fullname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`)
) ENGINE=MyISAM AUTO_INCREMENT=97853 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `placebackup`
--

DROP TABLE IF EXISTS `placebackup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `placebackup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `close_time` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`information`),
  FULLTEXT KEY `title_index` (`title`),
  FULLTEXT KEY `information_index` (`information`),
  FULLTEXT KEY `search_index` (`title`,`information`)
) ENGINE=MyISAM AUTO_INCREMENT=88224 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `placesave`
--

DROP TABLE IF EXISTS `placesave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `placesave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `community_code` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_by_user_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_from_ip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `idlookid_url` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `information` mediumtext CHARACTER SET utf8,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `organised_by` varchar(255) DEFAULT NULL,
  `placeid` bigint(20) NOT NULL,
  `place_type` varchar(255) NOT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `reference_url` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `subtitle` varchar(225) CHARACTER SET utf8 DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `title_without_accent` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `created_by_user_fullname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nhom` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by_user` varchar(255) DEFAULT NULL,
  `security_level` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_level` int(11) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age_range` int(11) DEFAULT NULL,
  `avatar_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `confident_level` int(11) DEFAULT NULL,
  `country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` bit(1) NOT NULL,
  `favorite_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_connected_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_newsletter` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `job` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `keywords` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `language` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_active_time` bigint(20) DEFAULT NULL,
  `last_connected_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `nationality` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `original_country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `thanks_given_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  UNIQUE KEY `UK_ctuyb917rxy2guxe4469qp3q4` (`role`,`email`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `fullname` varchar(80) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `id` varchar(45) NOT NULL,
  `access_level` int(11) DEFAULT NULL,
  `age_range` int(11) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `confident_level` int(11) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `favorite_address` varchar(255) DEFAULT NULL,
  `first_connected_ip` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `is_newsletter` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `last_active_time` bigint(20) DEFAULT NULL,
  `last_connected_ip` varchar(255) NOT NULL,
  `last_login_date` datetime NOT NULL,
  `latitude` double DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `original_country` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `thanks_given_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_user_role`
--

DROP TABLE IF EXISTS `users_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_user_role` (
  `users` varchar(45) NOT NULL,
  `user_role` int(11) NOT NULL,
  PRIMARY KEY (`users`,`user_role`),
  UNIQUE KEY `UK_8afxbcmg04eduhalpkcmqk5lu` (`user_role`),
  CONSTRAINT `FK_8afxbcmg04eduhalpkcmqk5lu` FOREIGN KEY (`user_role`) REFERENCES `user_roles` (`user_role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zipcode_spatial`
--

DROP TABLE IF EXISTS `zipcode_spatial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zipcode_spatial` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `zipcode` char(7) NOT NULL,
  `lon` int(11) DEFAULT NULL,
  `lat` int(11) DEFAULT NULL,
  `loc` point NOT NULL,
  PRIMARY KEY (`id`),
  KEY `zipcode` (`zipcode`),
  SPATIAL KEY `loc` (`loc`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-09  1:47:36

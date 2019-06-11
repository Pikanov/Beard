 SET NAMES utf8 ;

DROP TABLE IF EXISTS `comments`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comments_ibfk_1` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


LOCK TABLES `comments` WRITE;
INSERT INTO `comments` VALUES (6,'my first comment',1),(7,'my first comment',1),(19,'test3',1),(20,'test4',1),(29,'opera',1),(35,'hello test',1),(36,'ksljkasdjlkascnl',1),(37,'klsaklsalcknaskc',1),(38,'hello from opera',1),(39,' xdvxdvxdvxdvxdv',1),(41,'&#1086;&#1088;&#1087;&#1086;&#1088;&#1088;&#1083;&#1088;&#1086;&#1087;&#1086;',1),(42,'hello 09/06/2019',1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `price_offers`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `price_offers` (
  `price_offers_id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` bigint(20) NOT NULL,
  PRIMARY KEY (`price_offers_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `price_offers` WRITE;
INSERT INTO `price_offers` VALUES (1,'Haircut',500),(2,'Royal shave',250),(3,'Beard and mustache trim',350);


DROP TABLE IF EXISTS `roles`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'user'),(2,'admin'),(3,'master');
UNLOCK TABLES;

DROP TABLE IF EXISTS `schedule_items`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `schedule_items` (
  `schedule_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `master_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `free_busy` tinyint(4) DEFAULT NULL,
  `schedule_id` bigint(20) DEFAULT NULL,
  `price_offers_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`schedule_item_id`),
  KEY `user_id_fk_idx` (`master_id`),
  KEY `schedule_id_fk_idx` (`schedule_id`),
  KEY `price_offers_id_fk` (`price_offers_id`),
  KEY `customer_id_fk` (`customer_id`),
  CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `master_id_fk` FOREIGN KEY (`master_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `price_offers_id_fk` FOREIGN KEY (`price_offers_id`) REFERENCES `price_offers` (`price_offers_id`),
  CONSTRAINT `schedule_id_fk` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;


LOCK TABLES `schedule_items` WRITE;
/*!40000 ALTER TABLE `schedule_items` DISABLE KEYS */;
INSERT INTO `schedule_items` VALUES (51,'2019-06-05','10:00:00',45,NULL,1,1,NULL),(52,'2019-06-04','11:00:00',48,NULL,1,1,NULL),(53,'2019-06-04','12:00:00',49,NULL,1,1,NULL),(54,'2019-06-04','13:00:00',45,NULL,1,1,NULL),(55,'2019-06-04','14:00:00',48,NULL,1,1,NULL),(56,'2019-06-04','15:00:00',49,NULL,1,1,NULL),(57,'2019-06-04','16:00:00',45,NULL,1,1,NULL),(58,'2019-06-03','17:00:00',48,NULL,1,1,NULL),(59,'2019-06-03','18:00:00',49,NULL,1,1,NULL),(60,'2019-06-02','19:00:00',45,NULL,1,1,NULL),(61,'2019-05-31','20:00:00',48,1,0,1,1);
/*!40000 ALTER TABLE `schedule_items` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `schedules`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `schedules` (
  `schedule_id` bigint(20) NOT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `users`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(150) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL,
  `phone` varchar(12) CHARACTER SET utf8 NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `users_fk_1` (`role_id`),
  CONSTRAINT `users_fk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'admin','admin','admin@gmail.com','70844e130198c110a24768ebff21a735','0632773351',2),(10,'user','user','oleksii.skachkov@gmail.com','Qwerty11@','0632773352',1),(36,'master','master','master@gmail.com','Qwerty11@','0632559988',3),(45,'John','Washington','john@gmail.com','Qwerty11@','0632556688',3),(48,'Arthur','Morgan','morgan@gmail.com','Qwerty11@','0936772364',3),(49,'Guile','Adnderson','anderson@gmail.com','Qwerty11@','0665587499',3),(53,'Volodymyr','Pikanov','Pikanov_v@ukr.net','Qwerty11@','0632773351',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
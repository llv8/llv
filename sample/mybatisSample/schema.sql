create schema mybatis default character set utf8 collate utf8_general_ci;

CREATE TABLE `user1` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1,'lewis');
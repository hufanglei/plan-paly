/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-02 08:51:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `day` varchar(50) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `effect_time` datetime DEFAULT NULL,
  `month` varchar(50) DEFAULT NULL,
  `summarize` varchar(255) DEFAULT NULL,
  `plan_type` varchar(20) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `work_rate` varchar(20) DEFAULT NULL,
  `year` varchar(50) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK348B29B982FED7` (`project_id`),
  KEY `FK348B294F732B3D` (`user_id`),
  CONSTRAINT `FK348B294F732B3D` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK348B29B982FED7` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES ('1', '发的说说地方水电费', '2017-01-20 15:19:46', '20170120', '0', '2017-01-06 00:00:00', '201701', '发的撒地方地方是', 'DAYPLAN', '2017-01-20 15:21:26', 'TEN', '2017', null, '1');
INSERT INTO `plan` VALUES ('2', '<p>\r\n	1.熟悉小程序的开发流程\r\n</p>\r\n<p>\r\n	2.备份项目\r\n</p>', '2017-02-05 13:45:14', '20170205', '0', '2017-02-05 00:00:00', '201702', '完成', 'DAYPLAN', '2017-02-10 10:33:22', 'TEN', '2017', '1', '1');
INSERT INTO `plan` VALUES ('3', '修改bug', '2017-02-10 09:18:40', '20170210', '0', '2017-02-10 00:00:00', '201702', '<p>\r\n	1.后台bug1完成\r\n</p>\r\n<p>\r\n	2.&nbsp;会员不需要删除，但是要冻结功能。完成\r\n</p>', 'DAYPLAN', '2017-02-10 10:22:09', 'TEN', '2017', '1', '1');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '0', '电商');
INSERT INTO `project` VALUES ('2', '0', '其他');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `user_type` varchar(20) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, null, '0', '胡方雷', '654321', null, null, null);
INSERT INTO `user` VALUES ('2', null, null, '0', '李帅', '654321', null, null, null);

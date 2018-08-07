/*
Navicat MySQL Data Transfer

Source Server         : 192.168.163.128_3306
Source Server Version : 50540
Source Host           : 192.168.163.128:3306
Source Database       : primer

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-08-07 22:29:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for man
-- ----------------------------
DROP TABLE IF EXISTS `man`;
CREATE TABLE `man` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '1',
  `create_ts` datetime DEFAULT NULL,
  `update_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of man
-- ----------------------------
INSERT INTO `man` VALUES ('1', 'name1', '1', '2018-07-27 03:07:58', '2018-07-27 03:08:10');

-- ----------------------------
-- Table structure for man_woman_bind
-- ----------------------------
DROP TABLE IF EXISTS `man_woman_bind`;
CREATE TABLE `man_woman_bind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `man_id` int(11) DEFAULT NULL,
  `woman_id` int(11) DEFAULT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '1',
  `create_ts` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_man_woman` (`man_id`,`woman_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of man_woman_bind
-- ----------------------------
INSERT INTO `man_woman_bind` VALUES ('5', '1', '2', '1', '2018-07-27 06:39:01', '2018-07-27 06:39:01');
INSERT INTO `man_woman_bind` VALUES ('8', '1', '3', '1', '2018-07-27 06:41:21', '2018-07-27 06:41:21');
INSERT INTO `man_woman_bind` VALUES ('9', '1', '5', '1', '2018-07-27 06:54:05', '2018-07-27 06:54:05');

-- ----------------------------
-- Table structure for woman
-- ----------------------------
DROP TABLE IF EXISTS `woman`;
CREATE TABLE `woman` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '1',
  `create_ts` datetime DEFAULT NULL,
  `update_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of woman
-- ----------------------------
INSERT INTO `woman` VALUES ('0', 'woman3', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:13');
INSERT INTO `woman` VALUES ('1', 'woman1', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:15');
INSERT INTO `woman` VALUES ('2', 'woman2', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:16');
INSERT INTO `woman` VALUES ('4', 'woman4', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:18');
INSERT INTO `woman` VALUES ('5', 'woman5', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:19');
INSERT INTO `woman` VALUES ('6', 'woman6', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:20');
INSERT INTO `woman` VALUES ('7', 'woman7', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:22');
INSERT INTO `woman` VALUES ('8', 'woman8', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:26');
INSERT INTO `woman` VALUES ('9', 'woman9', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:27');
INSERT INTO `woman` VALUES ('10', 'woman10', '1', '2018-08-06 21:22:08', '2018-07-27 03:10:31');

/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-12 15:53:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_navs`
-- ----------------------------
DROP TABLE IF EXISTS `tb_navs`;
CREATE TABLE `tb_navs` (
  `id` int(5) NOT NULL,
  `title` varchar(10) NOT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `herf` varchar(50) DEFAULT NULL,
  `spread` int(1) DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `paterId` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_navs
-- ----------------------------

/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-12 15:53:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_paper`
-- ----------------------------
DROP TABLE IF EXISTS `tb_paper`;
CREATE TABLE `tb_paper` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(50) NOT NULL,
  `status` int(1) DEFAULT '0' COMMENT '0:审核通过 1：未审核',
  `date` varchar(12) NOT NULL,
  `author` varchar(6) NOT NULL,
  `presentersId` int(6) DEFAULT NULL,
  `text` longtext,
  `power` int(1) DEFAULT '0',
  `show` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_paper
-- ----------------------------
INSERT INTO `tb_paper` VALUES ('5', 'test', '1', '2018-01-04', 'max', null, '123141414', '1', '1');
INSERT INTO `tb_paper` VALUES ('6', '测试文章', '1', '2018-01-04', 'as', null, '测试文章', '0', '1');
INSERT INTO `tb_paper` VALUES ('7', '测试文章1', '0', '2018-01-05', 'amx', null, '', '0', '1');
INSERT INTO `tb_paper` VALUES ('8', '测试', '0', '2018-01-03', '爱马仕', null, null, '0', '1');
INSERT INTO `tb_paper` VALUES ('9', '测试文章2', '0', '2018-01-04', 'as', null, '', '0', '1');
INSERT INTO `tb_paper` VALUES ('10', '测试文章3', '0', '2018-01-04', 'TK', null, '测试文章3', '0', '1');
INSERT INTO `tb_paper` VALUES ('11', '测试', '0', '2018-01-04', 'a', null, '', '0', '1');
INSERT INTO `tb_paper` VALUES ('12', '123', '0', '2018-01-04', 'q', null, '', '0', '1');
INSERT INTO `tb_paper` VALUES ('13', '测试9', '0', '2018-01-04', 'test', null, '测试9', '0', '1');
INSERT INTO `tb_paper` VALUES ('14', 'ceshi', '0', '2018-01-25', 'max', null, '测试', '0', '1');

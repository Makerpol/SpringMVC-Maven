/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-12 15:53:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `alias` varchar(10) DEFAULT NULL,
  `sex` int(11) DEFAULT '0' COMMENT '0：女   1：男  2：保密',
  `phone` bigint(13) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0：正常使用  1：冻结',
  `grade` int(1) NOT NULL DEFAULT '0' COMMENT '0 : 超级管理员 1：编辑人员 2：问题维修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '123456', 'admin', '0', '19216812233', '1233@163.com', '2017-12-21', '0', '0');
INSERT INTO `tb_user` VALUES ('2', '李琳', '123', 'luna', '0', '12312311131', '123@163.com', '2017-12-26', '0', '0');
INSERT INTO `tb_user` VALUES ('4', 'user', '123 345', null, null, '11111111111', '123@163.com', '2018-01-04', '0', '2');
INSERT INTO `tb_user` VALUES ('5', 'test', '123456', null, null, '11111111111', 'ww@qq.com', '2018-01-04', '0', '1');

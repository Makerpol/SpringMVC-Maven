/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-09 17:57:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `realname` varchar(20) DEFAULT '' COMMENT '真实姓名',
  `sex` int(1) DEFAULT '0' COMMENT '0：女   1：男  2：保密',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0：正常使用  1：禁用状态  2：删除状态',
  `grade` int(1) NOT NULL DEFAULT '0' COMMENT '0 : 管理员 1：责任主编  2：编辑人员 3：普通人员',
  `image` varchar(255) NOT NULL COMMENT '头像路径',
  `IDCard` varchar(18) DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin', '0', '19216812233', 'ceshi@163.com', '2017-12-21', '0', '0', '/upload/admin/96a1540f27e438bf32822d7f458e8f19.jpg', null);
INSERT INTO `tb_user` VALUES ('2', '李琳', 'E10ADC3949BA59ABBE56E057F20F883E', 'luna', '0', '12312311131', '123@163.com', '2017-12-26', '0', '0', '/images/luna.jpg', null);
INSERT INTO `tb_user` VALUES ('5', 'test', 'E10ADC3949BA59ABBE56E057F20F883E', 'test1', null, '11111111111', 'ww@qq.com', '2018-01-04', '0', '1', '/images/luna.jpg', null);
INSERT INTO `tb_user` VALUES ('6', 'user', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, '18922221111', '189@qq.com', null, '0', '0', '/images/luna.jpg', null);

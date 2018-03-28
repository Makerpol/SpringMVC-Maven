/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-28 17:48:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_copies`
-- ----------------------------
DROP TABLE IF EXISTS `tb_copies`;
CREATE TABLE `tb_copies` (
  `id` int(30) NOT NULL,
  `papername` varchar(255) NOT NULL DEFAULT 'null' COMMENT '投稿文章名字',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '投稿期刊类型 0 科技创新  1 河南科技  2 乡村科技  3 研究报告  4 创新方法',
  `author` varchar(50) NOT NULL COMMENT '作者',
  `phone` varchar(20) DEFAULT NULL,
  `adds` varchar(255) DEFAULT NULL COMMENT '常用地址',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `filepath` varchar(255) NOT NULL COMMENT '上传文件保存地址',
  `email` varchar(255) NOT NULL COMMENT '联系邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '作者备注',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '文章审核状态 0 未审核   1 已初审  2 已复审  3 已终审',
  `date` varchar(20) DEFAULT NULL COMMENT '投稿时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_copies
-- ----------------------------

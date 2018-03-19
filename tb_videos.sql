/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-24 18:09:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_videos`
-- ----------------------------
DROP TABLE IF EXISTS `tb_videos`;
CREATE TABLE `tb_videos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `videoname` varchar(100) NOT NULL COMMENT '视频名字',
  `path` varchar(255) NOT NULL COMMENT '视频路径',
  `paperid` int(10) NOT NULL COMMENT '文章id',
  `date` varchar(20) NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_videos
-- ----------------------------

/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50549
Source Host           : 47.94.165.222:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-08-21 15:33:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL COMMENT '地址',
  `address` varchar(256) DEFAULT NULL COMMENT '文档的地址',
  `haxi` varchar(256) DEFAULT NULL COMMENT '地址',
  `create_time` varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_file_images
-- ----------------------------
DROP TABLE IF EXISTS `t_file_images`;
CREATE TABLE `t_file_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) NOT NULL COMMENT '转换后id',
  `name` varchar(256) DEFAULT NULL COMMENT '文档的地址',
  `address` varchar(256) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8;

/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 127.0.0.1:3306
 Source Schema         : looking

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 16/04/2020 19:29:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品id\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称\n',
  `num` int(10) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='秒杀商品表\n';

-- ----------------------------
-- Records of books
-- ----------------------------
BEGIN;
INSERT INTO `books` VALUES (1, 'iPhone SE 2', 0);
INSERT INTO `books` VALUES (2, 'iPad Pro 10.5', 5);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

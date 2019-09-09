/*
 Navicat Premium Data Transfer

 Source Server         : shiwensama
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : ssm_employees

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 09/09/2019 14:51:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_dept
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept`;
CREATE TABLE `tbl_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_dept
-- ----------------------------
INSERT INTO `tbl_dept` VALUES (1, '开发部');
INSERT INTO `tbl_dept` VALUES (2, '运维部');
INSERT INTO `tbl_dept` VALUES (3, '吹水部');
INSERT INTO `tbl_dept` VALUES (4, '产品部');

-- ----------------------------
-- Table structure for tbl_emp
-- ----------------------------
DROP TABLE IF EXISTS `tbl_emp`;
CREATE TABLE `tbl_emp`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `d_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `fk_emp_dept`(`d_id`) USING BTREE,
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `tbl_dept` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_emp
-- ----------------------------
INSERT INTO `tbl_emp` VALUES (2, 'ff8d10', 'M', 'ff8d10@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (3, 'd49501', 'M', 'd49501@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (4, '57b442', 'M', '57b442@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (5, '9a7c03', 'M', '9a7c03@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (6, '22c4b4', 'M', '22c4b4@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (7, '8c8e55', 'M', '8c8e55@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (8, 'ab98b6', 'M', 'ab98b6@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (9, 'cecf67', 'M', 'cecf67@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (10, '338358', 'M', '338358@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (11, '2c6299', 'M', '2c6299@sup.com', 1);
INSERT INTO `tbl_emp` VALUES (12, '雷雷', 'M', '965805978@qq.com', 1);
INSERT INTO `tbl_emp` VALUES (13, '文文', 'M', 'wenwen@github.com', 3);

SET FOREIGN_KEY_CHECKS = 1;

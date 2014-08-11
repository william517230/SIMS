/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sims

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2014-08-11 20:28:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `基本信息表`
-- ----------------------------
DROP TABLE IF EXISTS `基本信息表`;
CREATE TABLE `基本信息表` (
  `学号` varchar(255) NOT NULL,
  `姓名` varchar(255) DEFAULT NULL,
  `性别` varchar(255) DEFAULT NULL,
  `生日` varchar(255) DEFAULT NULL,
  `身份证号` varchar(255) DEFAULT NULL,
  `年级` varchar(255) DEFAULT NULL,
  `学院` varchar(255) DEFAULT NULL,
  `专业` varchar(255) DEFAULT NULL,
  `班号` varchar(255) DEFAULT NULL,
  `头像路径` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`学号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 基本信息表
-- ----------------------------
INSERT INTO `基本信息表` VALUES ('3075005678', '比尔盖茨', '男', '1955-10-28', '441234195510281234', '1972', '计算机学院', '计算机科学与技术', '6', 'D:\\BaiduNetDisk\\src\\JAVA\\MyEclipseWorkSpace\\SIMS\\bin\\upload\\image\\比尔盖茨.jpg');
INSERT INTO `基本信息表` VALUES ('3081008899', '刘德华', '男', '1961-09-27', '441588196109276421', '1981', '艺术设计学院', '服装设计与表演', '3', 'D:\\BaiduNetDisk\\src\\JAVA\\MyEclipseWorkSpace\\SIMS\\bin\\upload\\image\\刘德华.jpg');
INSERT INTO `基本信息表` VALUES ('3082001234', '唐骏', '男', '1962-06-27', '441233196206275455', '2008', '管理学院', '工商管理', '3', 'D:\\BaiduNetDisk\\src\\JAVA\\MyEclipseWorkSpace\\SIMS\\bin\\upload\\image\\唐骏.jpg');
INSERT INTO `基本信息表` VALUES ('3089004321', '王菲', '女', '1969-08-08', '441234196908082134', '1989', '艺术设计学院', '服装设计与表演', '7', 'D:\\BaiduNetDisk\\src\\JAVA\\MyEclipseWorkSpace\\SIMS\\bin\\upload\\image\\王菲2.jpg');
INSERT INTO `基本信息表` VALUES ('3105123456', '王菲', '女', '1985-07-08', '441234198507082134', '2005', '外国语学院', '英语', '1', 'D:\\BaiduNetDisk\\src\\JAVA\\MyEclipseWorkSpace\\SIMS\\bin\\upload\\image\\王菲1.jpg');
INSERT INTO `基本信息表` VALUES ('3107006767', '唐骏', '男', '1987-03-15', '441233196206275455', '2007', '计算机学院', '软件工程', '3', 'D:\\BaiduNetDisk\\src\\JAVA\\MyEclipseWorkSpace\\SIMS\\bin\\upload\\image\\唐骏2.jpg');

-- ----------------------------
-- Table structure for `课程表`
-- ----------------------------
DROP TABLE IF EXISTS `课程表`;
CREATE TABLE `课程表` (
  `课程编号` varchar(255) NOT NULL,
  `课程名称` varchar(255) NOT NULL,
  `学分` varchar(255) DEFAULT NULL,
  `学时` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`课程编号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 课程表
-- ----------------------------
INSERT INTO `课程表` VALUES ('001', '数据库原理', '3', '48');
INSERT INTO `课程表` VALUES ('002', '数据结构', '3', '48');
INSERT INTO `课程表` VALUES ('003', '软件工程', '2.5', '32');
INSERT INTO `课程表` VALUES ('004', '汇编语言', '2.5', '32');
INSERT INTO `课程表` VALUES ('005', '算法设计', '2', '28');

-- ----------------------------
-- Table structure for `选课信息表`
-- ----------------------------
DROP TABLE IF EXISTS `选课信息表`;
CREATE TABLE `选课信息表` (
  `学号` varchar(255) NOT NULL,
  `课程编号` varchar(255) NOT NULL,
  `成绩` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`学号`,`课程编号`),
  KEY `课程编号` (`课程编号`),
  CONSTRAINT `课程编号` FOREIGN KEY (`课程编号`) REFERENCES `课程表` (`课程编号`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `学号` FOREIGN KEY (`学号`) REFERENCES `基本信息表` (`学号`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 选课信息表
-- ----------------------------
INSERT INTO `选课信息表` VALUES ('3089004321', '004', '88');

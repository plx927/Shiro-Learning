/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : shiro_example2

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-09-24 17:52:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '系统管理', 'admin:*', '/');
INSERT INTO `t_resource` VALUES ('2', '用户管理', 'admin:user:list', '/admin/user/list');
INSERT INTO `t_resource` VALUES ('3', '用户添加', 'admin:user:add', '/admin/user/add');
INSERT INTO `t_resource` VALUES ('4', '用户删除', 'admin:user:delete', '/admin/user/delete');
INSERT INTO `t_resource` VALUES ('5', '角色管理', 'admin:role:list', '/admin/role/list');
INSERT INTO `t_resource` VALUES ('6', '角色添加', 'admin:role:add', '/admin/role/add');
INSERT INTO `t_resource` VALUES ('7', '角色修改', 'admin:role:update', '/admin/role/update/*');
INSERT INTO `t_resource` VALUES ('8', '资源管理', 'admin:res:list', '/admin/res/list');
INSERT INTO `t_resource` VALUES ('10', '用户更新', 'admin:user:update', '/admin/user/update/*');
INSERT INTO `t_resource` VALUES ('11', '角色删除', 'admin:role:delete', '/admin/role/delete/*');
INSERT INTO `t_resource` VALUES ('12', '用户状态修改', 'admin:user:updateStatus', '/admin/user/updateStatus/*');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '组A', '组A');
INSERT INTO `t_role` VALUES ('2', '组B', '组B');
INSERT INTO `t_role` VALUES ('3', '组C', '组C');
INSERT INTO `t_role` VALUES ('5', '测试角色', '测试角色');

-- ----------------------------
-- Table structure for `t_role_res`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_res`;
CREATE TABLE `t_role_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_res
-- ----------------------------
INSERT INTO `t_role_res` VALUES ('1', '1', '1');
INSERT INTO `t_role_res` VALUES ('2', '2', '2');
INSERT INTO `t_role_res` VALUES ('3', '3', '2');
INSERT INTO `t_role_res` VALUES ('4', '4', '2');
INSERT INTO `t_role_res` VALUES ('5', '5', '3');
INSERT INTO `t_role_res` VALUES ('6', '6', '3');
INSERT INTO `t_role_res` VALUES ('7', '7', '3');
INSERT INTO `t_role_res` VALUES ('11', '10', '1');
INSERT INTO `t_role_res` VALUES ('12', '10', '2');
INSERT INTO `t_role_res` VALUES ('13', '10', '3');
INSERT INTO `t_role_res` VALUES ('14', '12', '1');
INSERT INTO `t_role_res` VALUES ('18', '8', '1');
INSERT INTO `t_role_res` VALUES ('30', '4', '5');
INSERT INTO `t_role_res` VALUES ('43', '11', '1');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '张三', 'cdc6d1963928d5c349a4eebf11c8b4a5', '0', 'zs');
INSERT INTO `t_user` VALUES ('2', '管理员', '0192023a7bbd73250516f069df18b500', '0', 'admin');
INSERT INTO `t_user` VALUES ('3', '李四', 'c3cb6d12c40908943b64bc0681af47db', '0', 'lisi');
INSERT INTO `t_user` VALUES ('4', 'ww', 'a87c9f7493e17b6aaa1b0ff155ce5765', '0', 'ww');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '2', '1');
INSERT INTO `t_user_role` VALUES ('3', '2', '3');
INSERT INTO `t_user_role` VALUES ('4', '3', '3');
INSERT INTO `t_user_role` VALUES ('5', '1', '4');
INSERT INTO `t_user_role` VALUES ('6', '2', '4');
INSERT INTO `t_user_role` VALUES ('7', '3', '4');
INSERT INTO `t_user_role` VALUES ('8', '1', '2');
INSERT INTO `t_user_role` VALUES ('9', '2', '2');
INSERT INTO `t_user_role` VALUES ('10', '3', '2');
INSERT INTO `t_user_role` VALUES ('11', '5', '2');

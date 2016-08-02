/*
Navicat MySQL Data Transfer

Source Server         : localhost5.6
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : mabao

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-08-02 18:27:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_type`;
CREATE TABLE `t_goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '一级分类Id',
  `type_name` varchar(45) NOT NULL COMMENT '一级分类名称',
  `type_list` varchar(1000) DEFAULT NULL COMMENT '二级分类列表',
  `units` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品类别表';

-- ----------------------------
-- Records of t_goods_type
-- ----------------------------
INSERT INTO `t_goods_type` VALUES ('1', '婴儿车', null, '辆', '车');
INSERT INTO `t_goods_type` VALUES ('2', '玩具', null, '个', '三岁小孩');
INSERT INTO `t_goods_type` VALUES ('3', '服饰鞋帽', null, '套', '韩流服装');
INSERT INTO `t_goods_type` VALUES ('4', '安全座椅', null, '个', '车用安全座椅');
INSERT INTO `t_goods_type` VALUES ('5', '图片绘本', null, '套', '学习成长');
INSERT INTO `t_goods_type` VALUES ('6', '日常用品', null, '个', '婴儿沐浴露');
INSERT INTO `t_goods_type` VALUES ('7', '婴儿食品', null, '个', '三鹿奶粉');
INSERT INTO `t_goods_type` VALUES ('8', '洗浴用品', null, '个', '宝宝专用');
INSERT INTO `t_goods_type` VALUES ('9', '生活用品', null, '个', '家庭套装');

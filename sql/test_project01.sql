/*
 Navicat Premium Data Transfer

 Source Server         : dz10b
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : test_project01

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 24/08/2023 21:33:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account`  (
  `accountId` int(0) NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `accountPwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收银账户密码',
  `money` double(8, 2) NULL DEFAULT NULL COMMENT '收银总金额',
  PRIMARY KEY (`accountId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收银账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES (1, 'qwer888888', 9633.18);

-- ----------------------------
-- Table structure for tb_activity
-- ----------------------------
DROP TABLE IF EXISTS `tb_activity`;
CREATE TABLE `tb_activity`  (
  `activityId` int(0) NOT NULL AUTO_INCREMENT COMMENT '活动标识id',
  `activityName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `criticalTotal` double(6, 2) NULL DEFAULT NULL COMMENT '折扣临界金额',
  `startDate` date NULL DEFAULT NULL COMMENT '活动开始日期',
  `endDate` date NULL DEFAULT NULL COMMENT '结束日期',
  `discount` double(4, 2) NULL DEFAULT NULL COMMENT '活动折扣',
  PRIMARY KEY (`activityId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 500019 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_activity
-- ----------------------------
INSERT INTO `tb_activity` VALUES (500001, '!!!夏季促销全场满200九折优惠!!!', 200.00, '2023-08-17', '2023-08-22', 0.90);
INSERT INTO `tb_activity` VALUES (500002, '!!!夏季促销全场满250八五折优惠!!!', 250.00, '2023-08-17', '2023-08-22', 0.85);
INSERT INTO `tb_activity` VALUES (500003, '!!!夏季促销全场满300八折优惠!!!', 300.00, '2023-08-16', '2023-08-22', 0.80);
INSERT INTO `tb_activity` VALUES (500004, '!!!夏季促销全场满350七折优惠!!!', 350.00, '2023-08-18', '2023-08-22', 0.75);
INSERT INTO `tb_activity` VALUES (500005, '!!!夏季促销全场满399七折优惠!!!', 399.00, '2023-08-19', '2023-08-22', 0.70);
INSERT INTO `tb_activity` VALUES (500010, '!!!夏季促销全场满500六五折优惠!!!', 500.00, '2023-08-20', '2023-08-23', 0.65);
INSERT INTO `tb_activity` VALUES (500011, '!!!夏季促销全场满500六五折优惠!!!', 500.00, '2023-08-20', '2023-08-23', 0.65);
INSERT INTO `tb_activity` VALUES (500012, '!!!夏季促销全场满500六五折优惠!!!', 500.00, '2023-08-20', '2023-08-23', 0.65);
INSERT INTO `tb_activity` VALUES (500013, '!!!夏季促销全场满500六五折优惠!!!', 500.00, '2023-08-20', '2023-08-23', 0.65);
INSERT INTO `tb_activity` VALUES (500017, '!!!夏季促销全场满500六五折优惠!!!', 500.00, '2023-08-20', '2023-08-23', 0.65);
INSERT INTO `tb_activity` VALUES (500018, '!!!夏季促销全场满500六五折优惠!!!', 500.00, '2023-08-20', '2023-08-23', 0.65);

-- ----------------------------
-- Table structure for tb_empuser
-- ----------------------------
DROP TABLE IF EXISTS `tb_empuser`;
CREATE TABLE `tb_empuser`  (
  `empUserId` int(0) NOT NULL AUTO_INCREMENT COMMENT '员工用户id',
  `empUserName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工用户姓名',
  `empUserDuty` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工用户职位',
  `empUserPwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工用户登录密码',
  PRIMARY KEY (`empUserId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100009 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工用户表登录收银系统' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_empuser
-- ----------------------------
INSERT INTO `tb_empuser` VALUES (3, '小明', '普通员工', '12333333');
INSERT INTO `tb_empuser` VALUES (6666, '彻雨清寒', '总经理', '1');
INSERT INTO `tb_empuser` VALUES (8888, '岳新亮', '总经理', '123');
INSERT INTO `tb_empuser` VALUES (100001, '王五', '总经理', 'qwer123456');
INSERT INTO `tb_empuser` VALUES (100002, '张三', '普通员工', '12345678');
INSERT INTO `tb_empuser` VALUES (100003, '李四', '普通员工', '88888888');
INSERT INTO `tb_empuser` VALUES (100005, '岳新亮', 's', '123456789');
INSERT INTO `tb_empuser` VALUES (100006, 'sss', 'aa', '123456');
INSERT INTO `tb_empuser` VALUES (100007, 'as', 'aa', '15');
INSERT INTO `tb_empuser` VALUES (100008, '靠靠靠', '测试', '111111111111111111');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `orderId` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `orderTotal` double(6, 2) NULL DEFAULT NULL COMMENT '订单总价',
  `orderTrueTotal` double(6, 2) NULL DEFAULT NULL COMMENT '订单实际价格',
  `orderDateTime` datetime(0) NULL DEFAULT NULL COMMENT '订单生成日期',
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (12, 200.00, 150.00, '2023-08-18 23:10:38');
INSERT INTO `tb_order` VALUES (13, 24.00, 24.00, '2023-08-20 12:12:12');
INSERT INTO `tb_order` VALUES (14, 600.00, 400.00, '2026-09-05 16:52:41');
INSERT INTO `tb_order` VALUES (15, 298.80, 253.98, '2023-08-20 11:14:24');
INSERT INTO `tb_order` VALUES (21, 1479.90, 961.94, '2023-08-21 08:44:37');
INSERT INTO `tb_order` VALUES (35, 676.90, 439.99, '2023-08-21 17:07:53');
INSERT INTO `tb_order` VALUES (37, 0.00, 0.00, '2023-08-21 17:26:10');
INSERT INTO `tb_order` VALUES (38, 494.80, 346.36, '2023-08-21 20:17:41');
INSERT INTO `tb_order` VALUES (39, 692.00, 449.80, '2023-08-21 20:22:09');
INSERT INTO `tb_order` VALUES (40, 2986.00, 1940.90, '2023-08-21 20:25:20');
INSERT INTO `tb_order` VALUES (41, 102.40, 102.40, '2023-08-21 20:33:16');
INSERT INTO `tb_order` VALUES (42, 38.40, 38.40, '2023-08-21 20:35:23');
INSERT INTO `tb_order` VALUES (43, 48.00, 48.00, '2023-08-21 20:36:25');
INSERT INTO `tb_order` VALUES (44, 919.00, 597.35, '2023-08-21 20:37:18');
INSERT INTO `tb_order` VALUES (45, 4902.60, 3186.69, '2023-08-21 20:39:23');
INSERT INTO `tb_order` VALUES (46, 756.40, 491.66, '2023-08-22 08:36:19');
INSERT INTO `tb_order` VALUES (47, 358.80, 358.80, '2023-08-22 10:54:02');
INSERT INTO `tb_order` VALUES (48, 0.00, 0.00, '2023-08-22 14:20:50');
INSERT INTO `tb_order` VALUES (49, NULL, NULL, '2023-08-24 08:00:41');

-- ----------------------------
-- Table structure for tb_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem`  (
  `orderId` int(0) NOT NULL COMMENT '订单编号外键',
  `productId` int(0) NOT NULL COMMENT '商品编号外键',
  `productName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `productCount` int(0) NULL DEFAULT NULL COMMENT '商品购买数量',
  `productPrice` double(6, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `productTotal` double(6, 2) NULL DEFAULT NULL COMMENT '商品总价',
  INDEX `tb_orderitem_order_orderId_fk`(`orderId`) USING BTREE,
  INDEX `tb_orderitem_product_productId_fk`(`productId`) USING BTREE,
  CONSTRAINT `tb_orderitem_order_orderId_fk` FOREIGN KEY (`orderId`) REFERENCES `tb_order` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_orderitem_product_productId_fk` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_orderitem
-- ----------------------------
INSERT INTO `tb_orderitem` VALUES (12, 200004, '百威啤酒', 2, 24.90, 49.80);
INSERT INTO `tb_orderitem` VALUES (12, 200006, '可比克', 3, 4.00, 12.00);
INSERT INTO `tb_orderitem` VALUES (12, 200008, '卫龙辣条', 15, 4.00, 60.00);
INSERT INTO `tb_orderitem` VALUES (13, 200004, '百威啤酒', 0, 24.90, 0.00);
INSERT INTO `tb_orderitem` VALUES (13, 200006, '可比克', 6, 4.00, 24.00);
INSERT INTO `tb_orderitem` VALUES (13, 200008, '卫龙辣条', 0, 4.00, 0.00);
INSERT INTO `tb_orderitem` VALUES (13, 200009, '舒肤佳洗手液', 0, 19.90, 0.00);
INSERT INTO `tb_orderitem` VALUES (14, 200004, '百威啤酒', 11, 24.90, 273.90);
INSERT INTO `tb_orderitem` VALUES (14, 200006, '可比克', 6, 4.00, 24.00);
INSERT INTO `tb_orderitem` VALUES (15, 200004, '百威啤酒', 16, 24.90, 398.40);
INSERT INTO `tb_orderitem` VALUES (15, 200009, '舒肤佳洗手液', 12, 19.90, 238.80);
INSERT INTO `tb_orderitem` VALUES (15, 200006, '可比克', 12, 4.00, 48.00);
INSERT INTO `tb_orderitem` VALUES (15, 200004, '百威啤酒', 12, 24.90, 298.80);
INSERT INTO `tb_orderitem` VALUES (21, 200006, '可比克', 37, 4.00, 148.00);
INSERT INTO `tb_orderitem` VALUES (21, 200005, '达利园饼干', 12, 3.20, 38.40);
INSERT INTO `tb_orderitem` VALUES (21, 200009, '舒肤佳洗手液', 65, 19.90, 1293.50);
INSERT INTO `tb_orderitem` VALUES (35, 200005, '达利园饼干', 49, 3.20, 156.80);
INSERT INTO `tb_orderitem` VALUES (35, 200004, '百威啤酒', 9, 24.90, 224.10);
INSERT INTO `tb_orderitem` VALUES (35, 200003, '巧乐兹', 37, 8.00, 296.00);
INSERT INTO `tb_orderitem` VALUES (38, 200006, '可比克', 15, 4.00, 60.00);
INSERT INTO `tb_orderitem` VALUES (38, 200004, '百威啤酒', 12, 24.90, 298.80);
INSERT INTO `tb_orderitem` VALUES (38, 200003, '巧乐兹', 17, 8.00, 136.00);
INSERT INTO `tb_orderitem` VALUES (39, 200008, '卫龙辣条', 154, 4.00, 616.00);
INSERT INTO `tb_orderitem` VALUES (39, 200006, '可比克', 19, 4.00, 76.00);
INSERT INTO `tb_orderitem` VALUES (40, 200007, '心相印', 154, 19.00, 2926.00);
INSERT INTO `tb_orderitem` VALUES (40, 200006, '可比克', 15, 4.00, 60.00);
INSERT INTO `tb_orderitem` VALUES (41, 200008, '卫龙辣条', 16, 4.00, 64.00);
INSERT INTO `tb_orderitem` VALUES (41, 200005, '达利园饼干', 12, 3.20, 38.40);
INSERT INTO `tb_orderitem` VALUES (42, 200005, '达利园饼干', 12, 3.20, 38.40);
INSERT INTO `tb_orderitem` VALUES (43, 200005, '达利园饼干', 15, 3.20, 48.00);
INSERT INTO `tb_orderitem` VALUES (44, 200004, '百威啤酒', 15, 24.90, 373.50);
INSERT INTO `tb_orderitem` VALUES (44, 200009, '舒肤佳洗手液', 15, 19.90, 298.50);
INSERT INTO `tb_orderitem` VALUES (44, 200007, '心相印', 13, 19.00, 247.00);
INSERT INTO `tb_orderitem` VALUES (45, 200006, '可比克', 18, 4.00, 72.00);
INSERT INTO `tb_orderitem` VALUES (45, 200004, '百威啤酒', 194, 24.90, 4830.60);
INSERT INTO `tb_orderitem` VALUES (46, 200003, '巧乐兹', 45, 8.00, 360.00);
INSERT INTO `tb_orderitem` VALUES (46, 200001, '可口可乐', 26, 3.00, 78.00);
INSERT INTO `tb_orderitem` VALUES (46, 200008, '卫龙辣条', 0, 4.00, 0.00);
INSERT INTO `tb_orderitem` VALUES (46, 200009, '舒肤佳洗手液', 16, 19.90, 318.40);
INSERT INTO `tb_orderitem` VALUES (47, 200004, '百威啤酒', 12, 24.90, 298.80);
INSERT INTO `tb_orderitem` VALUES (47, 200006, '可比克', 15, 4.00, 60.00);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `productId` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `productName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `productPrice` double(6, 2) NULL DEFAULT NULL COMMENT '商品单价',
  PRIMARY KEY (`productId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200011 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (200001, '可口可乐', 3.00);
INSERT INTO `tb_product` VALUES (200002, '百事可乐', 3.00);
INSERT INTO `tb_product` VALUES (200003, '巧乐兹', 8.00);
INSERT INTO `tb_product` VALUES (200004, '百威啤酒', 24.90);
INSERT INTO `tb_product` VALUES (200005, '达利园饼干', 3.20);
INSERT INTO `tb_product` VALUES (200006, '可比克', 4.00);
INSERT INTO `tb_product` VALUES (200007, '心相印', 19.00);
INSERT INTO `tb_product` VALUES (200008, '卫龙辣条', 4.00);
INSERT INTO `tb_product` VALUES (200009, '舒肤佳洗手液', 19.90);
INSERT INTO `tb_product` VALUES (200010, '老干妈', 12.00);

SET FOREIGN_KEY_CHECKS = 1;

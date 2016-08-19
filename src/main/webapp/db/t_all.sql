-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mabao
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_address`
--

DROP TABLE IF EXISTS `t_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `recipients` varchar(25) NOT NULL COMMENT '收件人',
  `tel` varchar(25) NOT NULL COMMENT '手机号',
  `area_id` int(11) NOT NULL DEFAULT '100000' COMMENT '所在行政区域',
  `location` varchar(100) NOT NULL COMMENT '地址详情',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '地址状态，是否为默认收货地址，0为否、1为是。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='地址信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_address`
--

LOCK TABLES `t_address` WRITE;
/*!40000 ALTER TABLE `t_address` DISABLE KEYS */;
INSERT INTO `t_address` VALUES (1,1,'二汪','110',220581,'中北路汪汪汪路2号门',1),(5,1,'大汪','120',220581,'喵喵家',0),(7,8,'chenheng','13260592767',430102,'测试',1),(8,5,'heng','13260592767',120101,'sdsd',0),(9,5,'132','13260592767',410102,'或',1),(10,5,'Qqqq','18771064490',110101,'A',0),(12,5,'测试4','13153031531',120101,'测试4',0),(13,5,'陈恒','13260592767',310101,'测试5',0);
/*!40000 ALTER TABLE `t_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_baby`
--

DROP TABLE IF EXISTS `t_baby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_baby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(255) DEFAULT NULL COMMENT '宝宝姓名',
  `birthday` date NOT NULL COMMENT '出生日期',
  `gender` int(11) NOT NULL COMMENT '宝宝性别',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='宝宝信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_baby`
--

LOCK TABLES `t_baby` WRITE;
/*!40000 ALTER TABLE `t_baby` DISABLE KEYS */;
INSERT INTO `t_baby` VALUES (1,1,'baby','2016-07-09',1,'爬'),(2,2,'bb','2016-04-02',0,'222'),(37,5,'Aa','2016-07-28',0,NULL),(38,12,'dd','2016-06-30',0,NULL),(39,5,'Aa','2016-07-28',0,''),(40,5,'Aa','2016-07-28',0,''),(41,5,'Aa','2016-07-28',0,''),(42,5,'Aa','2016-07-28',0,''),(43,5,'Aa','2016-07-28',0,''),(44,5,'Aa','2016-07-28',0,''),(45,5,'Aa','2016-07-28',0,''),(46,5,'Aa','2016-07-28',0,''),(47,5,'Aa','2016-07-28',0,'');
/*!40000 ALTER TABLE `t_baby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_banner`
--

DROP TABLE IF EXISTS `t_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `alt` varchar(255) DEFAULT NULL COMMENT '提示',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `sort` varchar(255) NOT NULL COMMENT '排序',
  `status` bit(1) NOT NULL DEFAULT b'1' COMMENT '禁用or启用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='首页广告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_banner`
--

LOCK TABLES `t_banner` WRITE;
/*!40000 ALTER TABLE `t_banner` DISABLE KEYS */;
INSERT INTO `t_banner` VALUES (1,30,NULL,'30.jpg','1','',NULL),(2,31,NULL,'31.jpg','2','',NULL),(3,32,NULL,'32.jpg','3','',NULL),(4,33,NULL,'33.jpg','4','',NULL);
/*!40000 ALTER TABLE `t_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号，自增',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品归属者编号，后台用户编号为0',
  `article_number` varchar(45) DEFAULT NULL COMMENT '货号',
  `picture` varchar(45) DEFAULT NULL COMMENT '图片标签',
  `title` varchar(45) NOT NULL COMMENT '标题',
  `old_price` decimal(11,2) NOT NULL COMMENT '原价，用整型存储避免计算出错，存取时记得变位。',
  `price` decimal(11,2) NOT NULL COMMENT '现价，用法同原价。',
  `baby_type` int(11) NOT NULL COMMENT '适合宝宝类型',
  `type_name` varchar(25) DEFAULT NULL COMMENT '二级类型名称',
  `type_id` int(11) DEFAULT NULL COMMENT '一级类型编号',
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(45) NOT NULL COMMENT '商品品牌',
  `up_time` datetime NOT NULL COMMENT '上架时间',
  `new_degree` int(2) NOT NULL COMMENT '新旧程度，0表示全新，95，80分别表示95成8成新',
  `size` int(5) DEFAULT NULL COMMENT '尺寸',
  `pack` tinyint(1) DEFAULT NULL COMMENT '是否有包装，1有0无',
  `receipt` tinyint(1) DEFAULT NULL COMMENT '是否有小票，1有0无',
  `message` varchar(500) DEFAULT NULL COMMENT '卖家分享',
  `picture_list` varchar(200) DEFAULT NULL COMMENT '附加图片，list元素用分号隔开存入数据库',
  `stock_number` int(11) DEFAULT NULL COMMENT '库存数量',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '商品状态，1为存在，0为下架或不存在。',
  `sell_end` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
INSERT INTO `t_goods` VALUES (1,1,'AX9008','12.jpg','适合两岁宝宝的婴儿车',80.00,66.30,0,'婴儿车',1,1,'H&M','2016-06-02 11:31:31',0,1,1,1,'这是一款带有未来设计感的婴儿车',NULL,100,1,0),(2,1,NULL,'1.jpg','适合三岁宝宝的玩具',110.00,124.85,1,'玩具',2,1,'LV','2016-06-04 00:00:00',0,1,1,1,'结实，耐折腾',NULL,150,1,0),(3,1,NULL,'2.jpg','遥控车',200.00,300.00,0,'玩具',2,1,'T&R','2016-06-03 00:00:00',0,1,1,1,'玩具遥控车',NULL,20,1,0),(4,1,NULL,'main-new1.png','H&M',66.90,100.00,1,'服饰鞋帽',3,3,'M&D','2016-06-05 00:00:00',0,1,1,1,'简约，奢华',NULL,10,1,0),(5,1,NULL,'main-new2.png','CARDING',280.00,350.00,0,'服饰鞋帽',3,1,'T&R','2016-06-06 23:08:18',0,1,1,1,'结实，耐折腾',NULL,200,1,0),(6,1,NULL,'13.jpg','三岁',270.00,280.00,1,'安全座椅',1,1,'T&R','2016-06-20 23:08:21',0,1,1,1,'结实，耐折腾',NULL,25,1,0),(7,1,NULL,'13.jpg','沙发',130.00,150.00,0,'安全座椅',4,1,'T&R','2016-06-21 23:08:23',0,1,1,1,'结实，耐折腾',NULL,30,1,0),(8,1,NULL,'13.jpg','三岁',200.00,240.00,0,'安全座椅',4,1,'T&R','2016-06-20 23:08:26',0,1,1,1,'结实，耐折腾',NULL,33,1,0),(9,1,NULL,'14.jpg','三岁',420.00,460.00,1,'图片绘本',1,1,'T&R','2016-06-14 23:08:29',0,1,1,1,'结实，耐折腾',NULL,50,1,0),(10,1,NULL,'14.jpg','画板',250.00,280.00,2,'图片绘本',5,1,'T&R','2016-06-06 23:08:33',0,1,1,1,'结实，耐折腾',NULL,80,1,0),(12,1,NULL,'16.jpg','奶瓶',35.00,35.00,0,'生活用品',9,2,'BOBO','2016-06-30 16:03:47',0,1,1,1,'结实好用','',30,1,0),(13,1,NULL,'17.jpg','奶粉',280.00,280.00,2,'玩具',2,4,'Enfamil','2016-06-30 16:05:22',3,1,1,1,'安全有营养',NULL,35,1,0),(14,1,NULL,'18.jpg','抽纸',9.50,9.50,0,'日常用品',6,5,'花王','2016-06-30 16:06:30',3,1,1,1,'卫生',NULL,15,1,0),(15,1,NULL,'19.jpg','洗浴套',130.00,130.00,2,'玩具',2,6,'屈臣氏','2016-06-30 16:07:31',3,1,1,1,'家庭套',NULL,9,1,0),(16,1,NULL,'20.jpg','尿不湿',23.00,23.00,0,'日常用品',6,7,'帮宝适','2016-06-30 16:08:44',5,1,1,1,'方便好用',NULL,15,1,0),(17,1,NULL,'21.jpg','奶嘴',13.00,13.00,0,'日常用品',6,5,'花王','2016-06-30 16:09:33',4,1,1,1,'材质好',NULL,7,1,0),(18,1,NULL,'22.jpg','浴巾',49.00,49.00,0,'日常用品',6,8,'竹纤维','2016-06-30 16:10:42',4,1,1,1,'安全优质',NULL,35,1,0),(19,1,NULL,'23.jpg','背带裤',89.00,89.00,2,'玩具',2,9,'七波辉','2016-06-30 16:11:38',4,1,1,1,'好看舒适',NULL,79,1,0),(20,1,NULL,'24.jpg','玩具',13.00,13.00,2,'日常用品',6,10,'轨迹','2016-06-30 16:12:33',1,1,1,1,'耐久',NULL,13,1,0),(21,1,NULL,'25.jpg','洗手液',28.00,28.00,2,'洗浴用品',8,11,'好娃娃','2016-06-30 16:13:32',1,1,1,1,'经用且环保',NULL,17,1,0),(22,1,NULL,'26.jpg','被子',280.00,280.00,0,'玩具',2,12,'BinBe','2016-06-30 16:14:40',5,1,1,1,'质地好',NULL,200,1,0),(30,1,NULL,'30.jpg','童装',360.00,250.00,1,'服饰鞋帽',3,1,'T&R','2015-06-16 23:07:33',0,1,1,1,'简约',NULL,1,1,0),(31,1,NULL,'31.jpg','童装',250.00,200.00,1,'服饰鞋帽',3,1,'T&R','2015-06-15 23:07:33',0,1,1,1,'简约',NULL,1,1,0),(32,1,NULL,'32.jpg','童装',480.00,360.00,2,'服饰鞋帽',3,1,'T&R','2015-06-14 23:07:33',0,1,1,1,'简约',NULL,1,1,0),(33,1,NULL,'33.jpg','童装',360.00,270.00,1,'服饰鞋帽',3,1,'T&R','2015-06-13 23:07:33',0,1,1,1,'简约',NULL,1,1,0),(34,5,NULL,'25.jpg','测试交易中',100.00,80.00,0,'婴儿车',1,1,'T&R','2016-07-30 00:00:00',0,1,1,1,'很好','',0,0,1),(35,5,NULL,'24.jpg','测试上架',222.00,33.00,0,'洗浴用品',8,1,'T&R','2016-08-01 00:00:00',0,1,1,1,'测试','',1,1,0),(36,5,NULL,'22.jpg','测试下架',66.00,6.00,0,'婴儿车',1,1,'T&R','2016-08-02 00:00:00',0,1,1,1,'测试','',1,0,0),(37,5,NULL,'26.jpg','测试交易完成',233.00,333.00,0,'婴儿车',1,1,'T&R','2016-08-02 10:00:00',0,1,1,1,'测试','',0,0,1);
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods_brand`
--

DROP TABLE IF EXISTS `t_goods_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) NOT NULL COMMENT '品牌名称',
  `picture` varchar(255) DEFAULT NULL COMMENT '图标',
  `sort` int(11) NOT NULL COMMENT '排序',
  `status` bit(1) NOT NULL DEFAULT b'1' COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='品牌';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods_brand`
--

LOCK TABLES `t_goods_brand` WRITE;
/*!40000 ALTER TABLE `t_goods_brand` DISABLE KEYS */;
INSERT INTO `t_goods_brand` VALUES (1,'T&R',NULL,1,'',NULL),(2,'BOBO',NULL,2,'',NULL),(3,'M&D',NULL,3,'',NULL),(4,'Enfamil',NULL,4,'',NULL),(5,'花王',NULL,5,'',NULL),(6,'屈臣氏',NULL,6,'',NULL),(7,'帮宝适',NULL,9,'',NULL),(8,'竹纤维',NULL,7,'',NULL),(9,'七波辉',NULL,8,'',NULL),(10,'轨迹',NULL,22,'',NULL),(11,'好娃娃',NULL,12,'',NULL),(12,'BinBe',NULL,0,'',NULL);
/*!40000 ALTER TABLE `t_goods_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods_size_table`
--

DROP TABLE IF EXISTS `t_goods_size_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods_size_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_type_id` int(11) NOT NULL COMMENT '商品类别',
  `name` varchar(255) NOT NULL COMMENT '尺码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品尺码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods_size_table`
--

LOCK TABLES `t_goods_size_table` WRITE;
/*!40000 ALTER TABLE `t_goods_size_table` DISABLE KEYS */;
INSERT INTO `t_goods_size_table` VALUES (1,1,'0-3m'),(2,1,'3-6m'),(3,1,'6-9m'),(4,1,'9-12m'),(5,1,'12-18m'),(6,1,'18-24m'),(7,2,'2m'),(8,2,'1.5m'),(9,2,'0.5m'),(10,4,'1m'),(11,4,'30公分'),(12,4,'50公分'),(13,4,'70公分'),(14,3,'常规尺寸'),(15,5,'平装'),(16,5,'精装');
/*!40000 ALTER TABLE `t_goods_size_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `buyer_id` int(11) NOT NULL COMMENT '买家编号',
  `operator_id` int(11) NOT NULL COMMENT '卖家',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `total_sum` decimal(10,2) NOT NULL COMMENT '总金额',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `port_time` datetime DEFAULT NULL COMMENT '订单发货时间',
  `pay_time` datetime DEFAULT NULL COMMENT '订单支付时间',
  `deal_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `freight` decimal(10,2) NOT NULL COMMENT '运费',
  `express_id` int(11) DEFAULT NULL,
  `port_number` varchar(20) DEFAULT NULL COMMENT '运单号',
  `payment_no` varchar(30) DEFAULT NULL COMMENT '支付单号',
  `address_id` int(11) DEFAULT NULL COMMENT '地址默认地址编号',
  `message` varchar(100) DEFAULT NULL COMMENT '买家留言',
  `state` int(1) NOT NULL COMMENT '订单状态，0待支付（买家），1待发货（卖家），2待收货（买家），3已完成（卖家），4已取消（买家）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (15,5,1,1,90.00,'2016-08-02 11:14:32',NULL,NULL,NULL,10.00,NULL,NULL,NULL,9,'',0),(16,5,1,3,112.00,'2016-08-02 22:46:38',NULL,NULL,NULL,10.00,NULL,NULL,NULL,9,'',1),(17,5,1,4,408.00,'2016-08-02 22:48:01','2016-08-03 10:10:01','2016-08-02 23:48:01',NULL,10.00,87,'600217910221',NULL,9,'',2),(18,5,1,2,356.00,'2016-08-02 23:08:27',NULL,NULL,NULL,10.00,NULL,NULL,NULL,9,'',3);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_details`
--

DROP TABLE IF EXISTS `t_order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `unit_cost` decimal(10,2) NOT NULL COMMENT '单价',
  `quantity` int(11) NOT NULL,
  `size` varchar(255) DEFAULT NULL COMMENT '尺寸',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type_name` varchar(255) DEFAULT NULL COMMENT '一级类型名称',
  `brand` varchar(255) DEFAULT NULL COMMENT '商品品牌',
  `up_time` datetime DEFAULT NULL COMMENT '上架时间',
  `new_degree` varchar(255) DEFAULT NULL COMMENT '新旧程度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='订单详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_details`
--

LOCK TABLES `t_order_details` WRITE;
/*!40000 ALTER TABLE `t_order_details` DISABLE KEYS */;
INSERT INTO `t_order_details` VALUES (22,15,34,80.00,1,'0-3m','测试','婴儿车','T&R','2016-07-30 00:00:00','全新'),(23,16,19,89.00,1,'0-3m','背带裤','玩具','七波辉','2016-06-30 16:11:38','6成新'),(24,16,20,13.00,2,'0-3m','玩具','日常用品','轨迹','2016-06-30 16:12:33','9成新'),(25,17,15,130.00,1,'0-3m','洗浴套','玩具','屈臣氏','2016-06-30 16:07:31','7成新'),(26,17,8,240.00,2,'0-3m','三岁','安全座椅','T&R','2016-06-20 23:08:26','全新'),(27,17,21,28.00,1,'0-3m','洗手液','洗浴用品','好娃娃','2016-06-30 16:13:32','9成新'),(28,18,17,13.00,1,'0-3m','奶嘴','日常用品','花王','2016-06-30 16:09:33','6成新'),(29,18,37,333.00,1,'0-3m','测试交易完成','婴儿车','T&R','2016-08-02 00:00:00','全新');
/*!40000 ALTER TABLE `t_order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shopping_cart`
--

DROP TABLE IF EXISTS `t_shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shopping_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shopping_cart`
--

LOCK TABLES `t_shopping_cart` WRITE;
/*!40000 ALTER TABLE `t_shopping_cart` DISABLE KEYS */;
INSERT INTO `t_shopping_cart` VALUES (6,6,2,1,'2016-07-22 09:49:39'),(7,7,2,1,'2016-07-22 23:10:35'),(9,8,2,1,'2016-07-24 20:18:53'),(10,5,13,2,'2016-08-11 15:08:29'),(13,5,19,1,'2016-08-11 16:29:42'),(14,5,22,1,'2016-08-12 19:50:43');
/*!40000 ALTER TABLE `t_shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) NOT NULL COMMENT '呢称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `picture` varchar(255) DEFAULT NULL COMMENT '头像',
  `code` varchar(45) DEFAULT NULL COMMENT '短信验证码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','123123123','123@126.com','2016-07-15 19:46:31','u6.JPG',NULL),(2,'test2','B6D767D2F8ED5D21A44B0E5886680CB9',NULL,'2','2016-07-14 14:58:09','u6.JPG',NULL),(3,'test3','test',NULL,'13260592767','2016-07-14 15:03:08','u6.JPG',NULL),(5,'test1','dc483e80a7a0bd9ef71d8cf973673924','13260592767','126@qq.com','2016-07-15 21:04:22','5/20160801181420639.png',''),(6,'test4','dc483e80a7a0bd9ef71d8cf973673924',NULL,'qqqq@aq.com','2016-07-22 09:20:44','u6.JPG',NULL),(7,'test','dc483e80a7a0bd9ef71d8cf973673924','15071372539','chenheng120@126.com','2016-07-22 22:26:39','7/20160812201744649.png',NULL),(8,'test6','dc483e80a7a0bd9ef71d8cf973673924',NULL,'aa@qq.com','2016-07-24 20:18:24','u6.JPG',NULL),(9,'test7','dc483e80a7a0bd9ef71d8cf973673924',NULL,'qqqqa@aq.com','2016-07-24 21:08:19','u6.JPG',NULL),(10,'test8','dc483e80a7a0bd9ef71d8cf973673924',NULL,'qqqqq@aq.com','2016-07-27 15:26:12','u6.JPG',NULL),(11,'Wwww','e10adc3949ba59abbe56e057f20f883e',NULL,'633@qq.com','2016-07-27 18:46:24','u6.JPG',NULL),(12,'test10','80c9ef0fb86369cd25f90af27ef53a9e',NULL,'qqqqqq@aq.com','2016-07-30 15:42:45','12/20160730154316687.png',NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-15 10:12:15

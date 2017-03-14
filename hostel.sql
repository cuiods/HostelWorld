/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : hostel

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-14 16:36:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `balance` int(32) DEFAULT '0',
  `user_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ACOUNT_USER_REF` (`user_id`),
  CONSTRAINT `ACOUNT_USER_REF` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('2', '8900', '16');
INSERT INTO `account` VALUES ('3', '1000', '16');
INSERT INTO `account` VALUES ('4', '10200', '17');
INSERT INTO `account` VALUES ('5', '4948', '20');
INSERT INTO `account` VALUES ('6', '5000', '22');
INSERT INTO `account` VALUES ('7', '5000', '24');
INSERT INTO `account` VALUES ('8', '5000', '25');
INSERT INTO `account` VALUES ('9', '5000', '26');
INSERT INTO `account` VALUES ('10', '5000', '27');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AUTH_NAME_UNIQUE` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'USER_BASE', 'Basic authority of hostel user.');
INSERT INTO `authority` VALUES ('2', 'MEMBER_ACTIVE', 'Active member authority.');
INSERT INTO `authority` VALUES ('3', 'HOTEL_ACTIVE', 'Active hotel authority.');
INSERT INTO `authority` VALUES ('4', 'MEMBER_PAUSE', 'Suspend member authority.');
INSERT INTO `authority` VALUES ('5', 'MANAGER', 'Manager authority.');
INSERT INTO `authority` VALUES ('6', 'HOTEL_PAUSE', 'Suspend hotel authority.');

-- ----------------------------
-- Table structure for check_record
-- ----------------------------
DROP TABLE IF EXISTS `check_record`;
CREATE TABLE `check_record` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `roomid` int(32) unsigned DEFAULT NULL,
  `memberid` int(32) unsigned DEFAULT NULL,
  `state` enum('checkIn','checkOut','complete') DEFAULT 'checkIn',
  `payway` enum('cash','member') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `pay` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `CHECK_MEMBER_REF` (`memberid`),
  KEY `CHECK_ROOM_REF` (`roomid`),
  CONSTRAINT `CHECK_MEMBER_REF` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `CHECK_ROOM_REF` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_record
-- ----------------------------
INSERT INTO `check_record` VALUES ('1', '1', '16', 'complete', 'cash', '2017-02-05 08:48:20', '2017-02-14 10:49:11', '2017-02-04', '2017-02-05', '0');
INSERT INTO `check_record` VALUES ('2', '1', '20', 'complete', 'cash', '2017-02-14 10:55:15', '2017-02-15 10:43:01', '2017-02-14', '2017-02-15', '0');
INSERT INTO `check_record` VALUES ('3', '1', null, 'checkIn', 'cash', '2017-02-15 10:34:46', '2017-02-15 10:43:04', '2017-02-15', '2017-02-16', '0');
INSERT INTO `check_record` VALUES ('4', '1', '20', 'complete', 'member', '2017-02-15 10:37:55', '2017-02-15 10:42:54', '2017-02-15', '2017-02-16', '0');
INSERT INTO `check_record` VALUES ('5', '1', '20', 'complete', 'member', '2017-02-19 11:04:57', '2017-02-19 11:04:57', '2017-02-19', '2017-02-20', '1');
INSERT INTO `check_record` VALUES ('6', '1', null, 'complete', 'cash', '2017-03-12 15:02:15', '2017-03-12 15:02:15', '2017-03-12', '2017-03-13', '0');
INSERT INTO `check_record` VALUES ('9', '1', null, 'checkIn', 'cash', '2017-03-12 17:32:35', '2017-03-12 17:32:35', '2017-03-01', '2017-03-02', '0');

-- ----------------------------
-- Table structure for check_tenant
-- ----------------------------
DROP TABLE IF EXISTS `check_tenant`;
CREATE TABLE `check_tenant` (
  `check_id` int(32) unsigned DEFAULT NULL,
  `tenant_id` int(32) unsigned DEFAULT NULL,
  KEY `CHECK_REF` (`check_id`),
  KEY `TENANT_REF` (`tenant_id`),
  CONSTRAINT `CHECK_REF` FOREIGN KEY (`check_id`) REFERENCES `check_record` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TENANT_REF` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check_tenant
-- ----------------------------
INSERT INTO `check_tenant` VALUES ('3', '1');
INSERT INTO `check_tenant` VALUES ('4', '1');
INSERT INTO `check_tenant` VALUES ('5', '4');
INSERT INTO `check_tenant` VALUES ('9', '11');

-- ----------------------------
-- Table structure for consume_record
-- ----------------------------
DROP TABLE IF EXISTS `consume_record`;
CREATE TABLE `consume_record` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `number` int(11) NOT NULL,
  `member_id` int(32) unsigned NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CONSUME_MEMBER_REF` (`member_id`),
  CONSTRAINT `CONSUME_MEMBER_REF` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consume_record
-- ----------------------------
INSERT INTO `consume_record` VALUES ('3', '2017-03-11 15:42:12', '98', '20', 'Return from reservation');
INSERT INTO `consume_record` VALUES ('4', '2017-03-11 16:56:42', '98', '20', 'Return from reservation');
INSERT INTO `consume_record` VALUES ('5', '2017-03-11 16:57:16', '-196', '20', 'Member card payment');
INSERT INTO `consume_record` VALUES ('6', '2017-03-11 18:07:03', '98', '20', 'Return from reservation');
INSERT INTO `consume_record` VALUES ('7', '2017-03-11 21:39:12', '50', '20', 'Transfer from Account:5');
INSERT INTO `consume_record` VALUES ('8', '2017-03-11 21:39:30', '2', '20', 'Transfer from Account:5');
INSERT INTO `consume_record` VALUES ('9', '2017-03-11 21:49:16', '10', '20', 'Exchange from score');
INSERT INTO `consume_record` VALUES ('10', '2017-03-12 15:00:47', '-192', '20', 'Member card payment');
INSERT INTO `consume_record` VALUES ('11', '2017-03-13 19:07:13', '-96', '20', 'Member card payment');
INSERT INTO `consume_record` VALUES ('12', '2017-03-13 19:07:23', '192', '20', 'Return from reservation');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` int(32) unsigned NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `state` enum('newly','edit','normal') DEFAULT 'newly',
  `location` varchar(255) NOT NULL,
  `location_x` double(64,0) DEFAULT NULL,
  `location_y` double(64,0) DEFAULT NULL,
  `description` text,
  `summary` varchar(50) DEFAULT '',
  `star` enum('one','two','three','four','five') DEFAULT 'one',
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `HOTEL_USER_REF` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('17', '南京涵田城市酒店', 'normal', '秦淮区建康路3号，近中华路 【 夫子庙地区 】', '0', '0', '南京涵田城市酒店，位于夫子庙景区，是“水平方”商业综合体的重要组成部分，住、吃、购、游、娱一应俱全，紧临南京“水游城”商业综合体，地理位置优越，距新街口市中心只有1公里，步行至地铁一号线三山街站仅需2-3分钟，酒店周边有多路公交可直达中山陵、总统府、雨花台等著名景点。酒店由秉承“服务制胜”理念的南京颐斯汀酒店管理咨询有限公司管理。\r\n　　南京涵田城市酒店，设计时尚，装修考究，客房配置完善、优质、精致，高星级标准的棉织品带给您贴身舒适的感觉，无线和宽带上网，让您尽享网络冲浪的自在便捷。特色的自助餐厅、浪漫的露天花园咖啡屋和茶餐厅让您感受古老都市的繁华与时尚；多个中小型会议室是您举办会议或培训的理想场所。', '购物便捷 休闲度假 亲子时刻 城市观光', 'five', 'a http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel` VALUES ('21', '\r\n南京金鹰国际酒店', 'normal', '秦淮区汉中路101号金鹰新街口店B座，近新街口螺丝转弯。地铁一号线新街口站20-22出口，地铁2号线上海路站1号出口', '1', '1', '南京金鹰国际酒店坐落于新街口金鹰商城B座，位于地铁1、2号线的交汇点，乘地铁可轻松到达著名的夫子庙、玄武湖、总统府、中山陵，至南京火车站、高铁南站也很是便捷。\r\n　　这里的每间客房均撷取民国风格之菁华，配全景落地窗、48寸LED液晶电视、BOSE音响系统、现磨咖啡机、超大洗漱空间、欧舒丹洗浴用品以及世界顶级思涟床具等。原汁原味无添加的中西餐厅让你养·味兼顾。满园春中餐厅准备了独一无二的私密空间；拥有开放式厨房的大华西餐厅全天候带你领略世界美食的魅力。充满书香气息的大堂吧奉上纯香咖啡、精致下午茶。', '配备世界顶级品牌设施的健身中心、室内恒温游泳池、专业瑜伽教室……动静随心，让你尽享大汗淋漓后的畅快。', 'three', 'a http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel` VALUES ('22', '南京中心大酒店', 'newly', '鼓楼区中山路75号，近德基广场', '1', '1', '酒店建筑面积3.8万平方米，是集客房、餐饮、会议、健身、美容、购物于一体的涉外豪华酒店。近年来更是对酒店进行了全面更新改造，整个酒店面貌焕然一新，形成了以25米高的中空大堂为轴心的酒店新形象——拥抱阳光的馨城。\r\n　　酒店致力于打造以主动发现宾客需求，给宾客带来惊喜为宗旨的礼仪行动这——服务品牌，营造你出在家中的温馨感觉，提供尽善尽美的住宿体验。', '南京中心大酒店位于鼓楼区中山路，是南京繁华的商业中心，而且周围也有中华民国高级将领俱乐部.', 'five', 'shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel` VALUES ('24', '\r\n桔子酒店', 'newly', '秦淮区大石坝街26号，夫子庙东门斜对面', '1', '1', '酒店周边有多条公交专线，到繁华的新街口商业街非常方便。\r\n　　全球知名的外资设计师全力打造的酒店品牌，客房内配备36寸液晶电视、WIFI上网、24小时热水淋浴、中央空调、电子秤等，隔音由清华声学所设计，保证你的安静睡眠。\r\n　　这里还为旅客提供各种服务，包括旅游票务服务、洗衣服务、行李寄存、叫醒服务、商务服务等，让你感受酒店无微不至的细节服务。', '桔子酒店（南京夫子庙店）坐落在秦淮河边，酒店窗外就是美丽的秦淮河。', 'two', 'shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel` VALUES ('25', '\r\n南京古南都饭店', 'newly', '鼓楼区广州路208号，近上海路', '1', '1', '南京古南都饭店位于南京市的中心地带——新街口地区，与江苏省五台山体育中心隔街相，是一家豪华商务饭店。饭店附近集中了南京大学、南京师范大学、河海大学等全国著名高等学府及多家金融商贸以及南京市儿童医院，省人民医院、省中医院等医疗机构；周边餐饮、娱乐、休闲及健身场所林立。饭店地理位置优越，人文气氛浓郁；交通便利，闹中取静。 \r\n　　南京古南都饭店共26层，总高度104米，拥有各类装饰雅致、配备完好的高档客房。客房提供个性化贴身式管家服务，为宾客创造温馨的“家外之家”。饭店客房在2003年全新装修，每间房间均设有直接饮用水系统，还特设置了适合居家旅游或长包长住的带独立厨房的豪华公寓房。饭店设有中餐、西餐、日餐各具特色；东方园林风格的屋顶花园空气清新，鸟语花香，为您的商旅之途倍添休闲情趣。', '饭店地理位置优越，人文气氛浓郁；交通便利，闹中取静。', 'five', 'shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel` VALUES ('26', '南京金陵饭店', 'newly', '鼓楼区汉中路2号，近中山路', '1', '1', '南京金陵饭店位于鼓楼区汉中路，近中山路；饭店四周德基广场、金鹰购物中心、哈姆雷斯等大型购物中心近在咫尺，饭店还无缝连接地铁1、2号线及便捷的公共交通，得天独厚的地理位置使您的出行更加轻松与高效。\r\n　　饭店是南京城市的“中心地标”，东西方文化在这里交相辉映。1983年开业至今，金陵饭店多次成功地接待世界多国政要及名流巨商。金陵饭店不仅屡获“中国最受欢迎十大酒店”和“中国最佳商务酒店”殊荣，更一举摘得中国服务业领域唯一中国质量管理最高奖桂冠。\r\n　　饭店拥有近千间客房，超过4000平方米会议场地，6家荟萃中西美食的餐厅，3500平方米健身俱乐部，悦椿SPA及11000平方米精品地下商业街区“金陵风尚”。无论是商务旅行或是家庭出游，深具东方情韵的金陵饭店皆能用心满足每位客人的个性化需求。', '饭店四周德基广场、金鹰购物中心、哈姆雷斯等大型购物中心近在咫尺', 'four', 'shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel` VALUES ('27', '南京城市名人酒店', 'normal', '鼓楼区中山北路30号，近湖北路', '1', '1', '南京城市名人酒店毗邻鼓楼广场、湖南路中心商业街和狮子桥美食街，与建设中的地铁鼓楼站近在咫尺，地理位置优越。\r\n　　南京城市名人酒店由中国（香港）名人城市酒店管理集团有限公司投资管理，是一座数码智慧型豪华商务酒店。酒店所在的益来国际广场楼高178米，共51层，是南京市地标性的建筑之一。酒店拥有极具现代风格的温馨客房；其中商务楼层以上客房均配有液晶台式电脑及宽带上网。\r\n　　南京城市名人酒店引进“中国谭家菜”和“腾湘阁”等两个品牌餐饮，共有餐位1000余个。此外，店内康体娱乐、会议、商务等配套设施一应俱全。人性化的设施配置和个性化的服务追求，将带给您完全不同的全方位享受。', '毗邻鼓楼广场、湖南路中心商业街和狮子桥美食街，与建设中的地铁鼓楼站近在咫尺，地理位置优越', 'five', 'shanghai.aliyuncs.com/images/logo.png');

-- ----------------------------
-- Table structure for hotel_temp
-- ----------------------------
DROP TABLE IF EXISTS `hotel_temp`;
CREATE TABLE `hotel_temp` (
  `id` int(32) unsigned NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `state` enum('newly','edit','normal') DEFAULT 'newly',
  `location` varchar(255) NOT NULL,
  `location_x` double(64,0) DEFAULT NULL,
  `location_y` double(64,0) DEFAULT NULL,
  `description` text,
  `summary` varchar(50) DEFAULT NULL,
  `star` enum('one','two','three','four','five') DEFAULT 'one',
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `HOTELTEMP_USER_REF` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel_temp
-- ----------------------------
INSERT INTO `hotel_temp` VALUES ('17', '南京涵田城市酒店', 'normal', '秦淮区建康路3号，近中华路 【 夫子庙地区 】', '0', '0', '南京涵田城市酒店，位于夫子庙景区，是“水平方”商业综合体的重要组成部分，住、吃、购、游、娱一应俱全，紧临南京“水游城”商业综合体，地理位置优越，距新街口市中心只有1公里，步行至地铁一号线三山街站仅需2-3分钟，酒店周边有多路公交可直达中山陵、总统府、雨花台等著名景点。酒店由秉承“服务制胜”理念的南京颐斯汀酒店管理咨询有限公司管理。\r\n　　南京涵田城市酒店，设计时尚，装修考究，客房配置完善、优质、精致，高星级标准的棉织品带给您贴身舒适的感觉，无线和宽带上网，让您尽享网络冲浪的自在便捷。特色的自助餐厅、浪漫的露天花园咖啡屋和茶餐厅让您感受古老都市的繁华与时尚；多个中小型会议室是您举办会议或培训的理想场所。', '购物便捷 休闲度假 亲子时刻 城市观光', 'five', 'a http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/logo.png');
INSERT INTO `hotel_temp` VALUES ('21', 'string', 'edit', 'string', '0', '0', 'string', 'string', 'four', 'string');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(32) unsigned NOT NULL,
  `state` enum('newly','active','pause','stop') DEFAULT 'newly',
  `level` int(11) DEFAULT '0',
  `score` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT '',
  `remain` int(32) DEFAULT '0',
  `active_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `MEMBER_USER_REF` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('16', 'active', '0', '0', 'hhh', '1100', '2017-02-14');
INSERT INTO `member` VALUES ('20', 'active', '3248', '10288', 'aaaaaaa', '29104', '2017-03-11');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `receive` int(32) unsigned NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `isRead` enum('read','unread') DEFAULT 'unread',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `MESSAGE_USER_REF` (`receive`),
  CONSTRAINT `MESSAGE_USER_REF` FOREIGN KEY (`receive`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `alt` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `roomid` int(32) unsigned DEFAULT NULL,
  `memberid` int(32) unsigned DEFAULT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `state` enum('reserve','checkIn') DEFAULT 'reserve',
  `extra` varchar(255) DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `RESERVE_ROOM_REF` (`roomid`),
  KEY `RESERVE_MEMBER_REF` (`memberid`),
  CONSTRAINT `RESERVE_MEMBER_REF` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE SET NULL,
  CONSTRAINT `RESERVE_ROOM_REF` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reserve
-- ----------------------------
INSERT INTO `reserve` VALUES ('1', '1', '16', '2017-02-04', '2017-02-05', 'cuihao', '110', 'checkIn', '', '2017-02-03 22:48:51', '2017-02-14 10:48:49', null);
INSERT INTO `reserve` VALUES ('6', '1', '20', '2017-02-15', '2017-02-16', 'member', '110', 'checkIn', '', '2017-02-14 17:16:40', '2017-02-15 10:25:59', '2017-03-11 18:07:04');
INSERT INTO `reserve` VALUES ('8', '1', '20', '2017-02-19', '2017-02-20', 'cuiods', '18795859216', 'checkIn', 'aaaaaaa', '2017-02-19 10:57:31', '2017-02-19 10:57:31', null);
INSERT INTO `reserve` VALUES ('9', '2', '20', '2017-02-19', '2017-02-20', 'cuiods', '18795859216', 'reserve', 'aaaaaaa', '2017-02-19 10:59:31', '2017-02-19 10:59:31', '2017-02-19 11:00:00');
INSERT INTO `reserve` VALUES ('12', '1', '20', '2017-03-11', '2017-03-12', 'cuiods', '18795859216', 'reserve', '如安静的房间', '2017-03-11 11:40:44', '2017-03-11 11:40:44', '2017-03-11 15:42:13');
INSERT INTO `reserve` VALUES ('13', '1', '20', '2017-03-11', '2017-03-12', 'cuiods', '18795859216', 'reserve', '如安静的房间', '2017-03-11 15:08:38', '2017-03-11 15:08:38', '2017-03-11 15:09:53');
INSERT INTO `reserve` VALUES ('14', '2', '20', '2017-03-12', '2017-03-13', '崔浩', '18795859216', 'reserve', '如安静的房间', '2017-03-11 15:20:35', '2017-03-11 15:20:35', null);
INSERT INTO `reserve` VALUES ('15', '4', '20', '2017-03-12', '2017-03-13', '乔娟', '18795859216', 'reserve', '如安静的房间', '2017-03-11 15:20:48', '2017-03-11 15:20:48', '2017-03-11 15:21:37');
INSERT INTO `reserve` VALUES ('16', '1', '20', '2017-03-23', '2017-03-25', 'cuiods', '18795859216', 'reserve', '如安静的房间', '2017-03-11 15:21:00', '2017-03-11 15:21:00', '2017-03-11 16:56:42');
INSERT INTO `reserve` VALUES ('17', '1', '20', '2017-03-15', '2017-03-16', 'cuiods', '18795859216', 'reserve', '期望有阳光', '2017-03-11 15:36:03', '2017-03-11 15:36:03', null);
INSERT INTO `reserve` VALUES ('18', '2', '20', '2017-03-29', '2017-03-30', '乔娟', '18795859216', 'reserve', '安静的房间', '2017-03-11 15:36:20', '2017-03-11 15:36:20', null);
INSERT INTO `reserve` VALUES ('19', '4', '20', '2017-03-24', '2017-03-25', '崔浩', '18795859216', 'reserve', '安静的房间', '2017-03-11 16:57:16', '2017-03-11 16:57:16', null);
INSERT INTO `reserve` VALUES ('20', '6', '20', '2017-03-12', '2017-03-13', '崔浩', '18795859216', 'reserve', '最好是有阳光的房间', '2017-03-12 15:00:47', '2017-03-12 15:00:47', '2017-03-13 19:07:23');
INSERT INTO `reserve` VALUES ('21', '1', '20', '2017-03-16', '2017-03-17', '崔浩', '18795859216', 'reserve', '如安静的房间', '2017-03-13 19:07:13', '2017-03-13 19:07:13', null);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `hotel_id` int(32) unsigned NOT NULL,
  `room_type` varchar(50) NOT NULL,
  `size` int(11) DEFAULT NULL,
  `people` int(11) NOT NULL,
  `bed_type` enum('big','two','bigAndTwo','normal','normalAndTwo') NOT NULL DEFAULT 'big',
  `description` varchar(255) DEFAULT '',
  `number` int(11) NOT NULL DEFAULT '0',
  `price` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ROOM_HOTEL_REF` (`hotel_id`),
  CONSTRAINT `ROOM_HOTEL_REF` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '17', 'single dog', '18', '1', 'big', 'single dog big bed', '20', '100.00', '2017-02-03 17:50:36', '2017-02-15 10:22:02', '2016-12-25', '2017-05-04', null);
INSERT INTO `room` VALUES ('2', '17', 'two dogs', '20', '2', 'normalAndTwo', 'two single dog bed', '20', '200.00', '2017-02-03 17:51:32', '2017-02-15 10:22:08', '2017-02-03', '2017-04-19', null);
INSERT INTO `room` VALUES ('4', '17', 'big room', '24', '3', 'big', '', '10', '200.00', '2017-02-15 10:52:30', '2017-02-15 10:52:30', '2017-01-15', '2017-12-16', null);
INSERT INTO `room` VALUES ('5', '21', 'aaaa', '12', '2', 'big', '12111aaa', '12', '120.00', '2017-02-16 13:29:02', '2017-02-16 13:29:02', '2017-02-16', '2017-03-01', null);
INSERT INTO `room` VALUES ('6', '17', '标准间A', '24', '2', 'two', '闹中取静，独立卫生间，免费WIFI', '10', '200.00', '2017-03-12 11:25:23', '2017-03-12 11:25:23', '2017-03-12', '2017-03-31', null);

-- ----------------------------
-- Table structure for room_picture
-- ----------------------------
DROP TABLE IF EXISTS `room_picture`;
CREATE TABLE `room_picture` (
  `roomid` int(32) unsigned NOT NULL,
  `picid` int(32) unsigned NOT NULL,
  PRIMARY KEY (`roomid`,`picid`),
  KEY `PIC_PIC_REF` (`picid`),
  CONSTRAINT `PIC_PIC_REF` FOREIGN KEY (`picid`) REFERENCES `picture` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PIC_ROOM_REF` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_picture
-- ----------------------------

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `id_card` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tenant
-- ----------------------------
INSERT INTO `tenant` VALUES ('1', 'aaa', '320682199612220001');
INSERT INTO `tenant` VALUES ('2', 'bbb', '320682199612090002');
INSERT INTO `tenant` VALUES ('3', 'ccc', '320682199601010003');
INSERT INTO `tenant` VALUES ('4', 'cuiods', '320682199606280191');
INSERT INTO `tenant` VALUES ('11', 'cuiods', '320682199606280000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `gender` enum('male','female') NOT NULL DEFAULT 'male',
  `type` enum('member','hotel','manager','admin') NOT NULL DEFAULT 'member',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `valid` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'cuiods', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'member', '2017-02-03 11:38:09', '2017-03-13 21:23:22', null, '0');
INSERT INTO `user` VALUES ('17', 'chotel', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'hotel', '2017-02-03 16:16:48', '2017-03-13 21:23:20', null, '0');
INSERT INTO `user` VALUES ('20', 'member', '123456', '13962729161', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'member', '2017-02-14 10:29:30', '2017-03-13 21:23:18', null, '0');
INSERT INTO `user` VALUES ('21', 'testHotel', '123456', '12345678', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'female', 'hotel', '2017-02-15 11:18:26', '2017-03-13 21:23:16', null, '0');
INSERT INTO `user` VALUES ('22', 'centerHotel', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'hotel', '2017-03-10 12:44:14', '2017-03-13 21:23:04', null, '0');
INSERT INTO `user` VALUES ('24', 'anotherHotel', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'hotel', '2017-03-10 12:45:31', '2017-03-13 21:23:06', null, '0');
INSERT INTO `user` VALUES ('25', 'hotel5', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'hotel', '2017-03-10 12:45:46', '2017-03-13 21:23:08', null, '0');
INSERT INTO `user` VALUES ('26', 'hotel6', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'hotel', '2017-03-10 12:45:51', '2017-03-13 21:23:10', null, '0');
INSERT INTO `user` VALUES ('27', 'hotel7', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'hotel', '2017-03-10 12:45:56', '2017-03-13 21:23:11', null, '0');
INSERT INTO `user` VALUES ('28', 'manager', '123456', '18795859216', 'http://hostel-world.oss-cn-shanghai.aliyuncs.com/images/avatar.png', 'male', 'manager', '2017-03-13 09:12:10', '2017-03-13 21:23:14', null, '0');

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `user_id` int(32) unsigned NOT NULL,
  `auth_id` int(32) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`auth_id`),
  KEY `USERAUTH_AUTH_REF` (`auth_id`),
  CONSTRAINT `USERAUTH_AUTH_REF` FOREIGN KEY (`auth_id`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `USERAUTH_USER_REF` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('16', '1');
INSERT INTO `user_authority` VALUES ('17', '1');
INSERT INTO `user_authority` VALUES ('20', '1');
INSERT INTO `user_authority` VALUES ('22', '1');
INSERT INTO `user_authority` VALUES ('24', '1');
INSERT INTO `user_authority` VALUES ('25', '1');
INSERT INTO `user_authority` VALUES ('26', '1');
INSERT INTO `user_authority` VALUES ('27', '1');
INSERT INTO `user_authority` VALUES ('28', '1');
INSERT INTO `user_authority` VALUES ('16', '2');
INSERT INTO `user_authority` VALUES ('20', '2');
INSERT INTO `user_authority` VALUES ('28', '2');
INSERT INTO `user_authority` VALUES ('17', '3');
INSERT INTO `user_authority` VALUES ('28', '3');
INSERT INTO `user_authority` VALUES ('20', '4');
INSERT INTO `user_authority` VALUES ('28', '4');
INSERT INTO `user_authority` VALUES ('28', '5');
INSERT INTO `user_authority` VALUES ('17', '6');
INSERT INTO `user_authority` VALUES ('22', '6');
INSERT INTO `user_authority` VALUES ('24', '6');
INSERT INTO `user_authority` VALUES ('25', '6');
INSERT INTO `user_authority` VALUES ('26', '6');
INSERT INTO `user_authority` VALUES ('27', '6');
INSERT INTO `user_authority` VALUES ('28', '6');

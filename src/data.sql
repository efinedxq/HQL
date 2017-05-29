drop database if exists hibernate;

create database hibernate;

use hibernate;

CREATE TABLE event_inf (
  event_id int(11) NOT NULL auto_increment,
  happenDate date default NULL,
  title varchar(255) default NULL,
  PRIMARY KEY  (event_id)
);

INSERT INTO event_inf VALUES
(1,'2004-10-03','很高兴的事情'),
(2,'2005-10-03','很普通的事情'),
(3,'2004-10-04','疯狂Java筹备中'),
(4,'2005-10-05','疯狂Java开始培训');

CREATE TABLE person_inf (
  person_id int(11) NOT NULL auto_increment,
  name varchar(255) default NULL,
  age int(11) default NULL,
  PRIMARY KEY  (person_id)
);


INSERT INTO person_inf VALUES
(1,'crazyit.org',30),
(2,'老朱',30);


CREATE TABLE person_email_inf (
  person_id int(11) NOT NULL,
  email_detail varchar(255) default NULL,
  KEY FKECD3B632CC53FFDC (person_id),
  FOREIGN KEY (person_id) REFERENCES person_inf (person_id)
);

INSERT INTO person_email_inf VALUES 
(1,'crazyit@crazyit.org'),
(1,'crazyit@fkit.org'),
(2,'dddd@163.com'),
(2,'vvvvvv@163.com');

CREATE TABLE person_event (
  person_id int(11) NOT NULL,
  event_id int(11) NOT NULL,
  PRIMARY KEY  (person_id,event_id),
  KEY FKECD7DD30273C5F2C (event_id),
  KEY FKECD7DD30CC53FFDC (person_id),
  FOREIGN KEY (person_id) REFERENCES person_inf (person_id),
  FOREIGN KEY (event_id) REFERENCES event_inf (event_id)
);


INSERT INTO person_event VALUES
(1,1),
(1,2),
(2,2),
(2,3),
(1,4);

DROP TABLE IF EXISTS `a`;
CREATE TABLE `a` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a
-- ----------------------------
INSERT INTO `a` VALUES ('1', 'a1');
INSERT INTO `a` VALUES ('2', 'a2');
INSERT INTO `a` VALUES ('3', 'a3');
INSERT INTO `a` VALUES ('4', 'a4');
INSERT INTO `a` VALUES ('7', '新增a');

-- ----------------------------
-- Table structure for b
-- ----------------------------
DROP TABLE IF EXISTS `b`;
CREATE TABLE `b` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b
-- ----------------------------
INSERT INTO `b` VALUES ('1', 'b1');
INSERT INTO `b` VALUES ('2', 'b2');
INSERT INTO `b` VALUES ('3', 'b3');
INSERT INTO `b` VALUES ('4', 'b4');
INSERT INTO `b` VALUES ('7', '新增b');


-- ----------------------------
-- Table structure for r
-- ----------------------------
DROP TABLE IF EXISTS `r`;
CREATE TABLE `r` (
  `aid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `rname` varchar(255) DEFAULT NULL,
  KEY `aid` (`aid`),
  KEY `bid` (`bid`),
  CONSTRAINT `aid` FOREIGN KEY (`aid`) REFERENCES `a` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bid` FOREIGN KEY (`bid`) REFERENCES `b` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r
-- ----------------------------
INSERT INTO `r` VALUES ('1', '1', 'r1');
INSERT INTO `r` VALUES ('1', '2', 'r2');
INSERT INTO `r` VALUES ('1', '3', 'r3');
INSERT INTO `r` VALUES ('1', '4', 'r4');
INSERT INTO `r` VALUES ('2', '1', 'r5');
INSERT INTO `r` VALUES ('2', '3', 'r6');
INSERT INTO `r` VALUES ('2', '4', 'r7');
INSERT INTO `r` VALUES ('3', '4', 'r7');
INSERT INTO `r` VALUES ('1', '7', '新增r');

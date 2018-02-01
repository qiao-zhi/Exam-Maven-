/*
SQLyog Ultimate v8.32 
MySQL - 5.7.10-log : Database - exam
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`exam` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `exam`;

/*Table structure for table `bigquestion` */

DROP TABLE IF EXISTS `bigquestion`;

CREATE TABLE `bigquestion` (
  `bigQuertionId` varchar(40) NOT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `bigQuestionName` varchar(2000) DEFAULT NULL,
  `bigQuestionSequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`bigQuertionId`),
  KEY `FK_Reference_51` (`paperId`),
  CONSTRAINT `FK_Reference_51` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='øº ‘¥Ã‚±£¥Ê±Ì';

/*Data for the table `bigquestion` */

/*Table structure for table `blacklist` */

DROP TABLE IF EXISTS `blacklist`;

CREATE TABLE `blacklist` (
  `id` int(11) NOT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `employeeOutName` varchar(40) DEFAULT NULL,
  `employeeOutIDCard` varchar(18) DEFAULT NULL,
  `unitName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_28` (`employeeId`),
  CONSTRAINT `FK_Reference_28` FOREIGN KEY (`employeeId`) REFERENCES `employee_out` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='∫⁄√˚µ•±Ì';

/*Data for the table `blacklist` */

/*Table structure for table `breakrules` */

DROP TABLE IF EXISTS `breakrules`;

CREATE TABLE `breakrules` (
  `breakId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(40) DEFAULT NULL,
  `breakContent` varchar(200) DEFAULT NULL,
  `breakTime` datetime DEFAULT NULL,
  `minusNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`breakId`),
  KEY `FK_Reference_58` (`employeeId`),
  CONSTRAINT `FK_Reference_58` FOREIGN KEY (`employeeId`) REFERENCES `employee_out` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Œ•’¬º«¬º±Ì';

/*Data for the table `breakrules` */

/*Table structure for table `checkrecord` */

DROP TABLE IF EXISTS `checkrecord`;

CREATE TABLE `checkrecord` (
  `checkId` varchar(40) NOT NULL,
  `questionId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  `checkStatus` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`checkId`),
  KEY `FK_Reference_32` (`questionId`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`questionId`) REFERENCES `questions` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='…Û∫Àº«¬º±Ì';

/*Data for the table `checkrecord` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `departmentId` varchar(40) NOT NULL,
  `upDepartmentId` varchar(40) DEFAULT NULL,
  `departmentName` varchar(40) NOT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`departmentId`),
  KEY `FK_Reference_37` (`upDepartmentId`),
  CONSTRAINT `FK_Reference_37` FOREIGN KEY (`upDepartmentId`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='∂˛º∂≤ø√≈±Ì';

/*Data for the table `department` */

/*Table structure for table `dictionary` */

DROP TABLE IF EXISTS `dictionary`;

CREATE TABLE `dictionary` (
  `dictionaryId` varchar(40) NOT NULL,
  `dictionaryName` varchar(50) NOT NULL,
  `upDictionaryId` varchar(40) NOT NULL,
  `isUse` varchar(2) NOT NULL,
  `discription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dictionaryId`),
  KEY `FK_Reference_49` (`upDictionaryId`),
  CONSTRAINT `FK_Reference_49` FOREIGN KEY (`upDictionaryId`) REFERENCES `dictionary` (`dictionaryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='œµÕ≥◊÷µ‰±Ì';

/*Data for the table `dictionary` */

/*Table structure for table `employee_in` */

DROP TABLE IF EXISTS `employee_in`;

CREATE TABLE `employee_in` (
  `employeeId` varchar(40) NOT NULL,
  `employeeNumber` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `idCode` char(18) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `photo` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `duty` varchar(20) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `finger` varchar(200) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT NULL,
  `trainStatus` int(11) DEFAULT NULL COMMENT 'øº ‘Õ®π˝«Èøˆ£∫\n            1£∫–Ë“™Ω¯––1º∂øº ‘\n            2£∫–Ë“™Ω¯––2º∂øº ‘\n            3£∫–Ë“™Ω¯––3º∂øº ‘\n            4£∫“—Õ®π˝»Î≥°≈‡—µøº ‘',
  PRIMARY KEY (`employeeId`),
  KEY `FK_Reference_9` (`departmentId`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='÷∞π§±Ì';

/*Data for the table `employee_in` */

/*Table structure for table `employee_out` */

DROP TABLE IF EXISTS `employee_out`;

CREATE TABLE `employee_out` (
  `employeeId` varchar(40) NOT NULL,
  `employeeNumber` varchar(40) NOT NULL,
  `name` varchar(10) NOT NULL,
  `idCode` char(18) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `photo` varchar(40) DEFAULT NULL,
  `duty` varchar(20) DEFAULT NULL,
  `unitId` varchar(40) DEFAULT NULL,
  `projectId` varchar(50) DEFAULT NULL,
  `minusNum` float DEFAULT NULL,
  `finger` varchar(20) DEFAULT NULL,
  `trainStatus` int(11) DEFAULT NULL COMMENT 'øº ‘Õ®π˝«Èøˆ£∫\n            1£∫–Ë“™Ω¯––1º∂øº ‘\n            2£∫–Ë“™Ω¯––2º∂øº ‘\n            3£∫–Ë“™Ω¯––3º∂øº ‘\n            4£∫“—Õ®π˝»Î≥°≈‡—µøº ‘',
  `isDelete` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  KEY `FK_Reference_57` (`unitId`),
  KEY `FK_Reference_60` (`projectId`),
  CONSTRAINT `FK_Reference_57` FOREIGN KEY (`unitId`) REFERENCES `unit` (`unitId`),
  CONSTRAINT `FK_Reference_60` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Õ‚¿¥÷∞π§±Ì';

/*Data for the table `employee_out` */

/*Table structure for table `employeeexam` */

DROP TABLE IF EXISTS `employeeexam`;

CREATE TABLE `employeeexam` (
  `gradeId` varchar(40) NOT NULL,
  `examId` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `grade` float DEFAULT NULL,
  `examMethod` varchar(5) DEFAULT NULL,
  `employeeType` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`gradeId`),
  KEY `FK_Reference_53` (`examId`),
  CONSTRAINT `FK_Reference_53` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='‘±π§øº ‘±Ì';

/*Data for the table `employeeexam` */

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `examId` varchar(40) NOT NULL,
  `examName` varchar(2000) DEFAULT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`examId`),
  KEY `FK_Reference_50` (`paperId`),
  CONSTRAINT `FK_Reference_50` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='øº ‘';

/*Data for the table `exam` */

/*Table structure for table `exampaper` */

DROP TABLE IF EXISTS `exampaper`;

CREATE TABLE `exampaper` (
  `paperId` varchar(40) NOT NULL,
  `paperScore` float DEFAULT NULL,
  `makeTime` datetime NOT NULL,
  `level` varchar(10) NOT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `answer` varchar(300) DEFAULT NULL,
  `useTimes` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' ‘æÌ±Ì';

/*Data for the table `exampaper` */

/*Table structure for table `exampaperoption` */

DROP TABLE IF EXISTS `exampaperoption`;

CREATE TABLE `exampaperoption` (
  `optionId` varchar(40) NOT NULL,
  `questionId` varchar(40) DEFAULT NULL,
  `optionContent` varchar(300) NOT NULL,
  `optionSequence` varchar(2) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`optionId`),
  KEY `FK_Reference_45` (`questionId`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`questionId`) REFERENCES `exampaperquestion` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='¿˙ ∑ ‘æÌ—°œÓ±Ì';

/*Data for the table `exampaperoption` */

/*Table structure for table `exampaperquestion` */

DROP TABLE IF EXISTS `exampaperquestion`;

CREATE TABLE `exampaperquestion` (
  `questionId` varchar(40) NOT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `bigQuertionId` varchar(40) DEFAULT NULL,
  `questionContent` varchar(200) NOT NULL,
  `questionSequence` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL,
  `analysis` varchar(2000) DEFAULT NULL,
  `score` float NOT NULL,
  `questionSource` varchar(2) NOT NULL,
  PRIMARY KEY (`questionId`),
  KEY `FK_Reference_38` (`paperId`),
  KEY `FK_Reference_52` (`bigQuertionId`),
  CONSTRAINT `FK_Reference_38` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`),
  CONSTRAINT `FK_Reference_52` FOREIGN KEY (`bigQuertionId`) REFERENCES `bigquestion` (`bigQuertionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' ‘æÌÃ‚ƒø±Ì';

/*Data for the table `exampaperquestion` */

/*Table structure for table `historybreakrules` */

DROP TABLE IF EXISTS `historybreakrules`;

CREATE TABLE `historybreakrules` (
  `breakId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(40) DEFAULT NULL,
  `breakContent` varchar(200) DEFAULT NULL,
  `breakTime` datetime DEFAULT NULL,
  `minusNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`breakId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='¿˙ ∑Œ•’¬º«¬º±Ì';

/*Data for the table `historybreakrules` */

/*Table structure for table `historyemployeeexam` */

DROP TABLE IF EXISTS `historyemployeeexam`;

CREATE TABLE `historyemployeeexam` (
  `gradeId` varchar(40) NOT NULL,
  `examId` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `grade` float DEFAULT NULL,
  `examMethod` varchar(5) DEFAULT NULL,
  `employeeType` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`gradeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Õ‚¿¥‘±π§¿˙ ∑≥…º®±Ì';

/*Data for the table `historyemployeeexam` */

/*Table structure for table `newsrecord` */

DROP TABLE IF EXISTS `newsrecord`;

CREATE TABLE `newsrecord` (
  `newsId` int(11) NOT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `title` varchar(500) NOT NULL,
  `content` text NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Õ®÷™±Ì';

/*Data for the table `newsrecord` */

/*Table structure for table `onlineexamanswerinfor` */

DROP TABLE IF EXISTS `onlineexamanswerinfor`;

CREATE TABLE `onlineexamanswerinfor` (
  `answerInfoId` varchar(40) NOT NULL,
  `questionId` varchar(40) NOT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `questionType` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL,
  `selectOption` varchar(30) DEFAULT NULL,
  `questionScore` float DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`answerInfoId`),
  KEY `FK_Reference_35` (`paperId`),
  KEY `FK_Reference_47` (`employeeId`),
  KEY `FK_Reference_48` (`questionId`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`),
  CONSTRAINT `FK_Reference_47` FOREIGN KEY (`employeeId`) REFERENCES `employee_in` (`employeeId`),
  CONSTRAINT `FK_Reference_48` FOREIGN KEY (`questionId`) REFERENCES `exampaperquestion` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='‘⁄œﬂøº ‘¥Ã‚œÍœ∏–≈œ¢±Ì';

/*Data for the table `onlineexamanswerinfor` */

/*Table structure for table `onlineexaminfor` */

DROP TABLE IF EXISTS `onlineexaminfor`;

CREATE TABLE `onlineexaminfor` (
  `onLineExamId` varchar(40) NOT NULL,
  `examId` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `ipAddress` varchar(50) DEFAULT NULL,
  `examStatus` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`onLineExamId`),
  KEY `FK_Reference_33` (`examId`),
  KEY `FK_Reference_44` (`employeeId`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`),
  CONSTRAINT `FK_Reference_44` FOREIGN KEY (`employeeId`) REFERENCES `employee_in` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='‘⁄œﬂøº ‘ª˘±æ–≈œ¢±Ì';

/*Data for the table `onlineexaminfor` */

/*Table structure for table `options` */

DROP TABLE IF EXISTS `options`;

CREATE TABLE `options` (
  `optionId` varchar(40) NOT NULL,
  `questionId` varchar(40) DEFAULT NULL,
  `optionContent` varchar(2000) NOT NULL,
  `optionWithTag` varchar(2000) DEFAULT NULL,
  `optionSequence` varchar(2) NOT NULL,
  `isAnswer` varchar(2) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`optionId`),
  KEY `FK_Reference_43` (`questionId`),
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`questionId`) REFERENCES `questions` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='—°œÓ±Ì';

/*Data for the table `options` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `permissionId` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `status` varchar(2) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `permissionCode` varchar(40) DEFAULT NULL,
  `parentId` varchar(40) DEFAULT NULL,
  `parentsId` varchar(40) DEFAULT NULL,
  `sortNo` varchar(40) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='»®œﬁ±Ì';

/*Data for the table `permission` */

/*Table structure for table `pictureindex` */

DROP TABLE IF EXISTS `pictureindex`;

CREATE TABLE `pictureindex` (
  `pictureId` varchar(40) NOT NULL,
  `originalName` varchar(50) DEFAULT NULL,
  `currentName` varchar(50) DEFAULT NULL,
  `pictureSize` varchar(10) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pictureId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Õº∆¨±Ì';

/*Data for the table `pictureindex` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `projectId` varchar(50) NOT NULL,
  `unitId` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `contactPerson` varchar(40) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='œÓƒø±Ì';

/*Data for the table `project` */

/*Table structure for table `questionbank` */

DROP TABLE IF EXISTS `questionbank`;

CREATE TABLE `questionbank` (
  `questionBankId` varchar(40) NOT NULL,
  `questionBankName` varchar(40) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isUse` varchar(2) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`questionBankId`),
  KEY `FK_Reference_29` (`departmentId`),
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ã‚ø‚±Ì';

/*Data for the table `questionbank` */

/*Table structure for table `questions` */

DROP TABLE IF EXISTS `questions`;

CREATE TABLE `questions` (
  `questionId` varchar(40) NOT NULL,
  `questionBankId` varchar(40) DEFAULT NULL,
  `question` varchar(2000) NOT NULL,
  `questionWithTag` varchar(2000) DEFAULT NULL,
  `answer` varchar(40) NOT NULL,
  `analysisWithTag` varchar(1000) DEFAULT NULL,
  `analysis` varchar(2000) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `level` varchar(10) NOT NULL,
  `employeeId` varchar(40) NOT NULL,
  `emplloyeeName` varchar(40) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `knowledgeType` varchar(40) DEFAULT NULL,
  `hasPicture` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `FK_Reference_30` (`questionBankId`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`questionBankId`) REFERENCES `questionbank` (`questionBankId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ã‚ƒø±Ì';

/*Data for the table `questions` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` varchar(40) NOT NULL,
  `roleName` varchar(40) NOT NULL,
  `roleStatus` varchar(2) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ω«…´±Ì';

/*Data for the table `role` */

/*Table structure for table `rolepermission` */

DROP TABLE IF EXISTS `rolepermission`;

CREATE TABLE `rolepermission` (
  `roleId` varchar(40) NOT NULL,
  `permissionId` varchar(40) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`),
  KEY `FK_Reference_56` (`permissionId`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FK_Reference_56` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ω«…´»®œﬁ±Ì';

/*Data for the table `rolepermission` */

/*Table structure for table `traincontent` */

DROP TABLE IF EXISTS `traincontent`;

CREATE TABLE `traincontent` (
  `documentId` int(11) NOT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `documentName` varchar(50) DEFAULT NULL,
  `trainType` varchar(50) DEFAULT NULL,
  `departmentName` varchar(40) DEFAULT NULL,
  `knowledgeType` varchar(40) DEFAULT NULL,
  `originalName` varchar(50) DEFAULT NULL,
  `currentName` varchar(50) DEFAULT NULL,
  `upTime` datetime DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `level` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `browseTimes` int(11) DEFAULT NULL,
  PRIMARY KEY (`documentId`),
  KEY `FK_Reference_59` (`departmentId`),
  CONSTRAINT `FK_Reference_59` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='≈‡—µ◊ ¡œ±Ì';

/*Data for the table `traincontent` */

/*Table structure for table `unit` */

DROP TABLE IF EXISTS `unit`;

CREATE TABLE `unit` (
  `unitId` varchar(40) NOT NULL,
  `upUnitId` varchar(40) DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact` varchar(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`unitId`),
  KEY `FK_Reference_39` (`upUnitId`),
  CONSTRAINT `FK_Reference_39` FOREIGN KEY (`upUnitId`) REFERENCES `unit` (`unitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Õ‚¿¥µ•Œª±Ì';

/*Data for the table `unit` */

insert  into `unit`(`unitId`,`upUnitId`,`name`,`address`,`contact`,`phone`,`grade`) values ('6aead9286d614a10ab28ddf150035b13',NULL,'ÈÉ®Èó®1','Âú∞ÂùÄ1','ËÅîÁ≥ª‰∫∫1','1388888888',12);

/*Table structure for table `unitproject` */

DROP TABLE IF EXISTS `unitproject`;

CREATE TABLE `unitproject` (
  `unitId` varchar(40) NOT NULL,
  `projectId` varchar(50) NOT NULL,
  PRIMARY KEY (`unitId`,`projectId`),
  KEY `FK_Reference_55` (`projectId`),
  CONSTRAINT `FK_Reference_54` FOREIGN KEY (`unitId`) REFERENCES `unit` (`unitId`),
  CONSTRAINT `FK_Reference_55` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Õ‚¿¥µ•Œªπ§≥Ã±Ì';

/*Data for the table `unitproject` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` varchar(40) NOT NULL,
  `userIDCard` varchar(18) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `departmentName` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `userPhoto` varchar(40) DEFAULT NULL,
  `isUse` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='”√ªß±Ì';

/*Data for the table `user` */

/*Table structure for table `userrole` */

DROP TABLE IF EXISTS `userrole`;

CREATE TABLE `userrole` (
  `employeeId` varchar(40) NOT NULL,
  `roleId` varchar(40) NOT NULL,
  `userId` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`employeeId`,`roleId`),
  KEY `FK_Reference_18` (`roleId`),
  KEY `FK_Reference_34` (`userId`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='”√ªßΩ«…´±Ì';

/*Data for the table `userrole` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

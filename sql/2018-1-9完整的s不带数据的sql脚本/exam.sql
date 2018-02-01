/*
SQLyog Ultimate v8.32 
MySQL - 5.5.40 : Database - exam
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `bigquestion` */

CREATE TABLE `bigquestion` (
  `bigQuertionId` varchar(40) NOT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `bigQuestionName` varchar(2000) DEFAULT NULL,
  `bigQuestionSequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`bigQuertionId`),
  KEY `FK_Reference_51` (`paperId`),
  CONSTRAINT `FK_Reference_51` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���Դ��Ᵽ���';

/*Table structure for table `blacklist` */

CREATE TABLE `blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(40) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `blackIdcard` varchar(18) DEFAULT NULL,
  `temporaryInStatus` varchar(2) DEFAULT NULL COMMENT '是否是临时进入黑名单字段',
  `employeeStatus` varchar(2) DEFAULT NULL COMMENT '员工状态是否是内部员工表中的员工',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_28` (`employeeId`),
  KEY `blackIdcard` (`blackIdcard`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='黑名单表';

/*Table structure for table `breakrules` */

CREATE TABLE `breakrules` (
  `breakId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(40) DEFAULT NULL,
  `BigEmployeeoutId` varchar(40) DEFAULT NULL,
  `breakContent` varchar(200) DEFAULT NULL,
  `breakTime` datetime DEFAULT NULL,
  `minusNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`breakId`),
  KEY `FK_Reference_27` (`employeeId`),
  KEY `FK_Reference_57` (`BigEmployeeoutId`),
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`employeeId`) REFERENCES `employee_out` (`employeeId`),
  CONSTRAINT `FK_Reference_57` FOREIGN KEY (`BigEmployeeoutId`) REFERENCES `haulemployeeout` (`BigEmployeeoutId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='违章记录表';

/*Table structure for table `checkrecord` */

CREATE TABLE `checkrecord` (
  `checkId` varchar(40) NOT NULL,
  `questionId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `checkDate` datetime DEFAULT NULL,
  `checkStatus` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`checkId`),
  KEY `FK_Reference_32` (`questionId`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`questionId`) REFERENCES `questions` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��˼�¼��';

/*Table structure for table `department` */

CREATE TABLE `department` (
  `departmentId` varchar(40) NOT NULL,
  `upDepartmentId` varchar(40) DEFAULT NULL,
  `departmentName` varchar(40) NOT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `sort` varchar(100) DEFAULT NULL,
  `departProjectNames` varchar(1000) DEFAULT NULL COMMENT '长期外来单位的工程信息',
  `departmentType` varchar(2) DEFAULT NULL COMMENT '部门类型0代表内部部门，1代表长期外来部门',
  PRIMARY KEY (`departmentId`),
  KEY `FK_Reference_37` (`upDepartmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�������ű�';

/*Table structure for table `dictionary` */

CREATE TABLE `dictionary` (
  `dictionaryId` varchar(40) NOT NULL,
  `dictionaryName` varchar(50) NOT NULL,
  `upDictionaryId` varchar(40) DEFAULT NULL,
  `isUse` varchar(2) NOT NULL,
  `discription` varchar(200) DEFAULT '无',
  PRIMARY KEY (`dictionaryId`),
  KEY `FK_Reference_49` (`upDictionaryId`),
  CONSTRAINT `FK_Reference_49` FOREIGN KEY (`upDictionaryId`) REFERENCES `dictionary` (`dictionaryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ϵͳ�ֵ��';

/*Table structure for table `employee_in` */

CREATE TABLE `employee_in` (
  `employeeId` varchar(40) NOT NULL,
  `employeeNumber` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `idCode` char(18) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT '''无''',
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `duty` varchar(20) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `finger` varchar(200) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT NULL,
  `trainStatus` int(11) DEFAULT NULL COMMENT '����ͨ�������\n            1����Ҫ����1������\n            2����Ҫ����2������\n            3����Ҫ����3������\n            4����ͨ���볡��ѵ����',
  `sort` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  KEY `FK_Reference_9` (`departmentId`),
  KEY `idCode` (`idCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ְ����';

/*Table structure for table `employee_out` */

CREATE TABLE `employee_out` (
  `employeeId` varchar(40) NOT NULL,
  `employeeNumber` varchar(40) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `idCode` char(18) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `finger` varchar(20) DEFAULT NULL,
  `headaddress` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  KEY `idCode` (`idCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外来职工表';

/*Table structure for table `employeeexam` */

CREATE TABLE `employeeexam` (
  `gradeId` int(11) NOT NULL AUTO_INCREMENT,
  `examId` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `grade` float DEFAULT NULL,
  `examMethod` varchar(5) DEFAULT NULL,
  `employeeType` varchar(2) DEFAULT NULL,
  `unitid` varchar(40) DEFAULT NULL COMMENT '外来单位ID',
  `distributeid` int(11) DEFAULT NULL COMMENT '分配表的主键',
  `bigemployeeoutid` varchar(40) DEFAULT NULL COMMENT '外来大修员工编号',
  PRIMARY KEY (`gradeId`),
  KEY `FK_Reference_53` (`examId`),
  KEY `employeeId` (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8 COMMENT='Ա�����Ա�';

/*Table structure for table `employeeoutdistribute` */

CREATE TABLE `employeeoutdistribute` (
  `distributeid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `bigid` varchar(40) DEFAULT NULL COMMENT '大修id',
  `haulempid` varchar(40) DEFAULT NULL COMMENT '大修员工ID',
  `unitid` varchar(40) DEFAULT NULL COMMENT '单位ID',
  `departmentId` varchar(40) DEFAULT NULL COMMENT '内部部门ID',
  `outempname` varchar(40) DEFAULT NULL COMMENT '员工姓名',
  `empOutIdCard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `empOutexamStatus` varchar(4) DEFAULT NULL COMMENT '考试状态',
  `empOutTrainGrade` varchar(2) DEFAULT NULL COMMENT '培训等级',
  PRIMARY KEY (`distributeid`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

/*Table structure for table `emplyin_breakrules` */

CREATE TABLE `emplyin_breakrules` (
  `empInBreakId` int(11) NOT NULL AUTO_INCREMENT,
  `empInEmployeeId` varchar(40) DEFAULT NULL COMMENT '内部员工表中的员工ID',
  `empInBreakContent` varchar(200) DEFAULT NULL,
  `empInBreakTime` datetime DEFAULT NULL,
  `empInMinusNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`empInBreakId`),
  KEY `FK_Reference_27` (`empInEmployeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='违章记录表';

/*Table structure for table `exam` */

CREATE TABLE `exam` (
  `examId` varchar(40) NOT NULL,
  `examName` varchar(2000) DEFAULT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `traincontent` varchar(1000) DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `xueshi` varchar(40) DEFAULT NULL,
  `bigId` varchar(40) DEFAULT NULL COMMENT '大修编号',
  `bigStatus` varchar(5) DEFAULT NULL COMMENT '大修状态',
  `examLevel` varchar(5) DEFAULT NULL COMMENT '考试等级',
  `employeeName` varchar(40) DEFAULT NULL,
  `employeeNum` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `examType` varchar(8) DEFAULT NULL COMMENT '考试类型(内部考试，外部考试)',
  `AnswerTime` int(11) DEFAULT '0' COMMENT '考试答题时间',
  `departmentId` varchar(40) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����';

/*Table structure for table `exampaper` */

CREATE TABLE `exampaper` (
  `paperId` varchar(40) NOT NULL,
  `paperScore` float DEFAULT NULL,
  `makeTime` datetime DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `paperAnswer` varchar(300) DEFAULT NULL,
  `useTimes` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�Ծ��';

/*Table structure for table `exampaperoption` */

CREATE TABLE `exampaperoption` (
  `optionId` int(11) NOT NULL AUTO_INCREMENT,
  `questionId` varchar(40) DEFAULT NULL,
  `optionContent` varchar(300) DEFAULT NULL,
  `optionSequence` varchar(2) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `isAnswer` varchar(2) DEFAULT NULL COMMENT '是否是答案',
  PRIMARY KEY (`optionId`),
  KEY `FK_Reference_45` (`questionId`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`questionId`) REFERENCES `exampaperquestion` (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=3507 DEFAULT CHARSET=utf8 COMMENT='��ʷ�Ծ�ѡ���';

/*Table structure for table `exampaperquestion` */

CREATE TABLE `exampaperquestion` (
  `questionId` varchar(40) NOT NULL,
  `paperId` varchar(40) DEFAULT NULL,
  `bigQuertionId` varchar(40) DEFAULT NULL,
  `questionContent` varchar(200) NOT NULL,
  `questionSequence` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL,
  `analysis` varchar(2000) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `questionSource` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `FK_Reference_38` (`paperId`),
  KEY `FK_Reference_52` (`bigQuertionId`),
  CONSTRAINT `FK_Reference_38` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`),
  CONSTRAINT `FK_Reference_52` FOREIGN KEY (`bigQuertionId`) REFERENCES `bigquestion` (`bigQuertionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�Ծ���Ŀ��';

/*Table structure for table `haulemployeeout` */

CREATE TABLE `haulemployeeout` (
  `BigEmployeeoutId` varchar(40) NOT NULL,
  `unitId` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `bigId` varchar(40) DEFAULT NULL,
  `empoutIdcard` varchar(18) DEFAULT NULL,
  `trainStatus` varchar(2) DEFAULT NULL,
  `empType` varchar(20) DEFAULT NULL,
  `empOutphone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BigEmployeeoutId`),
  KEY `FK_Reference_58` (`bigId`),
  KEY `empoutIdcard` (`empoutIdcard`),
  KEY `employeeId` (`employeeId`),
  CONSTRAINT `FK_Reference_58` FOREIGN KEY (`bigId`) REFERENCES `haulinfo` (`bigId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `haulinfo` */

CREATE TABLE `haulinfo` (
  `bigId` varchar(40) NOT NULL,
  `bigName` varchar(40) DEFAULT NULL,
  `bigdescription` varchar(200) DEFAULT NULL,
  `bigCreateDate` datetime DEFAULT NULL,
  `bigBeginDate` date DEFAULT NULL,
  `bigEndDate` date DEFAULT NULL,
  `bigStatus` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`bigId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `haulunit` */

CREATE TABLE `haulunit` (
  `unitBigId` varchar(40) NOT NULL,
  `unitId` varchar(40) DEFAULT NULL,
  `bigId` varchar(40) DEFAULT NULL,
  `unitMinisMum` int(11) DEFAULT NULL COMMENT '单位违章记分',
  `isNewBig` varchar(2) DEFAULT NULL,
  `projectNames` varchar(1000) DEFAULT NULL COMMENT '工程名称',
  `manager` varchar(40) DEFAULT NULL,
  `managerPhone` varchar(40) DEFAULT NULL,
  `secure` varchar(40) DEFAULT NULL,
  `securePhone` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`unitBigId`),
  KEY `FK_Reference_61` (`bigId`),
  CONSTRAINT `FK_Reference_61` FOREIGN KEY (`bigId`) REFERENCES `haulinfo` (`bigId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `news` */

CREATE TABLE `news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(40) DEFAULT NULL,
  `newsType` varchar(20) DEFAULT NULL,
  `newsTime` datetime DEFAULT NULL,
  `newsPerson` varchar(20) DEFAULT NULL,
  `newsContent` varchar(20000) DEFAULT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `onlineexamanswerinfor` */

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
  `onlineAnswerExamid` varchar(40) DEFAULT NULL COMMENT '考试ID',
  PRIMARY KEY (`answerInfoId`),
  KEY `FK_Reference_35` (`paperId`),
  KEY `FK_Reference_48` (`questionId`),
  KEY `employeeId` (`employeeId`),
  KEY `onlineAnswerExamid` (`onlineAnswerExamid`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`paperId`) REFERENCES `exampaper` (`paperId`),
  CONSTRAINT `FK_Reference_48` FOREIGN KEY (`questionId`) REFERENCES `exampaperquestion` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���߿��Դ�����ϸ��Ϣ��';

/*Table structure for table `onlineexaminfor` */

CREATE TABLE `onlineexaminfor` (
  `onLineExamId` varchar(40) NOT NULL,
  `examId` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `ipAddress` varchar(50) DEFAULT NULL,
  `examStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`onLineExamId`),
  KEY `FK_Reference_33` (`examId`),
  KEY `FK_Reference_44` (`employeeId`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���߿��Ի�����Ϣ��';

/*Table structure for table `options` */

CREATE TABLE `options` (
  `optionId` varchar(40) NOT NULL,
  `questionId` varchar(40) DEFAULT NULL,
  `optionContent` varchar(2000) DEFAULT NULL,
  `optionWithTag` varchar(2000) DEFAULT NULL,
  `optionSequence` varchar(2) DEFAULT NULL,
  `isAnswer` varchar(2) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`optionId`),
  KEY `FK_Reference_43` (`questionId`),
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`questionId`) REFERENCES `questions` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ѡ���';

/*Table structure for table `permission` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ȩ�ޱ�';

/*Table structure for table `pictureindex` */

CREATE TABLE `pictureindex` (
  `pictureId` varchar(40) NOT NULL,
  `originalName` varchar(50) DEFAULT NULL,
  `currentName` varchar(50) DEFAULT NULL,
  `pictureSize` varchar(10) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pictureId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ͼƬ��';

/*Table structure for table `questionbank` */

CREATE TABLE `questionbank` (
  `questionBankId` varchar(40) NOT NULL,
  `questionBankName` varchar(40) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `employeeName` varchar(40) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isUse` varchar(2) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `categoryNameId` varchar(40) DEFAULT NULL COMMENT '类别名称编号',
  PRIMARY KEY (`questionBankId`),
  KEY `FK_Reference_29` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����';

/*Table structure for table `questions` */

CREATE TABLE `questions` (
  `questionId` varchar(40) NOT NULL,
  `questionBankId` varchar(40) DEFAULT NULL,
  `question` varchar(2000) DEFAULT NULL,
  `questionWithTag` varchar(2000) DEFAULT NULL,
  `answer` varchar(40) DEFAULT NULL,
  `analysisWithTag` varchar(1000) DEFAULT NULL,
  `analysis` varchar(2000) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `emplloyeeName` varchar(40) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `knowledgeType` varchar(40) DEFAULT NULL,
  `hasPicture` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `FK_Reference_30` (`questionBankId`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`questionBankId`) REFERENCES `questionbank` (`questionBankId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ŀ��';

/*Table structure for table `role` */

CREATE TABLE `role` (
  `roleId` varchar(40) NOT NULL,
  `roleName` varchar(40) NOT NULL,
  `roleStatus` varchar(2) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ɫ��';

/*Table structure for table `rolepermission` */

CREATE TABLE `rolepermission` (
  `roleId` varchar(40) NOT NULL,
  `permissionId` varchar(40) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`),
  KEY `FK_Reference_56` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ɫȨ�ޱ�';

/*Table structure for table `traincontent` */

CREATE TABLE `traincontent` (
  `documentId` int(11) NOT NULL AUTO_INCREMENT,
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
  KEY `FK_Reference_59` (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='��ѵ���ϱ�';

/*Table structure for table `unit` */

CREATE TABLE `unit` (
  `unitId` varchar(40) NOT NULL,
  `upUnitId` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `contact` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`unitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外来单位表';

/*Table structure for table `user` */

CREATE TABLE `user` (
  `userId` varchar(40) NOT NULL,
  `userIDCard` varchar(18) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `departmentName` varchar(40) DEFAULT NULL,
  `employeeId` varchar(40) DEFAULT NULL,
  `userPhoto` varchar(40) DEFAULT NULL,
  `isUse` varchar(2) DEFAULT NULL,
  `departmentId` varchar(40) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û���';

/*Table structure for table `userrole` */

CREATE TABLE `userrole` (
  `roleId` varchar(40) NOT NULL,
  `userId` varchar(40) NOT NULL,
  PRIMARY KEY (`roleId`,`userId`),
  KEY `FK_Reference_18` (`roleId`),
  KEY `FK_Reference_34` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�û���ɫ��';

/* Trigger structure for table `breakrules` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `add_unit_after_add_break` AFTER INSERT ON `breakrules` FOR EACH ROW BEGIN
DECLARE big_id VARCHAR(40);
DECLARE unit_id VARCHAR(40);
SET big_id=(SELECT bigId FROM haulemployeeout WHERE BigEmployeeoutId=new.BigEmployeeoutId);
SET unit_id=(SELECT unitId FROM haulemployeeout WHERE BigEmployeeoutId=new.BigEmployeeoutId);
UPDATE haulunit SET unitMinisMum=unitMinisMum+new.minusNum WHERE unitId=unit_id AND bigId=big_id;
END */$$


DELIMITER ;

/* Trigger structure for table `breakrules` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `update_unit_after_update_break` AFTER UPDATE ON `breakrules` FOR EACH ROW BEGIN
DECLARE minus_Num int;
DECLARE big_id VARCHAR(40);
DECLARE unit_id VARCHAR(40);
SET big_id=(SELECT bigId FROM haulemployeeout WHERE BigEmployeeoutId=new.BigEmployeeoutId);
SET unit_id=(SELECT unitId FROM haulemployeeout WHERE BigEmployeeoutId=new.BigEmployeeoutId);
SET minus_Num = (SELECT new.minusNum-old.minusNum);
UPDATE haulunit SET unitMinisMum=unitMinisMum+minus_Num WHERE unitId=unit_id AND bigId=big_id;
END */$$


DELIMITER ;

/* Trigger structure for table `breakrules` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `minus_unit_after_delete_haulemp` AFTER DELETE ON `breakrules` FOR EACH ROW BEGIN
DECLARE big_id VARCHAR(40);
DECLARE unit_id VARCHAR(40);
SET big_id=(SELECT bigId FROM haulemployeeout WHERE BigEmployeeoutId=old.BigEmployeeoutId);
SET unit_id=(SELECT unitId FROM haulemployeeout WHERE BigEmployeeoutId=old.BigEmployeeoutId);
UPDATE haulunit SET unitMinisMum=unitMinisMum-old.minusNum WHERE unitId=unit_id AND bigId=big_id;
END */$$


DELIMITER ;

/* Trigger structure for table `employeeexam` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `minus_empnum_after_delete_employee` AFTER DELETE ON `employeeexam` FOR EACH ROW BEGIN
UPDATE exam SET employeeNum=employeeNum-1 WHERE exam.examId=old.examId;
END */$$


DELIMITER ;

/*!50106 set global event_scheduler = 1*/;

/* Event structure for event `eventEmpInBreaksInfo` */

DELIMITER $$

/*!50106 CREATE DEFINER=`root`@`localhost` EVENT `eventEmpInBreaksInfo` ON SCHEDULE EVERY 1 YEAR STARTS '2018-01-01 01:00:00' ON COMPLETION PRESERVE ENABLE DO CALL clearEmInBreakInfo() */$$
DELIMITER ;

/* Event structure for event `eventUpdateStatus` */

DELIMITER $$

/*!50106 CREATE DEFINER=`root`@`localhost` EVENT `eventUpdateStatus` ON SCHEDULE EVERY 1 SECOND STARTS '2017-11-21 00:12:44' ON COMPLETION PRESERVE ENABLE DO call updateStatus() */$$
DELIMITER ;

/* Procedure structure for procedure `clearEmInBreakInfo` */

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `clearEmInBreakInfo`()
BEGIN
	DELETE FROM blacklist WHERE temporaryInStatus = '0';
END */$$
DELIMITER ;

/* Procedure structure for procedure `clearEmploutBlack` */

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `clearEmploutBlack`()
BEGIN

delete from blacklist where temporaryInStatus='0' and employeeStatus='1' and TIMESTAMPDIFF(YEAR,time,NOW())=1;

END */$$
DELIMITER ;

/* Procedure structure for procedure `updateDepartmentIds` */

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateDepartmentIds`(IN oldDepartmentId VARCHAR(40),IN upDepartmentIdNew VARCHAR(40))
BEGIN	
			UPDATE department SET departmentId= REPLACE(departmentId,oldDepartmentId ,upDepartmentIdNew  ),updepartmentId= REPLACE(updepartmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%') AND departmentId != oldDepartmentId;
			UPDATE employee_in SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
			UPDATE exam SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
			UPDATE exampaper SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
			UPDATE questionbank SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
			UPDATE traincontent SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
			UPDATE `user` SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ),departmentName = (SELECT departmentName FROM department WHERE departmentId = `user`.departmentId) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
			UPDATE role SET departmentId = REPLACE(departmentId,oldDepartmentId,upDepartmentIdNew  ) WHERE departmentid LIKE CONCAT(oldDepartmentId,'%');
		END */$$
DELIMITER ;

/* Procedure structure for procedure `updateStatus` */

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStatus`()
BEGIN
	UPDATE exam SET `status`="已结束" WHERE  `status` != "已结束" AND  (NOW() - endTime)>0  ;
	UPDATE exam  SET `status`="正在答题"  WHERE   `status` != "已结束" AND ( NOW() - startTime)>=0 
	AND  (NOW() - endTime)<=0;
	
	UPDATE haulinfo SET bigStatus="已结束" WHERE  bigStatus != "已结束" AND  (CURDATE() - bigEndDate)>0; 
	UPDATE haulinfo SET bigStatus="进行中" WHERE  (CURDATE() - bigEndDate)<=0 
	AND  (CURDATE() - bigBeginDate)>=0;
	
	UPDATE exam SET bigStatus=(SELECT bigStatus FROM haulinfo WHERE bigId=exam.bigId);
END */$$
DELIMITER ;

/*Table structure for table `employee_exam_grade` */

DROP TABLE IF EXISTS `employee_exam_grade`;

/*!50001 CREATE TABLE  `employee_exam_grade`(
 `examId` varchar(40) ,
 `examName` varchar(2000) ,
 `startTime` datetime ,
 `endTime` datetime ,
 `status` varchar(40) ,
 `paperId` varchar(40) ,
 `bigStatus` varchar(5) ,
 `bigId` varchar(40) ,
 `departmentId` varchar(40) ,
 `level` varchar(10) ,
 `paperScore` float ,
 `employeeId` varchar(40) ,
 `employeeName` varchar(40) ,
 `employeeType` varchar(2) ,
 `examMethod` varchar(5) ,
 `grade` float ,
 `isPass` varchar(1) ,
 `sex` varchar(2) ,
 `idCode` varchar(18) ,
 `answerStatus` varchar(20) ,
 `departmentName` varchar(40) 
)*/;

/*Table structure for table `employee_out_base_info` */

DROP TABLE IF EXISTS `employee_out_base_info`;

/*!50001 CREATE TABLE  `employee_out_base_info`(
 `name` varchar(10) ,
 `idCard` char(18) ,
 `sex` varchar(2) ,
 `address` varchar(50) ,
 `birthday` date ,
 `photo` varchar(200) ,
 `BigEmployeeoutId` varchar(40) ,
 `unitId` varchar(40) ,
 `employeeId` varchar(40) ,
 `bigId` varchar(40) ,
 `empoutIdcard` varchar(18) ,
 `trainStatus` varchar(2) ,
 `empType` varchar(20) ,
 `empOutphone` varchar(20) ,
 `bigBeginDate` date ,
 `bigCreateDate` datetime ,
 `bigdescription` varchar(200) ,
 `bigEndDate` date ,
 `bigName` varchar(40) ,
 `bigStatus` varchar(4) ,
 `departmentName` varchar(40) ,
 `minusNum` decimal(32,0) ,
 `minusNumSum` decimal(32,0) ,
 `isblackList` varchar(1) ,
 `isInBlackList` varchar(1) 
)*/;

/*Table structure for table `exam_employeeexam_exampaper` */

DROP TABLE IF EXISTS `exam_employeeexam_exampaper`;

/*!50001 CREATE TABLE  `exam_employeeexam_exampaper`(
 `examId` varchar(40) ,
 `examName` varchar(2000) ,
 `paperId` varchar(40) ,
 `startTime` datetime ,
 `endTime` datetime ,
 `traincontent` varchar(1000) ,
 `status` varchar(40) ,
 `xueshi` varchar(40) ,
 `employeeName` varchar(40) ,
 `bigStatus` varchar(5) ,
 `departmentId` varchar(40) ,
 `examType` varchar(8) ,
 `paperanswer` varchar(300) ,
 `description` varchar(200) ,
 `createPaperName` varchar(40) ,
 `level` varchar(10) ,
 `makeTime` datetime ,
 `paperScore` float ,
 `title` varchar(100) ,
 `useTimes` int(11) ,
 `sumPerson` bigint(21) ,
 `countPassPerson` bigint(21) 
)*/;

/*Table structure for table `online_exam_employee_info` */

DROP TABLE IF EXISTS `online_exam_employee_info`;

/*!50001 CREATE TABLE  `online_exam_employee_info`(
 `examId` varchar(40) ,
 `examName` varchar(2000) ,
 `startTime` datetime ,
 `endTime` datetime ,
 `status` varchar(40) ,
 `paperId` varchar(40) ,
 `AnswerTime` int(11) ,
 `level` varchar(10) ,
 `paperScore` float ,
 `employeeId` varchar(40) ,
 `employeeName` varchar(40) ,
 `employeeType` varchar(2) ,
 `examMethod` varchar(5) ,
 `grade` float ,
 `isPass` varchar(1) ,
 `sex` varchar(2) ,
 `idCode` varchar(18) ,
 `departmentName` varchar(40) ,
 `singleSum` double ,
 `singleScore` double ,
 `multipleSum` double ,
 `multipleScore` double ,
 `trueOrFalseSum` double ,
 `trueOrFalseScore` double ,
 `startAnswerTime` datetime ,
 `endAnswerTime` datetime ,
 `loginTime` datetime ,
 `ipAddress` varchar(50) ,
 `AnswerStatus` varchar(20) 
)*/;

/*Table structure for table `overhaul_unit` */

DROP TABLE IF EXISTS `overhaul_unit`;

/*!50001 CREATE TABLE  `overhaul_unit`(
 `id` varchar(40) ,
 `upid` varchar(40) ,
 `name` varchar(40) ,
 `bigCreateDate` datetime 
)*/;

/*Table structure for table `questionbank_questions_department` */

DROP TABLE IF EXISTS `questionbank_questions_department`;

/*!50001 CREATE TABLE  `questionbank_questions_department`(
 `questionBankId` varchar(40) ,
 `questionBankName` varchar(40) ,
 `departmentId` varchar(40) ,
 `employeeName` varchar(40) ,
 `createTime` datetime ,
 `isUse` varchar(2) ,
 `description` varchar(200) ,
 `categoryNameId` varchar(40) ,
 `typeName` varchar(50) ,
 `upTypeId` varchar(40) ,
 `departmentName` varchar(40) ,
 `sumQuestions` bigint(21) ,
 `countSingle` decimal(23,0) ,
 `countMultiple` decimal(23,0) ,
 `countTrueOrFalse` decimal(23,0) 
)*/;

/*View structure for view employee_exam_grade */

/*!50001 DROP TABLE IF EXISTS `employee_exam_grade` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `employee_exam_grade` AS select `exam`.`examId` AS `examId`,`exam`.`examName` AS `examName`,`exam`.`startTime` AS `startTime`,`exam`.`endTime` AS `endTime`,`exam`.`status` AS `status`,`exam`.`paperId` AS `paperId`,`exam`.`bigStatus` AS `bigStatus`,`exam`.`bigId` AS `bigId`,`exam`.`departmentId` AS `departmentId`,`exampaper`.`level` AS `level`,`exampaper`.`paperScore` AS `paperScore`,`employeeexam`.`employeeId` AS `employeeId`,`employeeexam`.`employeeName` AS `employeeName`,`employeeexam`.`employeeType` AS `employeeType`,`employeeexam`.`examMethod` AS `examMethod`,`employeeexam`.`grade` AS `grade`,(case (`employeeexam`.`grade` >= (`exampaper`.`paperScore` * 0.9)) when 1 then '是' else '否' end) AS `isPass`,(case `employeeexam`.`employeeType` when '0' then (select `employee_in`.`sex` from `employee_in` where (`employee_in`.`idCode` = `employeeexam`.`employeeId`)) else (select `employee_out`.`sex` from `employee_out` where (`employee_out`.`idCode` = `employeeexam`.`employeeId`)) end) AS `sex`,(case `employeeexam`.`employeeType` when '0' then (select `employee_in`.`idCode` from `employee_in` where (`employee_in`.`idCode` = `employeeexam`.`employeeId`)) else (select `employee_out`.`idCode` from `employee_out` where (`employee_out`.`idCode` = `employeeexam`.`employeeId`)) end) AS `idCode`,(case `employeeexam`.`employeeType` when '0' then (case (`exam`.`status` = '已结束') when 1 then '已交卷' else (select `onlineexaminfor`.`examStatus` from `onlineexaminfor` where ((`onlineexaminfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexaminfor`.`examId` = `employeeexam`.`examId`))) end) else '已交卷' end) AS `answerStatus`,(case `employeeexam`.`employeeType` when '0' then (select `department`.`departmentName` from (`department` join `employee_in`) where ((`employee_in`.`idCode` = `employeeexam`.`employeeId`) and (`employee_in`.`departmentId` = `department`.`departmentId`))) else (select `unit`.`name` from `unit` where (`unit`.`unitId` = `employeeexam`.`unitid`)) end) AS `departmentName` from ((`exam` join `exampaper`) join `employeeexam`) where ((`exam`.`examId` = `employeeexam`.`examId`) and (`exam`.`paperId` = `exampaper`.`paperId`)) */;

/*View structure for view employee_out_base_info */

/*!50001 DROP TABLE IF EXISTS `employee_out_base_info` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `employee_out_base_info` AS select `employee_out`.`name` AS `name`,`employee_out`.`idCode` AS `idCard`,`employee_out`.`sex` AS `sex`,`employee_out`.`address` AS `address`,`employee_out`.`birthday` AS `birthday`,`employee_out`.`headaddress` AS `photo`,`haulemployeeout`.`BigEmployeeoutId` AS `BigEmployeeoutId`,`haulemployeeout`.`unitId` AS `unitId`,`haulemployeeout`.`employeeId` AS `employeeId`,`haulemployeeout`.`bigId` AS `bigId`,`haulemployeeout`.`empoutIdcard` AS `empoutIdcard`,`haulemployeeout`.`trainStatus` AS `trainStatus`,`haulemployeeout`.`empType` AS `empType`,`haulemployeeout`.`empOutphone` AS `empOutphone`,`haulinfo`.`bigBeginDate` AS `bigBeginDate`,`haulinfo`.`bigCreateDate` AS `bigCreateDate`,`haulinfo`.`bigdescription` AS `bigdescription`,`haulinfo`.`bigEndDate` AS `bigEndDate`,`haulinfo`.`bigName` AS `bigName`,`haulinfo`.`bigStatus` AS `bigStatus`,(select `unit`.`name` from `unit` where (`unit`.`unitId` = `haulemployeeout`.`unitId`)) AS `departmentName`,ifnull((select sum(`breakrules`.`minusNum`) from `breakrules` where ((`breakrules`.`employeeId` = `haulemployeeout`.`employeeId`) and (`breakrules`.`breakTime` like concat(year(curdate()),'%')))),0) AS `minusNum`,ifnull((select sum(`breakrules`.`minusNum`) from `breakrules` where (`breakrules`.`employeeId` = `haulemployeeout`.`employeeId`)),0) AS `minusNumSum`,(case isnull((select `blacklist`.`id` from `blacklist` where ((`blacklist`.`blackIdcard` = `haulemployeeout`.`empoutIdcard`) and (`blacklist`.`temporaryInStatus` = 1) and (`blacklist`.`employeeStatus` = '1')))) when 1 then '否' else '是' end) AS `isblackList`,(case isnull((select `blacklist`.`id` from `blacklist` where ((`blacklist`.`blackIdcard` = `haulemployeeout`.`empoutIdcard`) and (`blacklist`.`employeeStatus` = '1')))) when 1 then '否' else '是' end) AS `isInBlackList` from ((`employee_out` join `haulemployeeout`) join `haulinfo`) where ((`employee_out`.`employeeId` = `haulemployeeout`.`employeeId`) and (`haulemployeeout`.`bigId` = `haulinfo`.`bigId`)) */;

/*View structure for view exam_employeeexam_exampaper */

/*!50001 DROP TABLE IF EXISTS `exam_employeeexam_exampaper` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `exam_employeeexam_exampaper` AS select `exam`.`examId` AS `examId`,`exam`.`examName` AS `examName`,`exam`.`paperId` AS `paperId`,`exam`.`startTime` AS `startTime`,`exam`.`endTime` AS `endTime`,`exam`.`traincontent` AS `traincontent`,`exam`.`status` AS `status`,`exam`.`xueshi` AS `xueshi`,`exam`.`employeeName` AS `employeeName`,`exam`.`bigStatus` AS `bigStatus`,`exam`.`departmentId` AS `departmentId`,`exam`.`examType` AS `examType`,`exampaper`.`paperAnswer` AS `paperanswer`,`exampaper`.`description` AS `description`,`exampaper`.`employeeName` AS `createPaperName`,`exampaper`.`level` AS `level`,`exampaper`.`makeTime` AS `makeTime`,`exampaper`.`paperScore` AS `paperScore`,`exampaper`.`title` AS `title`,`exampaper`.`useTimes` AS `useTimes`,(select count(`employeeexam`.`employeeId`) from `employeeexam` where (`exam`.`examId` = `employeeexam`.`examId`)) AS `sumPerson`,(select count(`employeeexam`.`employeeId`) from `employeeexam` where ((`exam`.`examId` = `employeeexam`.`examId`) and (`employeeexam`.`grade` >= (`exampaper`.`paperScore` * 0.9)))) AS `countPassPerson` from (`exam` join `exampaper`) where (`exam`.`paperId` = `exampaper`.`paperId`) */;

/*View structure for view online_exam_employee_info */

/*!50001 DROP TABLE IF EXISTS `online_exam_employee_info` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `online_exam_employee_info` AS select `exam`.`examId` AS `examId`,`exam`.`examName` AS `examName`,`exam`.`startTime` AS `startTime`,`exam`.`endTime` AS `endTime`,`exam`.`status` AS `status`,`exam`.`paperId` AS `paperId`,`exam`.`AnswerTime` AS `AnswerTime`,`exampaper`.`level` AS `level`,`exampaper`.`paperScore` AS `paperScore`,`employeeexam`.`employeeId` AS `employeeId`,`employeeexam`.`employeeName` AS `employeeName`,`employeeexam`.`employeeType` AS `employeeType`,`employeeexam`.`examMethod` AS `examMethod`,`employeeexam`.`grade` AS `grade`,(case (`employeeexam`.`grade` >= (`exampaper`.`paperScore` * 0.9)) when 1 then '是' else '否' end) AS `isPass`,(select `employee_in`.`sex` from `employee_in` where (`employee_in`.`idCode` = `employeeexam`.`employeeId`)) AS `sex`,(select `employee_in`.`idCode` from `employee_in` where (`employee_in`.`idCode` = `employeeexam`.`employeeId`)) AS `idCode`,(select `department`.`departmentName` from (`department` join `employee_in`) where ((`employee_in`.`idCode` = `employeeexam`.`employeeId`) and (`employee_in`.`departmentId` = `department`.`departmentId`))) AS `departmentName`,(select sum(`onlineexamanswerinfor`.`questionScore`) from `onlineexamanswerinfor` where ((`onlineexamanswerinfor`.`questionType` = '单选题') and (`onlineexamanswerinfor`.`paperId` = `exam`.`paperId`) and (`onlineexamanswerinfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexamanswerinfor`.`onlineAnswerExamid` = `exam`.`examId`))) AS `singleSum`,(select sum(`onlineexamanswerinfor`.`score`) from `onlineexamanswerinfor` where ((`onlineexamanswerinfor`.`questionType` = '单选题') and (`onlineexamanswerinfor`.`paperId` = `exam`.`paperId`) and (`onlineexamanswerinfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexamanswerinfor`.`onlineAnswerExamid` = `exam`.`examId`))) AS `singleScore`,(select sum(`onlineexamanswerinfor`.`questionScore`) from `onlineexamanswerinfor` where ((`onlineexamanswerinfor`.`questionType` = '多选题') and (`onlineexamanswerinfor`.`paperId` = `exam`.`paperId`) and (`onlineexamanswerinfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexamanswerinfor`.`onlineAnswerExamid` = `exam`.`examId`))) AS `multipleSum`,(select sum(`onlineexamanswerinfor`.`score`) from `onlineexamanswerinfor` where ((`onlineexamanswerinfor`.`questionType` = '多选题') and (`onlineexamanswerinfor`.`paperId` = `exam`.`paperId`) and (`onlineexamanswerinfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexamanswerinfor`.`onlineAnswerExamid` = `exam`.`examId`))) AS `multipleScore`,(select sum(`onlineexamanswerinfor`.`questionScore`) from `onlineexamanswerinfor` where ((`onlineexamanswerinfor`.`questionType` = '判断题') and (`onlineexamanswerinfor`.`paperId` = `exam`.`paperId`) and (`onlineexamanswerinfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexamanswerinfor`.`onlineAnswerExamid` = `exam`.`examId`))) AS `trueOrFalseSum`,(select sum(`onlineexamanswerinfor`.`score`) from `onlineexamanswerinfor` where ((`onlineexamanswerinfor`.`questionType` = '判断题') and (`onlineexamanswerinfor`.`paperId` = `exam`.`paperId`) and (`onlineexamanswerinfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexamanswerinfor`.`onlineAnswerExamid` = `exam`.`examId`))) AS `trueOrFalseScore`,(select `onlineexaminfor`.`startTime` from `onlineexaminfor` where ((`onlineexaminfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexaminfor`.`examId` = `employeeexam`.`examId`))) AS `startAnswerTime`,(select `onlineexaminfor`.`endTime` from `onlineexaminfor` where ((`onlineexaminfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexaminfor`.`examId` = `employeeexam`.`examId`))) AS `endAnswerTime`,(select `onlineexaminfor`.`loginTime` from `onlineexaminfor` where ((`onlineexaminfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexaminfor`.`examId` = `employeeexam`.`examId`))) AS `loginTime`,(select `onlineexaminfor`.`ipAddress` from `onlineexaminfor` where ((`onlineexaminfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexaminfor`.`examId` = `employeeexam`.`examId`))) AS `ipAddress`,(select `onlineexaminfor`.`examStatus` from `onlineexaminfor` where ((`onlineexaminfor`.`employeeId` = `employeeexam`.`employeeId`) and (`onlineexaminfor`.`examId` = `employeeexam`.`examId`))) AS `AnswerStatus` from ((`exam` join `exampaper`) join `employeeexam`) where ((`exam`.`examId` = `employeeexam`.`examId`) and (`exam`.`paperId` = `exampaper`.`paperId`) and (`employeeexam`.`employeeType` = 0)) */;

/*View structure for view overhaul_unit */

/*!50001 DROP TABLE IF EXISTS `overhaul_unit` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `overhaul_unit` AS select `haulinfo`.`bigId` AS `id`,(case when 1 then 1 else 1 end) AS `upid`,`haulinfo`.`bigName` AS `name`,`haulinfo`.`bigCreateDate` AS `bigCreateDate` from `haulinfo` union select `haulunit`.`unitId` AS `id`,`haulunit`.`bigId` AS `upid`,(select `unit`.`name` from `unit` where (`unit`.`unitId` = `haulunit`.`unitId`)) AS `name`,(select `haulinfo`.`bigCreateDate` from `haulinfo` where (`haulinfo`.`bigId` = `haulunit`.`bigId`)) AS `bigCreateDate` from `haulunit` order by `bigCreateDate` desc */;

/*View structure for view questionbank_questions_department */

/*!50001 DROP TABLE IF EXISTS `questionbank_questions_department` */;
/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `questionbank_questions_department` AS select `questionbank`.`questionBankId` AS `questionBankId`,`questionbank`.`questionBankName` AS `questionBankName`,`questionbank`.`departmentId` AS `departmentId`,`questionbank`.`employeeName` AS `employeeName`,`questionbank`.`createTime` AS `createTime`,`questionbank`.`isUse` AS `isUse`,`questionbank`.`description` AS `description`,`questionbank`.`categoryNameId` AS `categoryNameId`,(select `dictionary`.`dictionaryName` from `dictionary` where (`dictionary`.`dictionaryId` = `questionbank`.`categoryNameId`)) AS `typeName`,(select `dictionary`.`upDictionaryId` from `dictionary` where (`dictionary`.`dictionaryId` = `questionbank`.`categoryNameId`)) AS `upTypeId`,(select `department`.`departmentName` from `department` where (`questionbank`.`departmentId` = `department`.`departmentId`)) AS `departmentName`,count(`questions`.`questionId`) AS `sumQuestions`,sum((case `questions`.`type` when '单选题' then 1 else 0 end)) AS `countSingle`,sum((case `questions`.`type` when '多选题' then 1 else 0 end)) AS `countMultiple`,sum((case `questions`.`type` when '判断题' then 1 else 0 end)) AS `countTrueOrFalse` from (`questionbank` left join `questions` on((`questionbank`.`questionBankId` = `questions`.`questionBankId`))) group by `questionbank`.`questionBankId` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

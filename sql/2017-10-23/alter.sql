/**
 * 更改考试表增加考试人数字段
 */
ALTER TABLE `exam`.`exam`     ADD COLUMN `employeeNum` INT NULL AFTER `employeeName`;
/**
 * 更改考试表增加考试创建时间
 */
ALTER TABLE `exam`.`exam`     ADD COLUMN `createTime` DATETIME NULL AFTER `employeeNum`;
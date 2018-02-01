/**
 * 修改成绩表的主键为int自增型
 */
ALTER TABLE `exam`.`employeeexam`     CHANGE `gradeId` `gradeId` INT NOT NULL AUTO_INCREMENT;
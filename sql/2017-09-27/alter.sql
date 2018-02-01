/*将非空验证去掉*/
ALTER TABLE `exam`.`exampaper`     CHANGE `makeTime` `makeTime` DATETIME NULL ,     CHANGE `level` `level` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;
/*将试卷选项的ID设为int型，且自增*/
ALTER TABLE `exam`.`exampaperoption`     CHANGE `optionId` `optionId` INT NOT NULL AUTO_INCREMENT;
/**给试卷选项表增加是否是答案字段***/
ALTER TABLE `exam`.`exampaperoption`     ADD COLUMN `isAnswer` VARCHAR(2) NULL COMMENT '是否是答案' AFTER `description`;
/*字典表上级编号可以为空*/
ALTER TABLE `exam`.`dictionary`     CHANGE `upDictionaryId` `upDictionaryId` VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;
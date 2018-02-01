/**
 * 更改试卷选项表的非空验证
 */
ALTER TABLE `exam`.`exampaperoption`     CHANGE `optionContent` `optionContent` VARCHAR(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,     CHANGE `optionSequence` `optionSequence` VARCHAR(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;
/**
 * 更改试卷表(将试卷表的答案字段修改，与题的答案重名)
 */
ALTER TABLE `exam`.`exampaper`     CHANGE `answer` `paperAnswer` VARCHAR(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;
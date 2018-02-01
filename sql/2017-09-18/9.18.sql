/**
 * 更改外部部门表的非空限制
 */
ALTER TABLE `exam`.`unit`     CHANGE `name` `name` VARCHAR(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,     CHANGE `contact` `contact` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,     CHANGE `phone` `phone` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ;
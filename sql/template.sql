-- 数据库初始化

-- 创建数据库
-- create database if not exists "play" default character set utf8mb4 collate utf8mb4_unicode_ci;
create database if not exists `test` default character set utf8mb4 collate utf8mb4_general_ci;
use `test`;

-- 取消外键约束
-- SET FOREIGN_KEY_CHECKS=0;

-- 注意：每个自定义的名称，包括字段名，索引名，都要加上`` 这个特殊的引号，在键盘的左上角。
-- 否则如果和其内置的关键字冲突的话（比如desc），可能会产生错误

-- 建表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名（登录用，区分大小写）',
  `nickname` VARCHAR(20) DEFAULT 'animer' COMMENT '昵称',
  `password` VARCHAR(50) NOT NULL COMMENT '密码',
  `age` INT COMMENT '年龄',
  `gender` INT DEFAULT 0 COMMENT '性别（0：男；1：女）',
  `id_card_no` VARCHAR (30) COMMENT '身份证号',
  `detail_id` INT COMMENT '详情表关联id',
  `desc` VARCHAR (200) COMMENT '描述',
  -- 定义时间的毫秒数：3位（最大是6位，保存到微妙），小数点位数大于0后，就无法指定默认值了CURRENT_TIMESTAMP了
  `create_time` DATETIME(3) /*DEFAULT CURRENT_TIMESTAMP*/ COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

  -- 主键
  PRIMARY KEY (`id`),
  -- 唯一索引
  UNIQUE KEY `uni_index_id_card_no` (`id_card_no`)USING BTREE,
  -- 普通索引
  KEY `index_nickname` (`nickname`) USING BTREE,
  -- 普通联合索引
  KEY `index_username_nickname` (`username`, `nickname`) USING BTREE,
  -- hash索引，InnoDB引擎中，无法人工创建hash索引，只有运行时的自适应hash索引
  -- KEY `hash_index_username` (`username`) USING HASH,
  -- 全文索引，无法指定使用btree或者hash
  FULLTEXT KEY `full_text_desc` (`desc`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
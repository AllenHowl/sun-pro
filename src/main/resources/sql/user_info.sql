CREATE TABLE `user_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(255) NOT NULL COMMENT '用户昵称',
  `sex` smallint(2) NOT NULL COMMENT '性别（0-男， 1-女）',
  `birth_day` datetime(6) NOT NULL COMMENT '出生年月日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
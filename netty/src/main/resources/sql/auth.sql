-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `nick_name` varchar(200) NOT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否注销 0->否；1->是',
  `type` int(1) DEFAULT 0 COMMENT '用户类型 0 用户；1 客服',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com' , '张三', '用户', '2021-05-13 14:35:29', NULL, 1 ,0 ,0);
INSERT INTO `sys_user` VALUES ('2', 'test', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', '李四', '用户', '2021-12-29 17:34:14', NULL, 1, 0 ,0);
INSERT INTO `sys_user` VALUES ('3', 'test1', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test1', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('4', 'test2', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test2', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('5', 'test3', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test3', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('6', 'test4', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test4', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('7', 'test5', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test5', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('8', 'test6', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test6', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('9', 'test7', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test7', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('10', 'test8', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test8', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('11', 'test9', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test9', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('12', 'test10', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test10', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('13', 'test11', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test11', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('14', 'test12', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test12', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);
INSERT INTO `sys_user` VALUES ('15', 'test13', '123456', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'test13', '客服', '2021-12-29 17:34:14', NULL, 1, 0 ,1);


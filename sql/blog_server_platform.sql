CREATE DATABASE  IF NOT EXISTS `selab-blog` ;
USE `selab-blog`;



DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `user_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，用户id',
                         `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
                         `password` varchar(128) NOT NULL COMMENT '密码',
                         `phone` varchar(11)  NOT NULL COMMENT '手机号码',
                         `email` varchar(20)   NOT NULL COMMENT '邮件',
                         `sex` int UNSIGNED NULL DEFAULT 0 COMMENT '性别，1为男，0为女',
                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `status` int DEFAULT '1' COMMENT '是否启用，1启用，0禁用',
                         PRIMARY KEY (`user_id`)
) COMMENT '用户表';


DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
                           `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键,文章id',
                           `title` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '主题',
                           `content` varchar(500)  CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL COMMENT '文章内容',
                           `user_id` bigint NOT NULL COMMENT '用户名',
                           `image` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
                           `publish_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                           `article_like` int DEFAULT 0 COMMENT '点赞数',
                           PRIMARY KEY (`article_id`)
) COMMENT='文章';

DROP TABLE IF EXISTS `behaviour`;
CREATE TABLE `behaviour` (
                             `behaviour_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `article_id` bigint UNSIGNED NOT NULL COMMENT '文章id',
                             `user_id`  bigint UNSIGNED NOT NULL COMMENT '评论创建人',
                             `is_like` int  DEFAULT 0 COMMENT '是否点赞，0为否，1为是',
                             PRIMARY KEY (`behaviour_id`)
) COMMENT='用户行为表';

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                           `comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id',
                           `article_id` bigint UNSIGNED NOT NULL COMMENT '文章id',
                           `user_id` bigint UNSIGNED NOT NULL  COMMENT '用户id',
                           `comment_body` varchar(500) CHARACTER SET utf8mb4 NOT NULL COMMENT '评论内容',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`comment_id`)
) COMMENT='评论表';
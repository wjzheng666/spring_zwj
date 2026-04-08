CREATE DATABASE IF NOT EXISTS `springboot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `springboot`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                        `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                        `password` VARCHAR(100) NOT NULL COMMENT '密码（BCrypt加密）',
                        `age` INT COMMENT '年龄',
                        `email` VARCHAR(100) COMMENT '邮箱',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
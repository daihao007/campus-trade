CREATE DATABASE IF NOT EXISTS campus_trade DEFAULT CHARACTER SET utf8mb4;
USE campus_trade;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码',
    `nickname` VARCHAR(50) NOT NULL COMMENT '昵称',
    `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `role` TINYINT DEFAULT 0 COMMENT '角色：0普通用户，1管理员',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP 
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='用户表';

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '分类ID',
    `name` VARCHAR(50) NOT NULL UNIQUE COMMENT '分类名称',
    `sort` INT DEFAULT 0 COMMENT '排序',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='商品分类表';


DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    `title` VARCHAR(100) NOT NULL COMMENT '商品标题',
    `description` TEXT COMMENT '商品描述',
    `price` DECIMAL(10, 2) NOT NULL COMMENT '商品价格',
    `cover_image` VARCHAR(255) DEFAULT NULL COMMENT '商品封面图片',
    `category_id` BIGINT NOT NULL COMMENT '分类ID',
    `seller_id` BIGINT NOT NULL COMMENT '卖家用户ID',
    `status` TINYINT DEFAULT 0 COMMENT '商品状态：0在售，1已售，2下架',
    `view_count` INT DEFAULT 0 COMMENT '浏览次数',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `fk_product_category`
        FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
    CONSTRAINT `fk_product_seller`
        FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) COMMENT='商品表';


DROP TABLE IF EXISTS `favorite`;

CREATE TABLE `favorite` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '收藏ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `product_id` BIGINT NOT NULL COMMENT '商品ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
    CONSTRAINT `fk_favorite_user`
        FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_favorite_product`
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
    UNIQUE KEY `uk_user_product` (`user_id`, `product_id`)
) COMMENT='商品收藏表';

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '留言ID',
    `product_id` BIGINT NOT NULL COMMENT '商品ID',
    `user_id` BIGINT NOT NULL COMMENT '留言用户ID',
    `content` TEXT NOT NULL COMMENT '留言内容',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `fk_message_product`
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_message_user`
        FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) COMMENT='商品留言表';

INSERT INTO `category` (`name`, `sort`) VALUES
('电子产品', 1),
('书籍教材', 2),
('生活用品', 3),
('服装鞋帽', 4),
('运动器材', 5),
('数码配件', 6),
('其他', 7);
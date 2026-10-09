DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `customer_name` varchar(255) NOT NULL COMMENT '客户名称',
    `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `address` varchar(500) DEFAULT NULL COMMENT '地址',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `customer` (`customer_name`, `phone`, `email`, `address`) VALUES
('张三', '13900001001', 'zhangsan@example.com', '上海市浦东新区'),
('李四', '13900001002', 'lisi@example.com', '北京市朝阳区'),
('王五', '13900001003', 'wangwu@example.com', '广州市天河区'),
('赵六', '13900001004', 'zhaoliu@example.com', '深圳市南山区'),
('陈七', '13900001005', 'chenqi@example.com', '杭州市西湖区'),
('京东商城', '400-100-0001', 'jd@jd.com', '北京市亦庄'),
('天猫旗舰店', '400-200-0001', 'tmall@tmall.com', '杭州市余杭区');
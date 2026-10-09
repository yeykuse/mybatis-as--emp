DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `product_name` varchar(255) NOT NULL COMMENT '产品名称',
    `product_code` varchar(100) NOT NULL COMMENT '产品编码',
    `description` text COMMENT '产品描述',
    `price` decimal(10,2) DEFAULT NULL COMMENT '参考价格',
    `status` tinyint(1) DEFAULT 1 COMMENT '状态 1:上架 0:下架',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product` (`product_name`, `product_code`, `description`, `price`) VALUES
('智能手机Pro', 'PROD-001', '旗舰智能手机，6.7英寸屏幕', 4999.00),
('智能手机标准版', 'PROD-002', '标准版智能手机，6.1英寸屏幕', 3999.00),
('蓝牙耳机', 'PROD-003', '主动降噪蓝牙耳机', 899.00),
('笔记本电脑Air', 'PROD-004', '轻薄笔记本电脑，14英寸', 6999.00),
('平板电脑', 'PROD-005', '10.9英寸平板电脑', 3499.00),
('智能手表', 'PROD-006', '运动健康智能手表', 1999.00),
('无线充电器', 'PROD-007', '15W快速无线充电器', 199.00),
('机械键盘', 'PROD-008', 'RGB机械键盘，87键', 599.00),
('显示器27寸', 'PROD-009', '27英寸4K显示器', 2999.00),
('移动电源', 'PROD-010', '20000mAh大容量移动电源', 299.00);
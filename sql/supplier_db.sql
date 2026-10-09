DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `supplier_name` varchar(255) NOT NULL COMMENT '供应商名称',
    `contact_name` varchar(100) DEFAULT NULL COMMENT '联系人',
    `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
    `address` varchar(500) DEFAULT NULL COMMENT '地址',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `supplier` (`supplier_name`, `contact_name`, `phone`, `address`) VALUES
('华为技术', '赵经理', '13800001001', '深圳市龙岗区'),
('小米科技', '钱经理', '13800001002', '北京市海淀区'),
('三星电子', '孙经理', '13800001003', '韩国首尔'),
('索尼集团', '李经理', '13800001004', '日本东京'),
('联想集团', '周经理', '13800001005', '北京市海淀区');
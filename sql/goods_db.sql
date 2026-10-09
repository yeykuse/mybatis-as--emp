DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
    `sku` varchar(100) NOT NULL COMMENT 'SKU编码',
    `specification` varchar(255) DEFAULT NULL COMMENT '规格描述',
    `stock` int(11) DEFAULT 0 COMMENT '库存数量',
    `price` decimal(10,2) DEFAULT NULL COMMENT '售价',
    `status` tinyint(1) DEFAULT 1 COMMENT '状态 1:在售 0:停售',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sku` (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `goods` (`goods_name`, `sku`, `specification`, `stock`, `price`) VALUES
('智能手机Pro-曜石黑-128G', 'SKU-001', '曜石黑/128GB', 500, 4999.00),
('智能手机Pro-曜石黑-256G', 'SKU-002', '曜石黑/256GB', 300, 5499.00),
('智能手机Pro-星光白-128G', 'SKU-003', '星光白/128GB', 400, 4999.00),
('智能手机标准版-午夜蓝-128G', 'SKU-004', '午夜蓝/128GB', 600, 3999.00),
('智能手机标准版-午夜蓝-256G', 'SKU-005', '午夜蓝/256GB', 350, 4499.00),
('蓝牙耳机-白色', 'SKU-006', '白色/标准版', 1000, 899.00),
('蓝牙耳机-黑色', 'SKU-007', '黑色/标准版', 800, 899.00),
('蓝牙耳机Pro-银色', 'SKU-008', '银色/高配版', 500, 1299.00),
('笔记本电脑Air-银色-16G', 'SKU-009', '银色/16GB/512GB', 200, 6999.00),
('笔记本电脑Air-深空灰-16G', 'SKU-010', '深空灰/16GB/512GB', 150, 6999.00),
('笔记本电脑Air-深空灰-32G', 'SKU-011', '深空灰/32GB/1TB', 100, 8999.00),
('平板电脑-WiFi版-64G', 'SKU-012', 'WiFi版/64GB', 300, 3499.00),
('平板电脑-WiFi版-256G', 'SKU-013', 'WiFi版/256GB', 250, 4499.00),
('智能手表-运动版', 'SKU-014', '运动版/黑色硅胶表带', 400, 1999.00),
('智能手表-经典版', 'SKU-015', '经典版/棕色皮质表带', 300, 2499.00),
('无线充电器-白色', 'SKU-016', '白色/标准版', 1500, 199.00),
('机械键盘-红轴', 'SKU-017', '红轴/白色背光', 600, 599.00),
('机械键盘-青轴', 'SKU-018', '青轴/白色背光', 500, 599.00),
('显示器27寸-标准版', 'SKU-019', 'IPS/4K/60Hz', 200, 2999.00),
('移动电源-白色', 'SKU-020', '白色/20000mAh', 2000, 299.00);
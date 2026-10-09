DROP TABLE IF EXISTS `sale_item`;
CREATE TABLE `sale_item` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sale_id` int(11) NOT NULL COMMENT '销售单ID',
    `goods_id` int(11) NOT NULL COMMENT '商品ID',
    `quantity` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
    `unit_price` decimal(10,2) NOT NULL COMMENT '单价',
    PRIMARY KEY (`id`),
    KEY `idx_sale_id` (`sale_id`),
    KEY `idx_goods_id` (`goods_id`),
    CONSTRAINT `fk_si_sale` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_si_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sale_item` (`sale_id`, `goods_id`, `quantity`, `unit_price`) VALUES
(1, 1, 1, 4999.00),
(1, 2, 1, 5499.00),
(2, 6, 1, 899.00),
(3, 9, 1, 6999.00),
(4, 19, 1, 2999.00),
(5, 1, 1, 4999.00),
(6, 12, 1, 3499.00),
(7, 8, 1, 1299.00),
(8, 14, 1, 1999.00),
(9, 5, 1, 4499.00),
(10, 17, 1, 599.00),
(11, 3, 1, 4999.00),
(11, 4, 1, 3999.00),
(11, 10, 1, 6999.00),
(12, 2, 1, 5499.00),
(12, 11, 1, 8999.00),
(13, 1, 1, 4999.00),
(13, 7, 1, 899.00),
(14, 5, 1, 4499.00);
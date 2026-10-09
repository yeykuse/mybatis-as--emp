DROP TABLE IF EXISTS `product_goods`;
CREATE TABLE `product_goods` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `product_id` int(11) NOT NULL COMMENT '产品ID',
    `goods_id` int(11) NOT NULL COMMENT '商品ID',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_goods` (`product_id`, `goods_id`),
    KEY `idx_goods_id` (`goods_id`),
    CONSTRAINT `fk_pg_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_pg_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product_goods` (`product_id`, `goods_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(3, 6),
(3, 7),
(3, 8),
(4, 9),
(4, 10),
(4, 11),
(5, 12),
(5, 13),
(6, 14),
(6, 15),
(7, 16),
(8, 17),
(8, 18),
(9, 19),
(10, 20);
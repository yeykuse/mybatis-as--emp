DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `product_id` int(11) NOT NULL COMMENT '产品ID',
    `category_id` int(11) NOT NULL COMMENT '分类ID',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_category` (`product_id`, `category_id`),
    KEY `idx_category_id` (`category_id`),
    CONSTRAINT `fk_pc_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_pc_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product_category` (`product_id`, `category_id`) VALUES
(1, 1),
(1, 5),
(1, 13),
(2, 1),
(2, 5),
(3, 1),
(3, 6),
(3, 13),
(4, 2),
(4, 7),
(4, 14),
(5, 2),
(5, 8),
(6, 3),
(6, 10),
(7, 4),
(7, 11),
(8, 4),
(8, 12),
(9, 2),
(9, 9),
(10, 4),
(10, 11);
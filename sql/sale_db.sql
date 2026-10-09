DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `customer_id` int(11) NOT NULL COMMENT '客户ID',
    `sale_date` date NOT NULL COMMENT '销售日期',
    `total_amount` decimal(12,2) DEFAULT 0 COMMENT '总金额',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_customer_id` (`customer_id`),
    CONSTRAINT `fk_sale_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sale` (`customer_id`, `sale_date`, `total_amount`) VALUES
(1, '2026-01-15', 10498.00),
(1, '2026-03-20', 899.00),
(2, '2026-02-10', 6999.00),
(2, '2026-05-18', 2999.00),
(3, '2026-01-08', 4999.00),
(3, '2026-04-22', 3499.00),
(4, '2026-02-28', 1299.00),
(4, '2026-06-15', 1999.00),
(5, '2026-03-05', 4499.00),
(5, '2026-06-01', 599.00),
(6, '2026-01-20', 11997.00),
(6, '2026-04-10', 4998.00),
(7, '2026-02-15', 5998.00),
(7, '2026-05-25', 4499.00);
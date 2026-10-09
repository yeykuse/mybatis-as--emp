ALTER TABLE `product` ADD COLUMN `supplier_id` int(11) DEFAULT NULL COMMENT '供应商ID' AFTER `status`,
ADD KEY `idx_supplier_id` (`supplier_id`),
ADD CONSTRAINT `fk_product_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE SET NULL;

UPDATE `product` SET `supplier_id` = 1 WHERE `id` IN (1, 2, 6, 7);
UPDATE `product` SET `supplier_id` = 2 WHERE `id` IN (3, 10);
UPDATE `product` SET `supplier_id` = 3 WHERE `id` = 4;
UPDATE `product` SET `supplier_id` = 4 WHERE `id` IN (5, 9);
UPDATE `product` SET `supplier_id` = 5 WHERE `id` = 8;
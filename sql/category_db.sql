DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `category_name` varchar(255) NOT NULL COMMENT '分类名称',
    `parent_id` int(11) DEFAULT 0 COMMENT '父分类ID，0表示顶级分类',
    `sort_order` int(11) DEFAULT 0 COMMENT '排序号',
    `description` varchar(500) DEFAULT NULL COMMENT '分类描述',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `category` (`category_name`, `parent_id`, `sort_order`, `description`) VALUES
('手机数码', 0, 1, '手机及数码产品'),
('电脑办公', 0, 2, '电脑及办公设备'),
('智能穿戴', 0, 3, '智能穿戴设备'),
('配件周边', 0, 4, '配件及周边产品'),
('智能手机', 1, 1, '智能手机子分类'),
('蓝牙设备', 1, 2, '蓝牙设备子分类'),
('笔记本', 2, 1, '笔记本电脑子分类'),
('平板', 2, 2, '平板电脑子分类'),
('显示器', 2, 3, '显示器子分类'),
('手表手环', 3, 1, '智能手表手环子分类'),
('充电配件', 4, 1, '充电器及移动电源'),
('键盘鼠标', 4, 2, '键盘鼠标子分类'),
('热销爆品', 0, 10, '热销爆品专区'),
('新品上市', 0, 11, '新品上市专区');
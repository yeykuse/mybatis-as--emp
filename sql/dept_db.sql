DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
    `deptno` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
    `dname` varchar(255) NOT NULL COMMENT '部门名称',
    `loc` varchar(255) NOT NULL COMMENT '部门所在地',
    PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

INSERT INTO `dept` (`deptno`, `dname`, `loc`) VALUES
(10, '管理部', '北京'),
(20, '技术部', '上海'),
(30, '销售部', '广州'),
(40, '运营部', '深圳');
# mybatis-lab（demo1）

一个基于 **MyBatis / MyBatis-Plus** 的持久层框架学习实验项目，用于练习并演示 ORM 框架在员工管理系统（员工 / 部门 / 薪资等级 / 角色）上的 **CRUD 操作**与**多表关联查询**。

项目通过 JUnit 单元测试驱动，分别演示了 MyBatis 两种经典的映射方式——**XML 映射** 与 **注解映射**，并涵盖了多表关联（一对多、多对多）的查询实现。

## 技术栈

| 类别 | 技术 | 版本 |
| --- | --- | --- |
| 持久层框架 | MyBatis / MyBatis-Plus | 3.5.3.1 |
| 数据库 | MySQL | 8.x |
| JDBC 驱动 | mysql-connector-j | 8.0.33 |
| 单元测试 | JUnit | 4.13.2 |
| 日志 | Log4j | 1.2.17 |
| 构建工具 | Maven | — |
| 运行环境 | JDK 8 / Tomcat 10（Web 部署） | — |

## 项目结构

  demo1
├── sql/
│ └── ssm_emp.sql # 建库建表脚本 + 示例数据
├── src/
│ ├── main/
│ │ ├── java/com/lab/
│ │ │ ├── config/ # MyBatis-Plus 配置
│ │ │ ├── entity/ # 实体类：Emp / Dept / Salgrade / Role 及关联 VO
│ │ │ ├── mapper/ # Mapper 接口（注解版 + XML 版）
│ │ │ ├── test/ # MyBatisDemo 演示入口
│ │ │ └── util/ # MyBatisUtil 工具类
│ │ ├── resources/
│ │ │ ├── com/lab/mapper/ # XML 映射文件（DeptMapper / EmpMapper / SalgradeMapper）
│ │ │ ├── mybatis-config.xml # MyBatis 全局配置（数据源、别名、驼峰映射等）
│ │ │ └── log4j.properties # 日志配置
│ │ └── webapp/ # index.jsp + WEB-INF/web.xml（Web 部署）
│ └── test/java/com/lab/test/ # JUnit 测试类
│ ├── EmpAnnotationMapperTest # 员工——注解映射测试
│ ├── EmpXmlMapperTest # 员工——XML 映射测试
│ ├── SalgradeAnnotationMapperTest # 薪资等级——注解映射测试
│ ├── SalgradeXmlMapperTest # 薪资等级——XML 映射测试
│ └── RelationMapperTest # 多表关联查询测试
└── pom.xml plaintext  
## 功能特性

- **单表 CRUD**：以 `emp`（员工）、`salgrade`（薪资等级）、`dept`（部门）、`role`（角色）为核心表，实现增删改查。
- **两种映射方式**：
  - **注解映射**：通过 `@Select` / `@Insert` / `@Update` / `@Delete` 等注解直接在 Mapper 接口上书写 SQL。
  - **XML 映射**：通过 `<select>` / `<insert>` / `<update>` / `<delete>` 标签在 XML 映射文件中维护 SQL。
- **多表关联查询**：
  - 员工 — 部门（多对一 / 一对多）
  - 员工 — 薪资等级（跨表范围匹配）
  - 员工 — 角色（多对多，通过中间表）
- **驼峰映射**：`mapUnderscoreToCamelCase` 开启下划线字段与 Java 驼峰属性的自动转换。
- **示例数据**：`sql/ssm_emp.sql` 提供完整的建库建表语句与测试数据。

## 快速开始

### 1. 初始化数据库

在 MySQL 中执行 `sql/ssm_emp.sql`，会自动创建 `ssm_emp` 数据库、四张表（`emp` / `dept` / `salgrade` / `role`）并插入示例数据。

```sql
source sql/ssm_emp.sql;
  2. 配置数据库连接 编辑 src/main/resources/mybatis-config.xml，将 url、username、password 改为你的本地 MySQL 配置（默认 root / 123456）： xml  <property name="url"
    value="jdbc:mysql://localhost:3306/ssm_emp?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8"/>
<property name="username" value="root"/>
<property name="password" value="123456"/>
  3. 运行测试 使用 Maven 运行单元测试： bash  mvn test
  或在 IDEA 中直接运行 src/test/java/com/lab/test/ 下的各个测试类。 环境要求 • JDK 8 及以上 • Maven 3.6+ • MySQL 8.x • （可选）Tomcat 10，用于 Web 方式部署运行  许可证 本项目仅用于课程学习与实验演示。 plaintext  
文件在 `C:\Users\14296\Desktop\111\work1\demo1\README.md`，这就是完整原文，可以直接复制。

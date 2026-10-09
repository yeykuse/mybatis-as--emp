package com.chapter01;

import com.entity.Emp;
import com.entity.EmpWithDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 高级功能 Mapper
 * 
 * 演示 MyBatis 四大高级功能：
 * 1. 批量更新 - 使用 <foreach> 标签逐条生成 UPDATE 语句
 * 2. foreach IN 查询 - 传入字符串列表，生成 WHERE ename IN (?,?,?)
 * 3. 两张表关联查询 - emp LEFT JOIN dept
 * 4. resultMap - 将 JOIN 查询结果映射到 EmpWithDept DTO
 * 
 * 这些复杂功能 SQL 全部写在 XML 中（EmpAdvancedMapper.xml）
 */
public interface EmpAdvancedMapper {

    /**
     * 【批量更新】使用 foreach 批量修改员工薪资
     * 
     * 传入一个 Emp 列表，每个 Emp 对象必须设置 empno 和 sal 两个属性，
     * MyBatis 会为每条数据生成一行 UPDATE 语句，通过分号拼接为一条 SQL 执行。
     * 
     * XML 中使用 <foreach collection="list" item="emp" separator=";"> 逐条拼接
     * 
     * @param list 要更新的员工列表（每个元素需有 empno 和 sal）
     * @return 总共影响的行数
     */
    int batchUpdateSal(@Param("list") List<Emp> list);

    /**
     * 【foreach IN 查询 - 字符串】
     * 传入多个员工姓名，查询这些姓名的员工信息
     * 
     * XML 中使用 <foreach collection="names" item="name" open="(" separator="," close=")">
     * 生成 SQL: WHERE ename IN ('张三', '李四', '王五')
     * 
     * @param names 员工姓名列表
     * @return 匹配的员工列表
     */
    List<Emp> findByEnameList(@Param("names") List<String> names);

    /**
     * 【foreach IN 查询 - 数字】
     * 传入多个部门编号，查询这些部门的员工
     * 
     * XML 中使用 <foreach> 生成 SQL: WHERE deptno IN (10, 20)
     * 
     * @param deptnos 部门编号列表
     * @return 这些部门的员工列表
     */
    List<Emp> findByDeptnoList(@Param("deptnos") List<Integer> deptnos);

    /**
     * 【两张表关联查询 + resultMap】
     * emp LEFT JOIN dept，查询员工及其所属部门信息
     * 
     * 使用 <resultMap> 将关联查询结果映射到 EmpWithDept 对象
     * - <id> 映射主键
     * - <result> 映射普通列
     * - <association> 映射 dept 表的字段
     * 
     * @return 员工+部门信息的联合结果列表
     */
    List<EmpWithDept> findAllEmpWithDept();

    /**
     * 【注解方式 + script标签实现foreach IN查询】
     * 在 @Select 注解中使用 <script> 包裹，实现动态 SQL
     * 功能同 findByEnameList，只是用注解方式实现
     * 
     * @param names 员工姓名列表
     * @return 匹配的员工列表
     */
    @org.apache.ibatis.annotations.Select(
        "<script>" +
        "SELECT * FROM emp WHERE ename IN " +
        "<foreach collection='names' item='name' open='(' separator=',' close=')'>" +
        "#{name}" +
        "</foreach>" +
        "</script>"
    )
    List<Emp> findByEnameListAnnotation(@Param("names") List<String> names);
}
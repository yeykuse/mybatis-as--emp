package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * 注解方式 - 多参数查询
 * 
 * 核心知识点：
 *   一个方法同时接收多个 @Param 参数，MyBatis 会自动将各参数绑定到 SQL 中对应的占位符。
 *   当 Mapper 方法有多个参数时，必须使用 @Param 注解为每个参数取名，
 *   否则 MyBatis 默认使用 arg0、arg1、param1、param2 之类的名称，容易出错。
 */
public interface EmpMultiParamMapper {

    /**
     * 【多参数1】按职位和部门编号查询员工列表
     * 
     * 两个参数：job（职位）和 deptno（部门编号），都用 @Param 命名
     * SQL 中通过 #{job} 和 #{deptno} 引用对应参数
     * 
     * @param job    职位名称（精确匹配）
     * @param deptno 部门编号
     * @return 符合条件的员工列表
     */
    @Select("SELECT * FROM emp WHERE job = #{job} AND deptno = #{deptno}")
    List<Emp> findByJobAndDeptno(@Param("job") String job, @Param("deptno") Integer deptno);

    /**
     * 【多参数2】按薪资范围查询员工列表
     * 
     * 两个参数：minSal（最低薪资）和 maxSal（最高薪资）
     * 使用 BETWEEN ... AND ... 或 >= <= 都可以
     * 
     * @param minSal 最低薪资（包含）
     * @param maxSal 最高薪资（包含）
     * @return 薪资在范围内的员工列表
     */
    @Select("SELECT * FROM emp WHERE sal >= #{minSal} AND sal <= #{maxSal}")
    List<Emp> findBySalRange(@Param("minSal") BigDecimal minSal, @Param("maxSal") BigDecimal maxSal);

    /**
     * 【多参数3】按姓名模糊查询 + 职位精确匹配
     * 
     * 两个参数：ename（姓名，模糊查询）和 job（职位，精确匹配）
     * ename 使用 LIKE CONCAT 实现模糊查询，job 精确匹配
     * 
     * @param ename 员工姓名关键字（模糊匹配）
     * @param job   职位名称（精确匹配）
     * @return 符合条件的员工列表
     */
    @Select("SELECT * FROM emp WHERE ename LIKE CONCAT('%', #{ename}, '%') AND job = #{job}")
    List<Emp> findByEnameAndJob(@Param("ename") String ename, @Param("job") String job);
}
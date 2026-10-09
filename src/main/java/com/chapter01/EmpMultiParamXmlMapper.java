package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * XML方式 - 多参数查询
 * 
 * 本接口只有方法声明，SQL 语句全部写在对应的 XML 映射文件中（EmpMultiParamXmlMapper.xml）
 * 
 * 核心知识点：
 *   在 XML 中使用多参数时，SQL 占位符直接写 @Param 指定的名称即可，
 *   无需使用 parameterType（多参数时 parameterType 通常省略或用 Map）
 */
public interface EmpMultiParamXmlMapper {

    /**
     * 【多参数 - XML】按职位和部门编号查询员工列表
     * @param job    职位名称（精确匹配）
     * @param deptno 部门编号
     * @return 符合条件的员工列表
     */
    List<Emp> findByJobAndDeptnoXml(@Param("job") String job, @Param("deptno") Integer deptno);

    /**
     * 【多参数 - XML】按薪资范围查询员工列表
     * @param minSal 最低薪资（包含）
     * @param maxSal 最高薪资（包含）
     * @return 薪资在范围内的员工列表
     */
    List<Emp> findBySalRangeXml(@Param("minSal") BigDecimal minSal, @Param("maxSal") BigDecimal maxSal);

    /**
     * 【多参数 - XML】按姓名模糊查询 + 职位精确匹配
     * @param ename 员工姓名关键字（模糊匹配）
     * @param job   职位名称（精确匹配）
     * @return 符合条件的员工列表
     */
    List<Emp> findByEnameAndJobXml(@Param("ename") String ename, @Param("job") String job);
}
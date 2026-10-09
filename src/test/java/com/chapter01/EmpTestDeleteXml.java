package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * 【测试类】XML方式 - 删除
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper 删除 emp 表中的员工记录。
 * 
 * XML 中 DELETE 语句的配置（EmpDeleteXmlMapper.xml）：
 *   <delete id="deleteEmpXml">
 *       DELETE FROM emp WHERE empno = #{empno}
 *   </delete>
 * 
 * 测试策略（同注解方式）：
 *   先新增一条临时数据 -> 获取自增主键 -> 根据主键删除
 *   确保不会误删数据库中的原始测试数据。
 * 
 * 注解 vs XML 删除对比：
 *   注解方式：@Delete("DELETE FROM emp WHERE empno = #{empno}")
 *   XML 方式：<delete id="deleteEmpXml">DELETE FROM emp WHERE empno = #{empno}</delete>
 *   
 *   两者的 SQL 完全一样，只是定义位置不同。
 *   在实际项目中：
 *   - 简单 SQL 适合用注解（代码少、方便）
 *   - 复杂 SQL 适合用 XML（支持动态 SQL、可读性更好）
 */
public class EmpTestDeleteXml {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试 XML 方式删除员工
     * 
     * 操作流程同注解方式，只是使用 XML 版本的 Mapper：
     *   1. EmpAddXmlMapper.addEmpXml(emp)  -> 新增临时数据
     *   2. 获取自增 empno
     *   3. EmpDeleteXmlMapper.deleteEmpXml(empno) -> 删除
     *   4. 提交事务
     */
    @Test
    public void testDeleteEmpXml() {
        System.out.println("========== 【XML方式】删除员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpAddXmlMapper addMapper = sqlSession.getMapper(EmpAddXmlMapper.class);
            EmpDeleteXmlMapper deleteMapper = sqlSession.getMapper(EmpDeleteXmlMapper.class);

            // 新增一条临时数据
            Emp emp = new Emp();
            emp.setEname("待删除_XML_" + System.currentTimeMillis());
            emp.setJob("临时工");
            emp.setHiredate(new java.util.Date());
            emp.setSal(new java.math.BigDecimal("5000"));
            emp.setDeptno(10);
            
            // 新增并获取自增主键
            addMapper.addEmpXml(emp);
            System.out.println("新增待删除员工, empno=" + emp.getEmpno());

            // 根据 empno 删除
            int rows = deleteMapper.deleteEmpXml(emp.getEmpno());
            System.out.println("影响行数: " + rows);
            System.out.println("删除成功！");

            sqlSession.commit();
        }
    }
}
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
 * 【测试类】XML方式 - 按ID查
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper 根据员工编号（empno）查询单个员工信息。
 * 
 * XML方式参数传递：
 *   1. Java 接口使用 @Param("empno") 声明参数名
 *   2. XML 映射文件中使用 #{empno} 引用参数
 *   3. MyBatis 自动将参数值设置到预编译 SQL 的占位符中
 * 
 * 与注解方式的区别：
 *   - 注解：@Select("SELECT * FROM emp WHERE empno = #{empno}")
 *   - XML：<select id="findByIdXml" resultType="com.entity.Emp">
 *            SELECT * FROM emp WHERE empno = #{empno}
 *          </select>
 *   - 两者本质相同，只是 SQL 存放位置不同
 */
public class EmpTestFindByIdXml {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试 XML 方式的按ID查询
     * 
     * XML 映射文件定位规则：
     *   Mapper 接口: com.chapter01.EmpFindByIdXmlMapper
     *   XML 文件  : com/chapter01/EmpFindByIdXmlMapper.xml
     *   MyBatis 会根据接口的全限定名在 classpath 下查找同名 .xml 文件
     */
    @Test
    public void testFindByIdXml() {
        System.out.println("========== 【XML方式】按ID查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpFindByIdXmlMapper mapper = sqlSession.getMapper(EmpFindByIdXmlMapper.class);
            
            // 查询 empno = 2 的员工（和注解方式查不同的ID做区分）
            Emp emp = mapper.findByIdXml(2);
            
            System.out.println(emp);
        }
    }
}
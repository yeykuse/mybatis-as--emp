package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 【测试类】XML方式 - 查全部
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper 查询 emp 表中所有员工数据。
 * 
 * 对比注解方式：
 *   - 注解方式：SQL 写在 Java 接口的 @Select 注解里
 *   - XML 方式：SQL 写在独立的 .xml 映射文件中，Java 接口只声明方法签名
 * 
 * XML方式优点：
 *   - SQL 与 Java 代码完全分离，便于 DBA 或非 Java 人员维护
 *   - 支持动态 SQL（<if>、<where>、<foreach> 等标签）
 *   - 支持复杂的结果映射（<resultMap>）
 */
public class EmpTestFindAllXml {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 在每个测试方法执行前初始化 SqlSessionFactory
     * 
     * mybatis-config.xml 中注册了 EmpFindAllXmlMapper 接口，
     * MyBatis 会自动查找同路径下的 EmpFindAllXmlMapper.xml 映射文件
     * （命名规则：接口全限定名 + .xml）
     */
    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试 XML 方式的查询全部功能
     * 
     * 执行过程：
     *   1. sqlSession.getMapper(EmpFindAllXmlMapper.class)
     *      -> 创建 XML 方式的 Mapper 代理对象
     *   2. mapper.findAllXml()
     *      -> MyBatis 根据接口方法名 findAllXml 在 XML 中找对应的 <select> 标签
     *      -> 执行 SQL: SELECT * FROM emp
     *      -> 将查询结果自动映射为 List<Emp> 返回
     */
    @Test
    public void testFindAllXml() {
        System.out.println("========== 【XML方式】查询所有员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            // 获取 XML 方式的 Mapper
            EmpFindAllXmlMapper mapper = sqlSession.getMapper(EmpFindAllXmlMapper.class);
            
            // 执行 XML 中定义的查询
            List<Emp> emps = mapper.findAllXml();
            
            System.out.println("共 " + emps.size() + " 条记录");
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }
}
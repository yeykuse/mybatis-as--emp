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
 * 【测试类】XML方式 - 模糊查询
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper 根据员工姓名进行模糊匹配查询。
 * 
 * XML 中模糊查询的写法：
 *   <select id="findByEnameLikeXml" resultType="com.entity.Emp">
 *       SELECT * FROM emp
 *       WHERE ename LIKE CONCAT('%', #{value}, '%')
 *   </select>
 * 
 * 知识点：
 *   - resultType：告诉 MyBatis 将查询结果映射到哪个实体类
 *   - #{value} 引用的是 @Param("value") 注解指定的参数名
 *   - 如果 XML 中使用了 <if> 标签，就可以实现动态模糊查询
 *     （例如：传入姓名就按姓名模糊，不传就查全部）
 */
public class EmpTestFindByEnameXml {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试 XML 方式的模糊查询
     * 
     * 功能同注解方式，只是 SQL 写在 XML 中，
     * 查询"五"相关的员工姓名（如"王五"）
     */
    @Test
    public void testFindByEnameLikeXml() {
        System.out.println("========== 【XML方式】模糊查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpFindByEnameXmlMapper mapper = sqlSession.getMapper(EmpFindByEnameXmlMapper.class);
            
            // 模糊查询姓名中包含"五"的员工
            List<Emp> emps = mapper.findByEnameLikeXml("五");
            
            System.out.println("共 " + emps.size() + " 条记录");
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }
}
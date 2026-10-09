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
 * 【测试类】注解方式 - 模糊查询
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper 根据员工姓名进行模糊匹配查询。
 * 
 * 模糊查询的核心：
 *   SQL中使用 LIKE 关键字配合 CONCAT 函数拼接通配符：
 *     WHERE ename LIKE CONCAT('%', #{value}, '%')
 *   
 *   CONCAT('%', #{value}, '%') 等价于 '%value%'
 *   表示：只要员工姓名中包含指定字符，就匹配成功
 * 
 * 对比两种写法：
 *   - 安全写法（推荐）：LIKE CONCAT('%', #{value}, '%')
 *   - 简单写法（风险）：LIKE '%${value}%'（$ 拼接可能引发 SQL 注入）
 *   
 *   MyBatis 中：
 *     #{value} -> 使用预编译 ? 占位符，安全防注入
 *     ${value} -> 直接拼接字符串，有 SQL 注入风险
 */
public class EmpTestFindByEnameAnnotation {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试注解方式的模糊查询
     * 
     * 执行流程：
     *   1. 调用 mapper.findByEnameLike("张")
     *   2. MyBatis 执行 SQL：SELECT * FROM emp WHERE ename LIKE CONCAT('%', '张', '%')
     *   3. 返回所有姓名中包含"张"字的员工列表
     *   4. 遍历打印结果
     * 
     * 预期结果：
     *   如果 emp 表中有"张三"，查询结果会包含该记录
     */
    @Test
    public void testFindByEnameLike() {
        System.out.println("========== 【注解方式】模糊查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpFindByEnameMapper mapper = sqlSession.getMapper(EmpFindByEnameMapper.class);
            
            // 模糊查询姓名中包含"张"的员工
            List<Emp> emps = mapper.findByEnameLike("张");
            
            System.out.println("共 " + emps.size() + " 条记录");
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }
}
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
 * 【测试类】注解方式 - 按ID查
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper 根据员工编号（empno）查询单个员工信息。
 * 
 * 核心知识点：
 *   1. @Param 注解：当方法参数名与 SQL 中占位符不一致时，用 @Param 指定映射关系
 *   2. SQL 中的 #{empno} 占位符：MyBatis 会预编译替换为 ?，防止 SQL 注入
 *   3. 返回单个对象：当查询结果唯一时，Mapper 方法返回类型可以直接写 Emp 实体类
 * 
 * 参数传递过程：
 *   Java方法: findById(@Param("empno") Integer empno)
 *   SQL语句: SELECT * FROM emp WHERE empno = #{empno}
 *   实际执行: SELECT * FROM emp WHERE empno = ?
 *   参数设置: preparedStatement.setInt(1, 1)
 */
public class EmpTestFindByIdAnnotation {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试按ID查询员工
     * 
     * 操作流程：
     *   1. 获取 SqlSession（数据库连接会话）
     *   2. 通过 getMapper() 获取 Mapper 代理
     *   3. 调用 findById(1) 传入员工编号
     *   4. MyBatis 执行 SELECT * FROM emp WHERE empno = 1
     *   5. 将结果集映射为 Emp 对象并返回
     *   6. 打印员工信息
     * 
     * 注意事项：
     *   - 主键ID写的是固定值 1，需要确保 emp 表中存在 empno=1 的记录
     *   - 如果记录不存在，findById 返回 null
     */
    @Test
    public void testFindById() {
        System.out.println("========== 【注解方式】按ID查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpFindByIdMapper mapper = sqlSession.getMapper(EmpFindByIdMapper.class);
            
            // 查询 empno = 1 的员工
            Emp emp = mapper.findById(1);
            
            // 如果找到则打印，找不到会输出 null
            System.out.println(emp);
        }
    }
}
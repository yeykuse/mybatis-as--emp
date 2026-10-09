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
 * 【测试类】注解方式 - 查全部
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper 查询 emp 表中所有员工数据。
 * 
 * 测试流程：
 *   1. 读取 MyBatis 核心配置文件 (mybatis-config.xml)
 *   2. 通过 SqlSessionFactoryBuilder 构建 SqlSessionFactory
 *   3. 打开 SqlSession（数据库会话）
 *   4. 通过 getMapper() 获取注解方式的 Mapper 接口代理对象
 *   5. 调用 findAll() 方法执行 SELECT * FROM emp
 *   6. 遍历打印查询结果
 * 
 * 注解方式特点：
 *   SQL 语句直接写在 Java 接口的方法注解中（@Select、@Insert 等），
 *   不需要编写 XML 映射文件。适用于 SQL 较简单的场景。
 */
public class EmpTestFindAllAnnotation {

    /**
     * SqlSessionFactory 是 MyBatis 的核心工厂对象，
     * 用于创建 SqlSession（数据库会话）。
     * 通常在应用启动时创建一次，全局共享。
     */
    private SqlSessionFactory sqlSessionFactory;

    /**
     * @BeforeEach 注解表示该方法在每个 @Test 测试方法执行前都会运行。
     * 这里负责初始化 SqlSessionFactory。
     * 
     * 操作步骤：
     *   1. Resources.getResourceAsStream() -> 从 classpath 读取 mybatis-config.xml
     *   2. SqlSessionFactoryBuilder.build()  -> 解析配置文件，构建工厂对象
     * 
     * mybatis-config.xml 中配置了：
     *   - 数据库连接信息（驱动、URL、用户名、密码）
     *   - 日志实现（STDOUT_LOGGING，将SQL打印到控制台）
     *   - 注册的 Mapper 接口（告诉 MyBatis 有哪些映射器）
     */
    @BeforeEach
    public void init() throws Exception {
        // 从 classpath 根路径加载 MyBatis 核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // 解析配置文件并构建 SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * @Test 标记这是一个 JUnit5 测试方法，可以直接在 IDE 中运行。
     * 
     * 查询全部员工流程：
     *   try-with-resources 语法 -> SqlSession 会自动关闭，无需手动释放
     */
    @Test
    public void testFindAll() {
        System.out.println("========== 【注解方式】查询所有员工 ==========");
        
        // try-with-resources: SqlSession 实现了 AutoCloseable 接口
        // 无论正常执行还是抛出异常，结束后都会自动调用 sqlSession.close()
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            // getMapper() -> 通过动态代理生成 Mapper 接口的实现类对象
            // 调用它的方法时，MyBatis 会根据注解中的 SQL 语句执行数据库操作
            EmpFindAllMapper mapper = sqlSession.getMapper(EmpFindAllMapper.class);
            
            // 执行 SELECT * FROM emp，返回 List<Emp> 集合
            List<Emp> emps = mapper.findAll();
            
            System.out.println("共 " + emps.size() + " 条记录");
            
            // 遍历打印每个员工的信息（Emp 类重写了 toString 方法）
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }
}
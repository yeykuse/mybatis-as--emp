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
 * 【测试类】注解方式 - 删除
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper 删除 emp 表中的员工记录。
 * 
 * 删除操作的策略（避免误删已有数据）：
 *   1. 先新增一条临时数据（这样删除的数据是我们自己创建的）
 *   2. 获取新增数据的自增 empno
 *   3. 根据 empno 删除该记录
 *   4. 提交事务
 * 
 * 这样做的好处：
 *   - 不会误删数据库中的原始测试数据
 *   - 每次运行测试都会新增再删除，数据前后一致
 *   - 验证了新增和删除两个功能都能正常工作
 * 
 * 注意事项：
 *   - 新增和删除在同一个事务中，要么都成功，要么都回滚
 *   - 如果删除失败，新增的数据也不会保留（事务回滚）
 *   - 如果要保留新增的数据，需要单独调用 sqlSession.commit()
 */
public class EmpTestDeleteAnnotation {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试注解方式删除员工
     * 
     * 操作流程：
     *   1. 获取 EmpAddMapper（新增）和 EmpDeleteMapper（删除）
     *   2. 创建一个临时员工对象并插入数据库
     *   3. 从插入后的 emp 对象中获取自增的 empno
     *   4. 根据 empno 删除刚刚新增的员工
     *   5. 提交事务
     * 
     * 涉及两个 Mapper 接口：
     *   - EmpAddMapper.addEmp(emp)     -> 新增，带主键回填
     *   - EmpDeleteMapper.deleteEmp(empno) -> 根据 empno 删除
     */
    @Test
    public void testDeleteEmp() {
        System.out.println("========== 【注解方式】删除员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            // 获取新增和删除的 Mapper
            EmpAddMapper addMapper = sqlSession.getMapper(EmpAddMapper.class);
            EmpDeleteMapper deleteMapper = sqlSession.getMapper(EmpDeleteMapper.class);

            // 第一步：创建一个临时员工（用于测试删除）
            Emp emp = new Emp();
            emp.setEname("待删除_注解_" + System.currentTimeMillis());
            emp.setJob("临时工");
            emp.setHiredate(new java.util.Date());
            emp.setSal(new java.math.BigDecimal("5000"));
            emp.setDeptno(10);
            
            // 新增 -> emp.getEmpno() 会被自动回填
            addMapper.addEmp(emp);
            System.out.println("新增待删除员工, empno=" + emp.getEmpno());

            // 第二步：根据 empno 删除刚刚新增的员工
            int rows = deleteMapper.deleteEmp(emp.getEmpno());
            System.out.println("影响行数: " + rows);
            System.out.println("删除成功！");

            // 提交事务
            sqlSession.commit();
        }
    }
}
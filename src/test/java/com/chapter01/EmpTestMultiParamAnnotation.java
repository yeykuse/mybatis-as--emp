package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * 【测试类】注解方式 - 多参数查询
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper，一个方法同时传入多个参数进行查询。
 * 
 * 核心知识点：
 *   1. 多参数绑定：方法有多个参数时，必须用 @Param 为每个参数取名
 *   2. 如果没有 @Param，MyBatis 会使用 arg0、arg1、param1、param2 等名称，容易出错
 *   3. 多条件查询：WHERE 子句中可以同时使用多个条件，用 AND 连接
 *   4. 不同参数类型：可以混用 String、Integer、BigDecimal 等不同类型
 * 
 * 本类测试三个多参数方法：
 *   1. findByJobAndDeptno  — 按职位+部门编号（String + Integer）
 *   2. findBySalRange       — 按薪资范围（BigDecimal + BigDecimal）
 *   3. findByEnameAndJob    — 按姓名模糊+职位精确（String + String）
 */
public class EmpTestMultiParamAnnotation {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试多参数1：按职位和部门编号查询
     * 
     * 操作流程：
     *   1. 获取 SqlSession
     *   2. 获取注解方式多参数 Mapper
     *   3. 调用 findByJobAndDeptno("SALESMAN", 30)，传入两个参数
     *   4. MyBatis 将 "SALESMAN" 绑定到 #{job}，30 绑定到 #{deptno}
     *   5. 执行 SELECT * FROM emp WHERE job = 'SALESMAN' AND deptno = 30
     *   6. 打印查询结果
     */
    @Test
    public void testFindByJobAndDeptno() {
        System.out.println("========== 【注解-多参数】按职位和部门编号查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            EmpMultiParamMapper mapper = sqlSession.getMapper(EmpMultiParamMapper.class);

            // 查询职位为 SALESMAN 且部门编号为 30 的员工
            List<Emp> list = mapper.findByJobAndDeptno("SALESMAN", 30);

            System.out.println("查询条件：job=SALESMAN, deptno=30");
            System.out.println("查询结果数：" + list.size());
            for (Emp emp : list) {
                System.out.println(emp);
            }
        }
    }

    /**
     * 测试多参数2：按薪资范围查询
     * 
     * 操作流程：
     *   1. 获取 SqlSession
     *   2. 获取注解方式多参数 Mapper
     *   3. 调用 findBySalRange(new BigDecimal("3000"), new BigDecimal("5000"))
     *   4. 两个 BigDecimal 参数分别绑定到 #{minSal} 和 #{maxSal}
     *   5. 执行 SELECT * FROM emp WHERE sal >= 3000 AND sal <= 5000
     *   6. 打印薪资在 3000-5000 之间的员工
     */
    @Test
    public void testFindBySalRange() {
        System.out.println("========== 【注解-多参数】按薪资范围查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            EmpMultiParamMapper mapper = sqlSession.getMapper(EmpMultiParamMapper.class);

            // 查询薪资在 3000 到 5000 之间的员工（含边界）
            BigDecimal minSal = new BigDecimal("3000");
            BigDecimal maxSal = new BigDecimal("5000");
            List<Emp> list = mapper.findBySalRange(minSal, maxSal);

            System.out.println("查询条件：sal >= " + minSal + " AND sal <= " + maxSal);
            System.out.println("查询结果数：" + list.size());
            for (Emp emp : list) {
                System.out.println(emp);
            }
        }
    }

    /**
     * 测试多参数3：按姓名模糊查询 + 职位精确匹配
     * 
     * 操作流程：
     *   1. 获取 SqlSession
     *   2. 获取注解方式多参数 Mapper
     *   3. 调用 findByEnameAndJob("张", "经理")
     *   4. "张" 绑定到 #{ename}（模糊查询），"经理" 绑定到 #{job}（精确匹配）
     *   5. 执行 SELECT * FROM emp WHERE ename LIKE '%张%' AND job = '经理'
     *   6. 打印姓名中包含"张"且职位为"经理"的员工
     */
    @Test
    public void testFindByEnameAndJob() {
        System.out.println("========== 【注解-多参数】按姓名模糊+职位精确查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            EmpMultiParamMapper mapper = sqlSession.getMapper(EmpMultiParamMapper.class);

            // 查询姓名中包含"张"且职位为"经理"的员工
            List<Emp> list = mapper.findByEnameAndJob("张", "经理");

            System.out.println("查询条件：ename LIKE '%张%', job=经理");
            System.out.println("查询结果数：" + list.size());
            for (Emp emp : list) {
                System.out.println(emp);
            }
        }
    }
}
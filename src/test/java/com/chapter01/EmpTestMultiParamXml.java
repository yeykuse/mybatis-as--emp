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
 * 【测试类】XML方式 - 多参数查询
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper，一个方法同时传入多个参数进行查询。
 * 
 * 核心知识点：
 *   1. XML 多参数：当方法使用 @Param 命名后，XML 中直接用 #{paramName} 引用
 *   2. XML 中不需要写 parameterType（多参数时通常省略）
 *   3. XML 中的转义字符：>= 写作 &gt;=，<= 写作 &lt;=
 *   4. 注解和 XML 的参数绑定规则一致：都用 @Param 命名，SQL 中用 #{名称} 引用
 * 
 * 本类测试三个多参数方法（XML版）：
 *   1. findByJobAndDeptnoXml — 按职位+部门编号
 *   2. findBySalRangeXml      — 按薪资范围
 *   3. findByEnameAndJobXml   — 按姓名模糊+职位精确
 */
public class EmpTestMultiParamXml {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试多参数1（XML）：按职位和部门编号查询
     * 
     * 操作流程：
     *   1. 获取 SqlSession
     *   2. 获取 XML 方式多参数 Mapper
     *   3. 调用 findByJobAndDeptnoXml("SALESMAN", 30)，传入两个参数
     *   4. MyBatis 查找 XML 中 id="findByJobAndDeptnoXml" 的 SQL
     *   5. #{job} 替换为 "SALESMAN"，#{deptno} 替换为 30
     *   6. 执行 SQL，打印结果
     */
    @Test
    public void testFindByJobAndDeptnoXml() {
        System.out.println("========== 【XML-多参数】按职位和部门编号查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            EmpMultiParamXmlMapper mapper = sqlSession.getMapper(EmpMultiParamXmlMapper.class);

            // 查询职位为 SALESMAN 且部门编号为 30 的员工
            List<Emp> list = mapper.findByJobAndDeptnoXml("SALESMAN", 30);

            System.out.println("查询条件：job=SALESMAN, deptno=30");
            System.out.println("查询结果数：" + list.size());
            for (Emp emp : list) {
                System.out.println(emp);
            }
        }
    }

    /**
     * 测试多参数2（XML）：按薪资范围查询
     * 
     * 操作流程：
     *   1. 获取 SqlSession
     *   2. 获取 XML 方式多参数 Mapper
     *   3. 调用 findBySalRangeXml(new BigDecimal("3000"), new BigDecimal("5000"))
     *   4. MyBatis 将两个参数注入 XML 中的 #{minSal} 和 #{maxSal}
     *   5. 执行 SELECT * FROM emp WHERE sal >= 3000 AND sal <= 5000
     *   6. 打印结果
     */
    @Test
    public void testFindBySalRangeXml() {
        System.out.println("========== 【XML-多参数】按薪资范围查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            EmpMultiParamXmlMapper mapper = sqlSession.getMapper(EmpMultiParamXmlMapper.class);

            // 查询薪资在 2000 到 4000 之间的员工（与注解测试用不同范围，方便区分日志）
            BigDecimal minSal = new BigDecimal("2000");
            BigDecimal maxSal = new BigDecimal("4000");
            List<Emp> list = mapper.findBySalRangeXml(minSal, maxSal);

            System.out.println("查询条件：sal >= " + minSal + " AND sal <= " + maxSal);
            System.out.println("查询结果数：" + list.size());
            for (Emp emp : list) {
                System.out.println(emp);
            }
        }
    }

    /**
     * 测试多参数3（XML）：按姓名模糊查询 + 职位精确匹配
     * 
     * 操作流程：
     *   1. 获取 SqlSession
     *   2. 获取 XML 方式多参数 Mapper
     *   3. 调用 findByEnameAndJobXml("李", "销售员")
     *   4. "李" 绑定到 #{ename}（模糊查询），"销售员" 绑定到 #{job}（精确匹配）
     *   5. 执行 SELECT * FROM emp WHERE ename LIKE '%李%' AND job = '销售员'
     *   6. 打印姓名中包含"李"且职位为"销售员"的员工
     */
    @Test
    public void testFindByEnameAndJobXml() {
        System.out.println("========== 【XML-多参数】按姓名模糊+职位精确查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            EmpMultiParamXmlMapper mapper = sqlSession.getMapper(EmpMultiParamXmlMapper.class);

            // 查询姓名中包含"李"且职位为"销售员"的员工
            List<Emp> list = mapper.findByEnameAndJobXml("李", "销售员");

            System.out.println("查询条件：ename LIKE '%李%', job=销售员");
            System.out.println("查询结果数：" + list.size());
            for (Emp emp : list) {
                System.out.println(emp);
            }
        }
    }
}
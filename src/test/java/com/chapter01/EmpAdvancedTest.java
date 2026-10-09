package com.chapter01;

import com.entity.Emp;
import com.entity.EmpWithDept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 【测试类】MyBatis 高级功能测试
 * 
 * 测试四大高级功能：
 * 1. 批量更新 - foreach 逐条拼接 UPDATE
 * 2. foreach IN 字符串查询 - WHERE ename IN (?,?,?)
 * 3. foreach IN 数字查询 - WHERE deptno IN (?,?)
 * 4. 两张表关联查询 + resultMap - emp LEFT JOIN dept
 * 
 * 操作指南：
 *   在 IDE 中右键该类 -> Run，或者单独运行某个 @Test 方法
 */
public class EmpAdvancedTest {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 初始化 SqlSessionFactory
     * mybatis-config.xml 中已注册 EmpAdvancedMapper
     */
    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    // ==================== 1. 批量更新 ====================

    /**
     * 测试 foreach 批量更新薪资
     * 
     * 原理：传入 List<Emp>，XML 中 foreach 逐条生成 UPDATE 语句，
     *       用分号拼接后一次性执行
     * 
     * 生成 SQL 示例：
     *   UPDATE emp SET sal = 18000 WHERE empno = 5;
     *   UPDATE emp SET sal = 16000 WHERE empno = 6;
     *   UPDATE emp SET sal = 15000 WHERE empno = 7;
     */
    @Test
    public void testBatchUpdateSal() {
        System.out.println("========== 【批量更新】foreach 批量修改薪资 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmpAdvancedMapper mapper = sqlSession.getMapper(EmpAdvancedMapper.class);

            // 构建要更新的员工列表（只设 empno 和 sal）
            Emp emp5 = new Emp();
            emp5.setEmpno(5);
            emp5.setSal(new BigDecimal("18000"));

            Emp emp6 = new Emp();
            emp6.setEmpno(6);
            emp6.setSal(new BigDecimal("16000"));

            Emp emp7 = new Emp();
            emp7.setEmpno(7);
            emp7.setSal(new BigDecimal("15000"));

            List<Emp> updateList = Arrays.asList(emp5, emp6, emp7);
            int rows = mapper.batchUpdateSal(updateList);
            System.out.println("批量更新行数: " + rows);
            sqlSession.commit();
        }
    }

    // ==================== 2. foreach IN 查询 - 字符串 ====================

    /**
     * 测试 foreach IN 字符串查询
     * 
     * 传入姓名列表，查询这些姓名的员工
     * 
     * 生成 SQL：
     *   SELECT * FROM emp WHERE ename IN ('张三', '李四', '王五')
     * 
     * 注意：如果 IN 列表中有不存在的姓名，该条被忽略，不影响其他匹配
     */
    @Test
    public void testFindByEnameList() {
        System.out.println("========== 【foreach IN】按姓名列表查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmpAdvancedMapper mapper = sqlSession.getMapper(EmpAdvancedMapper.class);

            // 传入多个姓名
            List<String> names = Arrays.asList("张三", "李四", "王五");
            List<Emp> emps = mapper.findByEnameList(names);

            System.out.println("共 " + emps.size() + " 条记录");
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }

    // ==================== 3. foreach IN 查询 - 数字 ====================

    /**
     * 测试 foreach IN 数字查询
     * 
     * 传入部门编号列表，查询这些部门的所有员工
     * 
     * 生成 SQL：
     *   SELECT * FROM emp WHERE deptno IN (10, 20)
     */
    @Test
    public void testFindByDeptnoList() {
        System.out.println("========== 【foreach IN】按部门列表查询员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmpAdvancedMapper mapper = sqlSession.getMapper(EmpAdvancedMapper.class);

            // 传入多个部门编号
            List<Integer> deptnos = Arrays.asList(10, 20);
            List<Emp> emps = mapper.findByDeptnoList(deptnos);

            System.out.println("共 " + emps.size() + " 条记录");
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }

    // ==================== 4. 关联查询 + resultMap ====================

    /**
     * 测试两张表关联查询 + resultMap
     * 
     * emp LEFT JOIN dept，将结果通过 resultMap 映射到 EmpWithDept
     * 
     * 生成 SQL：
     *   SELECT e.*, d.dname, d.loc
     *   FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno
     *   ORDER BY e.empno
     * 
     * resultMap 的作用：
     *   将查询结果中的 dname、loc 列映射到 EmpWithDept 的 dname、loc 属性
     */
    @Test
    public void testFindAllEmpWithDept() {
        System.out.println("========== 【关联查询+resultMap】员工+部门联合查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmpAdvancedMapper mapper = sqlSession.getMapper(EmpAdvancedMapper.class);

            List<EmpWithDept> results = mapper.findAllEmpWithDept();
            System.out.println("共 " + results.size() + " 条记录");
            for (EmpWithDept ewd : results) {
                System.out.println(ewd);
            }
        }
    }

    // ==================== 5. 注解方式 foreach IN ====================

    /**
     * 测试注解方式实现 foreach IN 查询
     * 
     * 在 @Select 注解中使用 <script> 标签包裹动态 SQL，
     * 功能同 XML 版本的 findByEnameList
     */
    @Test
    public void testFindByEnameListAnnotation() {
        System.out.println("========== 【注解方式+script】foreach IN 查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmpAdvancedMapper mapper = sqlSession.getMapper(EmpAdvancedMapper.class);

            List<String> names = Arrays.asList("孙七", "周八", "吴九");
            List<Emp> emps = mapper.findByEnameListAnnotation(names);

            System.out.println("共 " + emps.size() + " 条记录");
            for (Emp emp : emps) {
                System.out.println(emp);
            }
        }
    }
}
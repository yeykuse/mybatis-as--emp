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
import java.util.Date;

/**
 * 【测试类】注解方式 - 新增 + 自增主键回填
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper 向 emp 表中插入一条新员工记录，
 *   并演示 MyBatis 的 自增主键回填 功能。
 * 
 * 自增主键回填 详解：
 *   问题：empno 是自增字段，INSERT 时不需要手动设置，由数据库自动生成。
 *       但插入后，Java 对象中 empno 仍然是 null，不知道新生成的ID是多少。
 *   解决：@Options(useGeneratedKeys = true, keyProperty = "empno")
 *        - useGeneratedKeys = true  -> 告诉 MyBatis 要获取数据库自增主键
 *        - keyProperty = "empno"    -> 将自动生成的主键值设置到 Emp 对象的 empno 属性
 *   效果：插入后 emp.getEmpno() 就能拿到数据库自动生成的主键值
 * 
 * 事务说明：
 *   增删改操作需要手动提交事务（sqlSession.commit()），
 *   否则数据不会真正写入数据库。
 *   查询操作不需要提交事务。
 */
public class EmpTestAddAnnotation {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试注解方式新增员工
     * 
     * 操作流程：
     *   1. 创建 Emp 对象并设置各字段值（不设置 empno，由数据库自增）
     *   2. 调用 mapper.addEmp(emp) 执行 INSERT
     *   3. MyBatis 执行插入后，自动将自增的 empno 回填到 emp 对象中
     *   4. 打印影响行数和回填的 empno
     *   5. 提交事务
     * 
     * 字段说明：
     *   - ename: 员工姓名（加时间戳区分，避免重复）
     *   - job:   职位
     *   - mgr:   上级领导编号
     *   - hiredate: 入职日期
     *   - sal:   薪资
     *   - comm:  奖金/提成
     *   - deptno: 部门编号
     */
    @Test
    public void testAddEmp() {
        System.out.println("========== 【注解方式】新增员工 ==========");
        
        // try-with-resources 自动关闭 SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpAddMapper mapper = sqlSession.getMapper(EmpAddMapper.class);

            // 创建员工对象（不设置 empno，由数据库自动生成）
            Emp emp = new Emp();
            emp.setEname("注解_新增_" + System.currentTimeMillis()); // 加时间戳防重复
            emp.setJob("开发工程师");
            emp.setMgr(3);       // 上级编号
            emp.setHiredate(new Date()); // 当前时间作为入职日期
            emp.setSal(new BigDecimal("15000"));
            emp.setComm(new BigDecimal("3000"));
            emp.setDeptno(20);

            // 执行 INSERT，返回影响行数
            int rows = mapper.addEmp(emp);
            System.out.println("影响行数: " + rows);
            
            // 关键点：插入后 emp 对象的 empno 已被回填，可直接获取
            System.out.println("自增主键回填 - empno: " + emp.getEmpno());

            // 增删改需要手动提交事务
            sqlSession.commit();
        }
    }
}
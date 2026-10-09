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

/**
 * 【测试类】注解方式 - 修改
 * 
 * 功能说明：
 *   测试使用 注解方式 的 Mapper 修改 emp 表中已有员工的信息。
 * 
 * 修改操作的流程：
 *   1. 先查询出要修改的员工（findById）
 *   2. 修改对象的属性值（setSal、setComm）
 *   3. 调用 updateEmp(emp) 执行 UPDATE
 * 
 * 这种方式叫"先查后改"，优点是：
 *   - 确保要修改的记录存在
 *   - 可以看到修改前的原始值
 * 
 * 注意：
 *   UPDATE 语句必须根据 empno 定位到具体记录，
 *   SQL 中通常用 WHERE empno = #{empno} 作为条件，
 *   所以 Emp 对象必须设置了 empno 才能正确更新。
 */
public class EmpTestUpdateAnnotation {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试注解方式修改员工
     * 
     * 操作流程：
     *   1. 用 findById(1) 查出 empno=1 的员工
     *   2. 保存旧薪资用作对比
     *   3. 将薪资增加 1000
     *   4. 设置奖金为 8888
     *   5. 调用 updateEmp(emp) 更新数据库
     *   6. 提交事务
     * 
     * 注意：
     *   - 一个 SqlSession 中可以获取多个不同的 Mapper
     *   - 这里同时用了 EmpFindByIdMapper（查）和 EmpUpdateMapper（改）
     *   - 它们操作的是同一个数据库连接
     */
    @Test
    public void testUpdateEmp() {
        System.out.println("========== 【注解方式】修改员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            // 获取修改和查询的 Mapper
            EmpUpdateMapper mapper = sqlSession.getMapper(EmpUpdateMapper.class);
            EmpFindByIdMapper findMapper = sqlSession.getMapper(EmpFindByIdMapper.class);

            // 第一步：查询出要修改的员工
            Emp emp = findMapper.findById(1);
            
            if (emp != null) {
                // 记录旧薪资
                BigDecimal oldSal = emp.getSal();
                
                // 第二步：修改属性
                emp.setSal(oldSal.add(new BigDecimal("1000"))); // 涨薪 1000
                emp.setComm(new BigDecimal("8888"));            // 设置奖金
                
                // 第三步：执行 UPDATE
                int rows = mapper.updateEmp(emp);
                System.out.println("影响行数: " + rows);
                System.out.println("薪资更新: " + oldSal + " → " + emp.getSal());
                
                // 提交事务
                sqlSession.commit();
            } else {
                System.out.println("未找到 empno=1 的员工");
            }
        }
    }
}
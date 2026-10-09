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
 * 【测试类】XML方式 - 修改
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper 修改 emp 表中已有员工的信息。
 * 
 * XML 中 UPDATE 语句的配置（EmpUpdateXmlMapper.xml）：
 *   <update id="updateEmpXml">
 *       UPDATE emp
 *       SET job = #{job}, sal = #{sal}, comm = #{comm}, ...
 *       WHERE empno = #{empno}
 *   </update>
 * 
 * 先查后改模式：
 *   先用 XML 方式的 findByEmpnoXml 查询，
 *   再用 updateEmpXml 更新，
 *   保证注解和 XML 两种方式完全独立、互不依赖。
 * 
 * 对比：
 *   - 注解方式：EmpFindByIdMapper.findById() + EmpUpdateMapper.updateEmp()
 *   - XML 方式：EmpFindByIdXmlMapper.findByIdXml() + EmpUpdateXmlMapper.updateEmpXml()
 *   - 两者使用不同的 Mapper 接口，但操作的是同一张数据库表
 */
public class EmpTestUpdateXml {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试 XML 方式修改员工
     * 
     * 这里修改 empno=2 的员工（与注解方式改不同的 ID 做对比）：
     *   1. 用 XML 的 findByIdXml(2) 查询
     *   2. 涨薪 2000
     *   3. 修改职位为"高级副总经理"
     *   4. 提交事务
     */
    @Test
    public void testUpdateEmpXml() {
        System.out.println("========== 【XML方式】修改员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpUpdateXmlMapper mapper = sqlSession.getMapper(EmpUpdateXmlMapper.class);
            EmpFindByIdXmlMapper findMapper = sqlSession.getMapper(EmpFindByIdXmlMapper.class);

            // 查询 empno=2 的员工
            Emp emp = findMapper.findByIdXml(2);
            
            if (emp != null) {
                BigDecimal oldSal = emp.getSal();
                
                // 修改属性
                emp.setSal(oldSal.add(new BigDecimal("2000")));
                emp.setJob("高级副总经理");
                
                int rows = mapper.updateEmpXml(emp);
                System.out.println("影响行数: " + rows);
                System.out.println("薪资更新: " + oldSal + " → " + emp.getSal());
                
                sqlSession.commit();
            } else {
                System.out.println("未找到 empno=2 的员工");
            }
        }
    }
}
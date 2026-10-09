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
 * 【测试类】XML方式 - 新增 + 自增主键回填
 * 
 * 功能说明：
 *   测试使用 XML方式 的 Mapper 向 emp 表中插入一条新员工记录，
 *   并演示 XML 中如何配置自增主键回填。
 * 
 * XML 中主键回填的配置（EmpAddXmlMapper.xml）：
 *   <insert id="addEmpXml" useGeneratedKeys="true" keyProperty="empno">
 *       INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno)
 *       VALUES(#{ename}, #{job}, #{mgr}, #{hiredate}, #{sal}, #{comm}, #{deptno})
 *   </insert>
 * 
 * 核心属性：
 *   useGeneratedKeys="true"  -> 启用自增主键获取
 *   keyProperty="empno"      -> 将主键值回填到 Emp 对象的 empno 属性
 *   keyColumn="empno"        -> （可选）指定数据库中的主键列名
 * 
 * 注意：
 *   XML 中使用 #{属性名} 来引用 Emp 对象的属性（如 #{ename} 对应 emp.getEname()）
 *   这是因为 MyBatis 会自动将 Emp 对象的属性映射为 SQL 参数
 */
public class EmpTestAddXml {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * 测试 XML 方式新增员工
     * 
     * 与注解方式的区别只在 SQL 定义的位置不同：
     *   - 注解：SQL 写在 @Insert 注解中
     *   - XML：SQL 写在 EmpAddXmlMapper.xml 的 <insert> 标签中
     * 
     * 其他逻辑完全一致：创建对象 -> 插入 -> 主键回填 -> 提交事务
     */
    @Test
    public void testAddEmpXml() {
        System.out.println("========== 【XML方式】新增员工 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            
            EmpAddXmlMapper mapper = sqlSession.getMapper(EmpAddXmlMapper.class);

            Emp emp = new Emp();
            emp.setEname("XML_新增_" + System.currentTimeMillis());
            emp.setJob("测试工程师");
            emp.setMgr(8);
            emp.setHiredate(new Date());
            emp.setSal(new BigDecimal("12000"));
            emp.setComm(new BigDecimal("2000"));
            emp.setDeptno(20);

            int rows = mapper.addEmpXml(emp);
            System.out.println("影响行数: " + rows);
            System.out.println("自增主键回填 - empno: " + emp.getEmpno());

            // 提交事务，数据才会真正写入数据库
            sqlSession.commit();
        }
    }
}
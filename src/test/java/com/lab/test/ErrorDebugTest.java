package com.lab.test;

import com.lab.entity.Emp;
import com.lab.mapper.ErrorDemoMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ErrorDebugTest {

    /**
     * 错误1：参数名写错 #{empname}（应为 #{empName}）
     * 预期现象：抛出 org.apache.ibatis.binding.BindingException
     *          Parameter 'empname' not found. Available parameters are [empName, param1]
     * 排查方法：查看日志中的 BindingException，定位到映射文件中 #{empname}，
     *          修正为 #{empName}（与 Emp 实体类属性名一致）
     */
    @Test
    public void testError1_ParameterBinding() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            ErrorDemoMapper mapper = session.getMapper(ErrorDemoMapper.class);
            List<Emp> list = mapper.selectAllWithError1();
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("=== 错误1：参数绑定异常 ===");
            System.out.println("异常类型: " + e.getClass().getName());
            System.out.println("异常信息: " + e.getMessage());
            System.out.println("排查结论: 映射文件中 #{empname} 应改为 #{empName}");
        }
    }

    /**
     * 错误2：resultMap 中 column 写错字段名 emp_nme（应为 emp_name）
     * 预期现象：不报错，但 empName 属性映射为 null
     * 排查方法：观察日志中 SQL 执行正常、返回行数正常，
     *         ,但输出对象中 empName=null，
     *          检查 resultMap 发现 column="emp_nme" 与数据库字段 emp_name 不匹配
     */
    @Test
    public void testError2_ResultMapNull() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            ErrorDemoMapper mapper = session.getMapper(ErrorDemoMapper.class);
            List<Emp> list = mapper.selectAllWithError2();
            System.out.println("=== 错误2：结果映射为 null ===");
            list.forEach(e -> {
                System.out.println(e);
                if (e.getEmpName() == null) {
                    System.out.println("  >> empName 为 null！排查: resultMap 中 column='emp_nme' 应改为 'emp_name'");
                }
            });
        }
    }

    /**
     * 错误3：缺少 useGeneratedKeys 属性，插入后主键不回填
     * 预期现象：不报错，但 emp.getEmpId() 返回 null
     * 排查方法：插入成功后打印 empId，发现为 null，
     *          检查 <insert> 标签发现缺少 useGeneratedKeys="true" keyProperty="empId"
     */
    @Test
    public void testError3_NoPrimaryKeyReturn() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            ErrorDemoMapper mapper = session.getMapper(ErrorDemoMapper.class);
            Emp emp = new Emp();
            emp.setEmpName("测试员工"); emp.setGender("男");
            emp.setDept("研发部"); emp.setPost("工程师");
            emp.setSalary(new BigDecimal("8000"));
            emp.setHireDate(new Date()); emp.setStatus(1);
            int rows = mapper.insertWithError3(emp);
            session.commit();
            System.out.println("=== 错误3：主键未回填 ===");
            System.out.println("插入行数 = " + rows + ", empId = " + emp.getEmpId());
            if (emp.getEmpId() == null) {
                System.out.println("  >> empId 为 null！排查: <insert> 缺少 useGeneratedKeys=\"true\" keyProperty=\"empId\"");
            }
        }
    }
}

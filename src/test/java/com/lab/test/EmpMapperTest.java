package com.lab.test;

import com.lab.entity.Emp;
import com.lab.mapper.EmpMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpMapperTest {

    @Test
    public void testInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = new Emp();
            emp.setEmpName("陈晨"); emp.setGender("女");
            emp.setDept("研发部"); emp.setPost("测试工程师");
            emp.setSalary(new BigDecimal("9000"));
            emp.setHireDate(new Date()); emp.setStatus(1);
            int rows = mapper.insert(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("回填主键 empId = " + emp.getEmpId());
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectById(1);
            emp.setSalary(new BigDecimal("13000"));
            int rows = mapper.update(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            int rows = session.getMapper(EmpMapper.class).deleteById(4);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
        }
    }

    @Test
    public void testSelectByCondition() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp cond = new Emp();
            cond.setEmpName("伟");
            cond.setDept("研发部");
            List<Emp> list = mapper.selectByCondition(cond);
            System.out.println("=== 多条件模糊查询（姓名含'伟' + 部门='研发部'） ===");
            list.forEach(System.out::println);
            org.junit.Assert.assertTrue(list.size() > 0);
        }
    }

    @Test
    public void testUpdateDynamic() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = new Emp();
            emp.setEmpId(1);
            emp.setSalary(new BigDecimal("15000"));
            emp.setPost("高级Java工程师");
            int rows = mapper.updateDynamic(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 动态更新：只修改 salary 和 post ===");
            System.out.println(mapper.selectById(1));
        }
    }

    @Test
    public void testDeleteBatch() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Integer> ids = new ArrayList<>();
            ids.add(5);
            ids.add(6);
            int rows = mapper.deleteBatch(ids);
            session.commit();
            System.out.println("=== 批量删除：影响行数 = " + rows + " ===");
        }
    }

    @Test
    public void testInsertBatch() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> emps = new ArrayList<>();
            Emp e1 = new Emp();
            e1.setEmpName("批量1"); e1.setGender("男"); e1.setDept("研发部");
            e1.setPost("工程师"); e1.setSalary(new BigDecimal("8000"));
            e1.setHireDate(new Date()); e1.setStatus(1);
            Emp e2 = new Emp();
            e2.setEmpName("批量2"); e2.setGender("女"); e2.setDept("市场部");
            e2.setPost("专员"); e2.setSalary(new BigDecimal("7000"));
            e2.setHireDate(new Date()); e2.setStatus(1);
            emps.add(e1);
            emps.add(e2);
            int rows = mapper.insertBatch(emps);
            session.commit();
            System.out.println("=== 批量插入：影响行数 = " + rows + " ===");
        }
    }

    @Test
    public void testSelectByChoose() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            Emp cond1 = new Emp();
            cond1.setEmpName("张伟");
            System.out.println("=== choose：按姓名精确查询 ===");
            mapper.selectByChoose(cond1).forEach(System.out::println);

            Emp cond2 = new Emp();
            cond2.setDept("研发部");
            System.out.println("=== choose：按部门查询 ===");
            mapper.selectByChoose(cond2).forEach(System.out::println);

            Emp cond3 = new Emp();
            System.out.println("=== choose：无条件查全部 ===");
            mapper.selectByChoose(cond3).forEach(System.out::println);
        }
    }
}
package com.lab.test;

import com.lab.entity.Emp;
import com.lab.entity.EmpSalgradeVO;
import com.lab.mapper.EmpAnnotationMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EmpAnnotationMapperTest {

    @Test
    public void testSelectAll() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpAnnotationMapper mapper = session.getMapper(EmpAnnotationMapper.class);
            List<Emp> list = mapper.selectAll();
            System.out.println("=== 注解方式：查询全部 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testSelectById() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpAnnotationMapper mapper = session.getMapper(EmpAnnotationMapper.class);
            Emp emp = mapper.selectById(1);
            System.out.println("=== 注解方式：按ID查询 ===");
            System.out.println(emp);
            org.junit.Assert.assertNotNull(emp);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpAnnotationMapper mapper = session.getMapper(EmpAnnotationMapper.class);
            Emp emp = new Emp();
            emp.setEmpName("刘芳"); emp.setGender("女");
            emp.setDept("市场部"); emp.setPost("市场经理");
            emp.setSalary(new BigDecimal("11000"));
            emp.setHireDate(new Date()); emp.setStatus(1);
            int rows = mapper.insert(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 注解方式：插入成功，回填主键 empId = " + emp.getEmpId() + " ===");
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpAnnotationMapper mapper = session.getMapper(EmpAnnotationMapper.class);
            Emp emp = mapper.selectById(1);
            emp.setSalary(new BigDecimal("16000"));
            int rows = mapper.update(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 注解方式：更新成功 ===");
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpAnnotationMapper mapper = session.getMapper(EmpAnnotationMapper.class);
            Emp emp = new Emp();
            emp.setEmpName("待删注解"); emp.setGender("女");
            emp.setDept("市场部"); emp.setPost("专员");
            emp.setSalary(new BigDecimal("5000"));
            emp.setHireDate(new Date()); emp.setStatus(1);
            mapper.insert(emp);
            session.commit();
            int id = emp.getEmpId();
            int rows = mapper.deleteById(id);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 注解方式：删除成功，empId = " + id + " ===");
        }
    }

    @Test
    public void testSelectEmpWithSalgrade() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpAnnotationMapper mapper = session.getMapper(EmpAnnotationMapper.class);
            List<EmpSalgradeVO> list = mapper.selectEmpWithSalgrade();
            System.out.println("=== 注解方式：多表查询（员工+薪资等级） ===");
            list.forEach(System.out::println);
            org.junit.Assert.assertTrue(list.size() > 0);
        }
    }
}
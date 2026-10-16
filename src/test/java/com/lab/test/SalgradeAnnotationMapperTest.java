package com.lab.test;

import com.lab.entity.Salgrade;
import com.lab.mapper.SalgradeAnnotationMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class SalgradeAnnotationMapperTest {

    @Test
    public void testSelectAll() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeAnnotationMapper mapper = session.getMapper(SalgradeAnnotationMapper.class);
            List<Salgrade> list = mapper.selectAll();
            System.out.println("=== 注解方式：查询全部 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testSelectById() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeAnnotationMapper mapper = session.getMapper(SalgradeAnnotationMapper.class);
            Salgrade sg = mapper.selectById(3);
            System.out.println("=== 注解方式：按等级查询 ===");
            System.out.println(sg);
            org.junit.Assert.assertNotNull(sg);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeAnnotationMapper mapper = session.getMapper(SalgradeAnnotationMapper.class);
            Salgrade sg = new Salgrade();
            sg.setLosal(new BigDecimal("200000"));
            sg.setHisal(new BigDecimal("999999"));
            int rows = mapper.insert(sg);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 注解方式：插入成功，回填主键 grade = " + sg.getGrade() + " ===");
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeAnnotationMapper mapper = session.getMapper(SalgradeAnnotationMapper.class);
            Salgrade sg = mapper.selectById(2);
            sg.setHisal(new BigDecimal("9000"));
            int rows = mapper.update(sg);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 注解方式：更新成功 ===");
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeAnnotationMapper mapper = session.getMapper(SalgradeAnnotationMapper.class);
            Salgrade sg = new Salgrade();
            sg.setLosal(new BigDecimal("0"));
            sg.setHisal(new BigDecimal("100"));
            mapper.insert(sg);
            session.commit();
            int grade = sg.getGrade();
            int rows = mapper.deleteById(grade);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== 注解方式：删除成功，grade = " + grade + " ===");
        }
    }
}
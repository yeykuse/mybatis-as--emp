package com.lab.test;

import com.lab.entity.Salgrade;
import com.lab.mapper.SalgradeMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class SalgradeXmlMapperTest {

    @Test
    public void testSelectAll() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeMapper mapper = session.getMapper(SalgradeMapper.class);
            List<Salgrade> list = mapper.selectAllXml();
            System.out.println("=== XML方式：查询全部 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testSelectById() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeMapper mapper = session.getMapper(SalgradeMapper.class);
            Salgrade sg = mapper.selectByIdXml(3);
            System.out.println("=== XML方式：按等级查询 ===");
            System.out.println(sg);
            org.junit.Assert.assertNotNull(sg);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeMapper mapper = session.getMapper(SalgradeMapper.class);
            Salgrade sg = new Salgrade();
            sg.setLosal(new BigDecimal("100000"));
            sg.setHisal(new BigDecimal("999999"));
            int rows = mapper.insertXml(sg);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== XML方式：插入成功，回填主键 grade = " + sg.getGrade() + " ===");
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeMapper mapper = session.getMapper(SalgradeMapper.class);
            Salgrade sg = mapper.selectByIdXml(1);
            sg.setHisal(new BigDecimal("6000"));
            int rows = mapper.updateXml(sg);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== XML方式：更新成功 ===");
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            SalgradeMapper mapper = session.getMapper(SalgradeMapper.class);
            Salgrade sg = new Salgrade();
            sg.setLosal(new BigDecimal("0"));
            sg.setHisal(new BigDecimal("100"));
            mapper.insertXml(sg);
            session.commit();
            int grade = sg.getGrade();
            int rows = mapper.deleteByIdXml(grade);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== XML方式：删除成功，grade = " + grade + " ===");
        }
    }
}
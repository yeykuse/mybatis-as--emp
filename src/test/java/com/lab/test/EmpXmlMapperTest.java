package com.lab.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lab.entity.Emp;
import com.lab.entity.EmpSalgradeVO;
import com.lab.mapper.EmpMapper;
import com.lab.util.MyBatisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpXmlMapperTest {

    @Test
    public void testSelectAll() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> list = mapper.selectAllXml();
            System.out.println("=== XML方式：查询全部 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testSelectById() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectByIdXml(1);
            System.out.println("=== XML方式：按ID查询 ===");
            System.out.println(emp);
            org.junit.Assert.assertNotNull(emp);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = new Emp();
            emp.setEmpName("陈晨"); emp.setGender("女");
            emp.setDept("研发部"); emp.setPost("测试工程师");
            emp.setSalary(new BigDecimal("9000"));
            emp.setHireDate(new Date()); emp.setStatus(1);
            int rows = mapper.insertXml(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== XML方式：插入成功，回填主键 empId = " + emp.getEmpId() + " ===");
        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectByIdXml(1);
            emp.setSalary(new BigDecimal("13000"));
            int rows = mapper.updateXml(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== XML方式：更新成功 ===");
        }
    }

    @Test
    public void testDelete() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = new Emp();
            emp.setEmpName("待删XML"); emp.setGender("男");
            emp.setDept("研发部"); emp.setPost("工程师");
            emp.setSalary(new BigDecimal("5000"));
            emp.setHireDate(new Date()); emp.setStatus(1);
            mapper.insertXml(emp);
            session.commit();
            int id = emp.getEmpId();
            int rows = mapper.deleteByIdXml(id);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);
            System.out.println("=== XML方式：删除成功，empId = " + id + " ===");
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
            System.out.println("=== XML方式：多条件模糊查询（姓名含'伟' + 部门='研发部'） ===");
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
            System.out.println("=== XML方式：动态更新，只修改 salary 和 post ===");
            System.out.println(mapper.selectByIdXml(1));
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
            System.out.println("=== XML方式：批量删除，影响行数 = " + rows + " ===");
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
            System.out.println("=== XML方式：批量插入，影响行数 = " + rows + " ===");
        }
    }

    @Test
    public void testSelectByChoose() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            Emp cond1 = new Emp();
            cond1.setEmpName("张伟");
            System.out.println("=== XML方式：choose 按姓名精确查询 ===");
            mapper.selectByChoose(cond1).forEach(System.out::println);

            Emp cond2 = new Emp();
            cond2.setDept("研发部");
            System.out.println("=== XML方式：choose 按部门查询 ===");
            mapper.selectByChoose(cond2).forEach(System.out::println);

            Emp cond3 = new Emp();
            System.out.println("=== XML方式：choose 无条件查全部 ===");
            mapper.selectByChoose(cond3).forEach(System.out::println);
        }
    }

    @Test
    public void testBaseMapperCRUD() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            System.out.println("=== BaseMapper：selectById ===");
            Emp emp = mapper.selectById(1);
            System.out.println(emp);

            System.out.println("=== BaseMapper：selectList（全部） ===");
            List<Emp> all = mapper.selectList(null);
            all.forEach(System.out::println);

            System.out.println("=== BaseMapper：updateById ===");
            emp.setSalary(new BigDecimal("14000"));
            int rows = mapper.updateById(emp);
            session.commit();
            org.junit.Assert.assertEquals(1, rows);

            System.out.println("=== BaseMapper：deleteById ===");
            int delRows = mapper.deleteById(10);
            session.commit();
            System.out.println("删除影响行数 = " + delRows);
        }
    }

    @Test
    public void testQueryWrapper() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            String name = "伟";
            String gender = "男";
            BigDecimal minSalary = new BigDecimal("8000");

            QueryWrapper<Emp> wrapper = new QueryWrapper<>();
            wrapper.like(StringUtils.isNotBlank(name), "emp_name", name)
                   .eq(StringUtils.isNotBlank(gender), "gender", gender)
                   .eq("status", 1)
                   .ge(minSalary != null, "salary", minSalary)
                   .orderByDesc("salary");

            List<Emp> list = mapper.selectList(wrapper);
            System.out.println("=== QueryWrapper 多条件查询 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testLambdaQueryWrapper() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            String name = "伟";

            LambdaQueryWrapper<Emp> lw = new LambdaQueryWrapper<>();
            lw.like(Emp::getEmpName, name)
              .eq(Emp::getStatus, 1)
              .orderByDesc(Emp::getSalary);

            List<Emp> list = mapper.selectList(lw);
            System.out.println("=== LambdaQueryWrapper 查询 ===");
            list.forEach(System.out::println);
        }
    }

    @Test
    public void testPage() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);

            QueryWrapper<Emp> wrapper = new QueryWrapper<>();
            wrapper.eq("status", 1).orderByDesc("salary");

            Page<Emp> page = new Page<>(1, 3);
            Page<Emp> result = mapper.selectPage(page, wrapper);

            System.out.println("=== 分页查询：第1页，每页3条 ===");
            System.out.println("总记录数 = " + result.getTotal());
            System.out.println("总页数 = " + result.getPages());
            result.getRecords().forEach(System.out::println);
        }
    }

    @Test
    public void testSelectEmpWithSalgrade() {
        try (SqlSession session = MyBatisUtil.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<EmpSalgradeVO> list = mapper.selectEmpWithSalgradeXml();
            System.out.println("=== XML方式：多表查询（员工+薪资等级） ===");
            list.forEach(System.out::println);
            org.junit.Assert.assertTrue(list.size() > 0);
        }
    }
}
package com.lab.test;

import com.lab.entity.Emp;
import com.lab.mapper.EmpMapper;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.InputStream;
import java.util.List;

public class MyBatisDemo {
    public static void main(String[] args) throws Exception {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new MybatisSqlSessionFactoryBuilder().build(in);
        try (SqlSession session = factory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            List<Emp> list = mapper.selectAll();
            list.forEach(System.out::println);
        }
    }
}
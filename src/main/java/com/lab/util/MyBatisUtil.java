package com.lab.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private static final SqlSessionFactory FACTORY;

    static {
        try {
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            FACTORY = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis 配置文件加载失败", e);
        }
    }

    public static SqlSession openSession() {
        return FACTORY.openSession();
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return FACTORY;
    }
}

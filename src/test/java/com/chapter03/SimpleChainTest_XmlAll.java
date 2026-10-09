package com.chapter03;

import com.entity.SupplierGoodsUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public class SimpleChainTest_XmlAll {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void test1_FindBySupplierAndGoods() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SimpleChainMapper mapper = sqlSession.getMapper(SimpleChainMapper.class);
            List<SupplierGoodsUser> list = mapper.findBySupplierAndGoodsXml("华为", "手机");
            System.out.println("===== 【XML 2参】供应商名 + 商品名 → " + list.size() + "条 =====");
            for (SupplierGoodsUser row : list) { System.out.println(row); }
        }
    }

    @Test
    public void test2_FindBySupplierUserDate() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SimpleChainMapper mapper = sqlSession.getMapper(SimpleChainMapper.class);
            List<SupplierGoodsUser> list = mapper.findBySupplierUserDateXml(1, "张", "2026-01-01", "2026-12-31");
            System.out.println("===== 【XML 4参】供应商ID + 用户名 + 日期范围 → " + list.size() + "条 =====");
            for (SupplierGoodsUser row : list) { System.out.println(row); }
        }
    }

    @Test
    public void test3_FindBySupplierIdAndSku() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SimpleChainMapper mapper = sqlSession.getMapper(SimpleChainMapper.class);
            List<SupplierGoodsUser> list = mapper.findBySupplierIdAndSku(2, "SKU-006");
            System.out.println("===== 【XML 2参】供应商ID + SKU → " + list.size() + "条 =====");
            for (SupplierGoodsUser row : list) { System.out.println(row); }
        }
    }

    @Test
    public void test4_FindByUserAndGoodsPriceRange() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SimpleChainMapper mapper = sqlSession.getMapper(SimpleChainMapper.class);
            List<SupplierGoodsUser> list = mapper.findByUserAndGoodsPriceRange("李", new BigDecimal("2000"), new BigDecimal("4000"));
            System.out.println("===== 【XML 3参】用户名 + 商品价格范围 → " + list.size() + "条 =====");
            for (SupplierGoodsUser row : list) { System.out.println(row); }
        }
    }

    @Test
    public void test5_FindBySupplierGoodsAndDate() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SimpleChainMapper mapper = sqlSession.getMapper(SimpleChainMapper.class);
            List<SupplierGoodsUser> list = mapper.findBySupplierGoodsAndDate(1, "手机", "2026-01-01", "2026-06-30");
            System.out.println("===== 【XML 4参】供应商ID + 商品名 + 日期范围 → " + list.size() + "条 =====");
            for (SupplierGoodsUser row : list) { System.out.println(row); }
        }
    }
}
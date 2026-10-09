package com.chapter01;

import com.entity.Goods;
import com.entity.ProductWithGoods;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class GoodsAdvancedTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testFindByDynamicCondition() {
        System.out.println("========== 【动态SQL】多条件组合查询商品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<Goods> list = mapper.findByDynamicCondition(
                    "耳机", null, 1, new BigDecimal("100"), new BigDecimal("2000"), null, null);
            System.out.println("条件：名称含'耳机' + 价格100~2000 + 在售状态");
            for (Goods g : list) {
                System.out.println(g);
            }
        }
    }

    @Test
    public void testFindAllGoodsWithProducts() {
        System.out.println("========== 【多表关联+resultMap】商品-产品 三表联查 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<ProductWithGoods> list = mapper.findAllGoodsWithProducts();
            System.out.println("SQL: goods LEFT JOIN product_goods LEFT JOIN product");
            System.out.println("共 " + list.size() + " 条记录");
            for (ProductWithGoods row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindGoodsWithProductsById() {
        System.out.println("========== 【多表关联】按商品ID查关联产品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<ProductWithGoods> list = mapper.findGoodsWithProductsById(6);
            System.out.println("商品ID=6（蓝牙耳机-白色）关联的产品：");
            for (ProductWithGoods row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindBySkuList() {
        System.out.println("========== 【foreach IN】按SKU列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<String> skus = Arrays.asList("SKU-001", "SKU-003", "SKU-006");
            List<Goods> list = mapper.findBySkuList(skus);
            System.out.println("SQL: WHERE sku IN ('SKU-001','SKU-003','SKU-006')");
            for (Goods g : list) {
                System.out.println(g);
            }
        }
    }

    @Test
    public void testFindBySkuListAnnotation() {
        System.out.println("========== 【foreach IN 注解方式】按SKU列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<String> skus = Arrays.asList("SKU-009", "SKU-010", "SKU-015");
            List<Goods> list = mapper.findBySkuListAnnotation(skus);
            for (Goods g : list) {
                System.out.println(g);
            }
        }
    }

    @Test
    public void testFindByPriceRangeAndStock() {
        System.out.println("========== 【多参数XML】价格范围 + 最低库存 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<Goods> list = mapper.findByPriceRangeAndStock(
                    new BigDecimal("500"), new BigDecimal("5000"), 200);
            System.out.println("条件: price 500~5000, stock>=200");
            for (Goods g : list) {
                System.out.println(g);
            }
        }
    }

    @Test
    public void testFindByNameAndStatus() {
        System.out.println("========== 【多参数XML】名称模糊 + 状态 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsAdvancedMapper mapper = sqlSession.getMapper(GoodsAdvancedMapper.class);
            List<Goods> list = mapper.findByNameAndStatus("笔记本", 1);
            System.out.println("条件: 名称含'笔记本' + 在售状态");
            for (Goods g : list) {
                System.out.println(g);
            }
        }
    }
}
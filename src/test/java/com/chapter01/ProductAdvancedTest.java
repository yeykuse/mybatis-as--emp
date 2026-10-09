package com.chapter01;

import com.entity.Product;
import com.entity.ProductWithCategory;
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

public class ProductAdvancedTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testFindByDynamicCondition() {
        System.out.println("========== 【动态SQL】多条件组合查询产品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<Product> list = mapper.findByDynamicCondition("手机", null, 1, new BigDecimal("1000"), new BigDecimal("10000"));
            System.out.println("条件：名称含'手机' + 价格1000~10000 + 上架状态");
            for (Product p : list) {
                System.out.println(p);
            }
        }
    }

    @Test
    public void testFindAllProductWithCategories() {
        System.out.println("========== 【多表关联+resultMap】产品-分类 三表联查 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<ProductWithCategory> list = mapper.findAllProductWithCategories();
            System.out.println("SQL: product LEFT JOIN product_category LEFT JOIN category");
            System.out.println("共 " + list.size() + " 条记录");
            for (ProductWithCategory row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindAllProductWithGoods() {
        System.out.println("========== 【多表关联+resultMap】产品-商品 三表联查 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<ProductWithGoods> list = mapper.findAllProductWithGoods();
            System.out.println("SQL: product LEFT JOIN product_goods LEFT JOIN goods");
            System.out.println("共 " + list.size() + " 条记录");
            for (ProductWithGoods row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindProductWithCategoriesById() {
        System.out.println("========== 【多表关联】按产品ID查关联分类 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<ProductWithCategory> list = mapper.findProductWithCategoriesById(1);
            System.out.println("产品ID=1 及其关联的分类：");
            for (ProductWithCategory row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindProductWithGoodsById() {
        System.out.println("========== 【多表关联】按产品ID查关联商品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<ProductWithGoods> list = mapper.findProductWithGoodsById(1);
            System.out.println("产品ID=1 及其关联的商品：");
            for (ProductWithGoods row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindByCodeList() {
        System.out.println("========== 【foreach IN】按产品编码列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<String> codes = Arrays.asList("PROD-001", "PROD-003", "PROD-005");
            List<Product> list = mapper.findByCodeList(codes);
            System.out.println("SQL生成: WHERE product_code IN ('PROD-001','PROD-003','PROD-005')");
            for (Product p : list) {
                System.out.println(p);
            }
        }
    }

    @Test
    public void testFindByIdList() {
        System.out.println("========== 【foreach IN】按ID列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<Integer> ids = Arrays.asList(1, 2, 3);
            List<Product> list = mapper.findByIdList(ids);
            for (Product p : list) {
                System.out.println(p);
            }
        }
    }

    @Test
    public void testFindByIdListAnnotation() {
        System.out.println("========== 【foreach IN 注解方式】按ID列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<Integer> ids = Arrays.asList(1, 2, 4);
            List<Product> list = mapper.findByIdListAnnotation(ids);
            System.out.println("使用 @Select + <script> 标签实现动态foreach");
            for (Product p : list) {
                System.out.println(p);
            }
        }
    }

    @Test
    public void testBatchUpdatePrice() {
        System.out.println("========== 【foreach 批量更新】批量修改价格 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);

            Product p1 = new Product();
            p1.setId(1);
            p1.setPrice(new BigDecimal("5099.00"));

            Product p2 = new Product();
            p2.setId(2);
            p2.setPrice(new BigDecimal("4099.00"));

            List<Product> list = Arrays.asList(p1, p2);
            int rows = mapper.batchUpdatePrice(list);
            System.out.println("批量更新行数: " + rows);
            sqlSession.commit();
        }
    }

    @Test
    public void testFindByPriceRangeAndStatus() {
        System.out.println("========== 【多参数XML】价格范围 + 状态 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<Product> list = mapper.findByPriceRangeAndStatus(
                    new BigDecimal("1000"), new BigDecimal("5000"), 1);
            System.out.println("条件: price 1000~5000, status=1");
            for (Product p : list) {
                System.out.println(p);
            }
        }
    }

    @Test
    public void testFindByNameAndPriceRange() {
        System.out.println("========== 【多参数XML】名称模糊 + 价格范围 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductAdvancedMapper mapper = sqlSession.getMapper(ProductAdvancedMapper.class);
            List<Product> list = mapper.findByNameAndPriceRange(
                    "手机", new BigDecimal("3000"), new BigDecimal("8000"));
            System.out.println("条件: 名称含'手机' + price 3000~8000");
            for (Product p : list) {
                System.out.println(p);
            }
        }
    }
}
package com.chapter01;

import com.entity.Category;
import com.entity.Goods;
import com.entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindAll() {
        System.out.println("========== 查询所有产品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Product> products = productMapper.findAll();
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Test
    public void testFindById() {
        System.out.println("========== 根据ID查询产品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            Product product = productMapper.findById(1);
            System.out.println(product);
        }
    }

    @Test
    public void testFindByNameLike() {
        System.out.println("========== 按名称模糊查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Product> products = productMapper.findByNameLike("手机");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Test
    public void testFindByMap() {
        System.out.println("========== 测试Map多条件（XML方式） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("productName", "手机");
            List<Product> products = productMapper.findByMap(map);
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Test
    public void testFindGoodsByProductId() {
        System.out.println("========== 查询产品关联的商品（多对多） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Goods> goodsList = productMapper.findGoodsByProductId(1);
            System.out.println("产品ID=1 关联的商品：");
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }

    @Test
    public void testFindCategoriesByProductId() {
        System.out.println("========== 查询产品关联的分类（多对多） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Category> categories = productMapper.findCategoriesByProductId(1);
            System.out.println("产品ID=1 关联的分类：");
            for (Category category : categories) {
                System.out.println(category);
            }
        }
    }

    @Test
    public void testInsertAndDelete() {
        System.out.println("========== 新增产品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            Product product = new Product();
            product.setProductName("测试产品");
            product.setProductCode("TEST-001");
            product.setDescription("这是一个测试产品");
            product.setPrice(new BigDecimal("999.00"));
            product.setStatus(1);
            int result = productMapper.insertProduct(product);
            sqlSession.commit();
            System.out.println("新增结果：" + result + "，生成ID：" + product.getId());

            int delResult = productMapper.deleteById(product.getId());
            sqlSession.commit();
            System.out.println("删除结果：" + delResult);
        }
    }
}
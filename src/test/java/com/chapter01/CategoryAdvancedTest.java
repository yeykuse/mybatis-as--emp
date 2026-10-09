package com.chapter01;

import com.entity.Category;
import com.entity.ProductWithCategory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class CategoryAdvancedTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testFindByDynamicCondition() {
        System.out.println("========== 【动态SQL】多条件组合查询分类 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryAdvancedMapper mapper = sqlSession.getMapper(CategoryAdvancedMapper.class);
            List<Category> list = mapper.findByDynamicCondition("手机", null, null);
            System.out.println("条件：名称含'手机'");
            for (Category c : list) {
                System.out.println(c);
            }
        }
    }

    @Test
    public void testFindAllCategoriesWithProducts() {
        System.out.println("========== 【多表关联+resultMap】分类-产品 三表联查 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryAdvancedMapper mapper = sqlSession.getMapper(CategoryAdvancedMapper.class);
            List<ProductWithCategory> list = mapper.findAllCategoriesWithProducts();
            System.out.println("SQL: category LEFT JOIN product_category LEFT JOIN product");
            System.out.println("共 " + list.size() + " 条记录");
            for (ProductWithCategory row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindCategoriesWithProductsById() {
        System.out.println("========== 【多表关联】按分类ID查关联产品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryAdvancedMapper mapper = sqlSession.getMapper(CategoryAdvancedMapper.class);
            List<ProductWithCategory> list = mapper.findCategoriesWithProductsById(5);
            System.out.println("分类ID=5（智能手机）关联的产品：");
            for (ProductWithCategory row : list) {
                System.out.println(row);
            }
        }
    }

    @Test
    public void testFindByIdList() {
        System.out.println("========== 【foreach IN】按分类ID列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryAdvancedMapper mapper = sqlSession.getMapper(CategoryAdvancedMapper.class);
            List<Integer> ids = Arrays.asList(1, 2, 3, 13);
            List<Category> list = mapper.findByIdList(ids);
            System.out.println("SQL: WHERE id IN (1,2,3,13)");
            for (Category c : list) {
                System.out.println(c);
            }
        }
    }

    @Test
    public void testFindByIdListAnnotation() {
        System.out.println("========== 【foreach IN 注解方式】按ID列表查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryAdvancedMapper mapper = sqlSession.getMapper(CategoryAdvancedMapper.class);
            List<Integer> ids = Arrays.asList(5, 6, 7);
            List<Category> list = mapper.findByIdListAnnotation(ids);
            for (Category c : list) {
                System.out.println(c);
            }
        }
    }

    @Test
    public void testFindByNameAndParentId() {
        System.out.println("========== 【多参数XML】名称模糊 + 父分类ID ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryAdvancedMapper mapper = sqlSession.getMapper(CategoryAdvancedMapper.class);
            List<Category> list = mapper.findByNameAndParentId("手机", 1);
            System.out.println("条件: 名称含'手机' + parentId=1（手机数码子分类）");
            for (Category c : list) {
                System.out.println(c);
            }
        }
    }
}
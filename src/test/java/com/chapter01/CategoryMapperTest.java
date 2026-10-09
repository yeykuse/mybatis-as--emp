package com.chapter01;

import com.entity.Category;
import com.entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindAll() {
        System.out.println("========== 查询所有分类 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            List<Category> categories = categoryMapper.findAll();
            for (Category category : categories) {
                System.out.println(category);
            }
        }
    }

    @Test
    public void testFindById() {
        System.out.println("========== 根据ID查询分类 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            Category category = categoryMapper.findById(1);
            System.out.println(category);
        }
    }

    @Test
    public void testFindByParentId() {
        System.out.println("========== 查询子分类 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            List<Category> categories = categoryMapper.findByParentId(1);
            System.out.println("手机数码的子分类：");
            for (Category category : categories) {
                System.out.println(category);
            }
        }
    }

    @Test
    public void testFindByNameLike() {
        System.out.println("========== 按名称模糊查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            List<Category> categories = categoryMapper.findByNameLike("手机");
            for (Category category : categories) {
                System.out.println(category);
            }
        }
    }

    @Test
    public void testFindByMap() {
        System.out.println("========== 测试Map多条件（XML方式） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("parentId", 1);
            List<Category> categories = categoryMapper.findByMap(map);
            for (Category category : categories) {
                System.out.println(category);
            }
        }
    }

    @Test
    public void testFindProductsByCategoryId() {
        System.out.println("========== 查询分类下的产品（多对多反向） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            List<Product> products = categoryMapper.findProductsByCategoryId(5);
            System.out.println("分类ID=5（智能手机）下的产品：");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Test
    public void testInsertAndDelete() {
        System.out.println("========== 新增分类 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            Category category = new Category();
            category.setCategoryName("测试分类");
            category.setParentId(0);
            category.setSortOrder(99);
            category.setDescription("测试分类描述");
            int result = categoryMapper.insertCategory(category);
            sqlSession.commit();
            System.out.println("新增结果：" + result + "，生成ID：" + category.getId());

            int delResult = categoryMapper.deleteById(category.getId());
            sqlSession.commit();
            System.out.println("删除结果：" + delResult);
        }
    }
}
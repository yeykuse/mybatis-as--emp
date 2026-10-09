package com.chapter01;

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

public class GoodsMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindAll() {
        System.out.println("========== 查询所有商品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            List<Goods> goodsList = goodsMapper.findAll();
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }

    @Test
    public void testFindById() {
        System.out.println("========== 根据ID查询商品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            Goods goods = goodsMapper.findById(1);
            System.out.println(goods);
        }
    }

    @Test
    public void testFindByNameLike() {
        System.out.println("========== 按名称模糊查询 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            List<Goods> goodsList = goodsMapper.findByNameLike("耳机");
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }

    @Test
    public void testFindByMap() {
        System.out.println("========== 测试Map多条件（XML方式） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("goodsName", "耳机");
            List<Goods> goodsList = goodsMapper.findByMap(map);
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }
    }

    @Test
    public void testFindProductsByGoodsId() {
        System.out.println("========== 查询商品关联的产品（多对多反向） ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            List<Product> products = goodsMapper.findProductsByGoodsId(1);
            System.out.println("商品ID=1 关联的产品：");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Test
    public void testInsertAndDelete() {
        System.out.println("========== 新增商品 ==========");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            Goods goods = new Goods();
            goods.setGoodsName("测试商品");
            goods.setSku("TEST-SKU");
            goods.setSpecification("测试规格");
            goods.setStock(100);
            goods.setPrice(new BigDecimal("199.00"));
            goods.setStatus(1);
            int result = goodsMapper.insertGoods(goods);
            sqlSession.commit();
            System.out.println("新增结果：" + result + "，生成ID：" + goods.getId());

            int delResult = goodsMapper.deleteById(goods.getId());
            sqlSession.commit();
            System.out.println("删除结果：" + delResult);
        }
    }
}
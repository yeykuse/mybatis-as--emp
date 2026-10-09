package com.chapter01;

import com.entity.Goods;
import com.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {

    List<Goods> findByMap(Map<String, Object> map);

    @Select("SELECT * FROM goods")
    List<Goods> findAll();

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods findById(@Param("id") Integer id);

    @Select("SELECT * FROM goods WHERE goods_name LIKE CONCAT('%', #{value}, '%')")
    List<Goods> findByNameLike(String value);

    List<Product> findProductsByGoodsId(@Param("goodsId") Integer goodsId);

    int insertGoods(@Param("goods") Goods goods);

    int updateGoods(@Param("goods") Goods goods);

    int deleteById(@Param("id") Integer id);
}
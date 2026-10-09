package com.chapter01;

import com.entity.Category;
import com.entity.Goods;
import com.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    List<Product> findByMap(Map<String, Object> map);

    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(@Param("id") Integer id);

    @Select("SELECT * FROM product WHERE product_name LIKE CONCAT('%', #{value}, '%')")
    List<Product> findByNameLike(String value);

    List<Goods> findGoodsByProductId(@Param("productId") Integer productId);

    List<Category> findCategoriesByProductId(@Param("productId") Integer productId);

    int insertProduct(@Param("product") Product product);

    int updateProduct(@Param("product") Product product);

    int deleteById(@Param("id") Integer id);
}
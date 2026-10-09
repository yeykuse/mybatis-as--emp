package com.chapter01;

import com.entity.Category;
import com.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {

    List<Category> findByMap(Map<String, Object> map);

    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(@Param("id") Integer id);

    @Select("SELECT * FROM category WHERE parent_id = #{parentId}")
    List<Category> findByParentId(@Param("parentId") Integer parentId);

    @Select("SELECT * FROM category WHERE category_name LIKE CONCAT('%', #{value}, '%')")
    List<Category> findByNameLike(String value);

    List<Product> findProductsByCategoryId(@Param("categoryId") Integer categoryId);

    int insertCategory(@Param("category") Category category);

    int updateCategory(@Param("category") Category category);

    int deleteById(@Param("id") Integer id);
}
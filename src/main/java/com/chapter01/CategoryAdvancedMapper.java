package com.chapter01;

import com.entity.Category;
import com.entity.ProductWithCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryAdvancedMapper {

    List<Category> findByDynamicCondition(@Param("categoryName") String categoryName,
                                          @Param("parentId") Integer parentId,
                                          @Param("description") String description);

    List<ProductWithCategory> findAllCategoriesWithProducts();

    List<ProductWithCategory> findCategoriesWithProductsById(@Param("categoryId") Integer categoryId);

    List<Category> findByIdList(@Param("ids") List<Integer> ids);

    List<Category> findByNameAndParentId(@Param("categoryName") String categoryName,
                                         @Param("parentId") Integer parentId);

    @Select("<script>" +
            "SELECT * FROM category WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Category> findByIdListAnnotation(@Param("ids") List<Integer> ids);
}
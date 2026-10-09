package com.chapter01;

import com.entity.Product;
import com.entity.ProductWithCategory;
import com.entity.ProductWithGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface ProductAdvancedMapper {

    List<Product> findByDynamicCondition(@Param("productName") String productName,
                                         @Param("productCode") String productCode,
                                         @Param("status") Integer status,
                                         @Param("minPrice") BigDecimal minPrice,
                                         @Param("maxPrice") BigDecimal maxPrice);

    List<ProductWithCategory> findAllProductWithCategories();

    List<ProductWithGoods> findAllProductWithGoods();

    List<ProductWithCategory> findProductWithCategoriesById(@Param("productId") Integer productId);

    List<ProductWithGoods> findProductWithGoodsById(@Param("productId") Integer productId);

    List<Product> findByCodeList(@Param("codes") List<String> codes);

    List<Product> findByIdList(@Param("ids") List<Integer> ids);

    int batchUpdatePrice(@Param("list") List<Product> list);

    List<Product> findByPriceRangeAndStatus(@Param("minPrice") BigDecimal minPrice,
                                            @Param("maxPrice") BigDecimal maxPrice,
                                            @Param("status") Integer status);

    List<Product> findByNameAndPriceRange(@Param("productName") String productName,
                                          @Param("minPrice") BigDecimal minPrice,
                                          @Param("maxPrice") BigDecimal maxPrice);

    @Select("<script>" +
            "SELECT * FROM product WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Product> findByIdListAnnotation(@Param("ids") List<Integer> ids);
}
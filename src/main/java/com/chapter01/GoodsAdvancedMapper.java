package com.chapter01;

import com.entity.Goods;
import com.entity.ProductWithGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsAdvancedMapper {

    List<Goods> findByDynamicCondition(@Param("goodsName") String goodsName,
                                       @Param("sku") String sku,
                                       @Param("status") Integer status,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice,
                                       @Param("minStock") Integer minStock,
                                       @Param("maxStock") Integer maxStock);

    List<ProductWithGoods> findAllGoodsWithProducts();

    List<ProductWithGoods> findGoodsWithProductsById(@Param("goodsId") Integer goodsId);

    List<Goods> findBySkuList(@Param("skus") List<String> skus);

    List<Goods> findByPriceRangeAndStock(@Param("minPrice") BigDecimal minPrice,
                                         @Param("maxPrice") BigDecimal maxPrice,
                                         @Param("minStock") Integer minStock);

    List<Goods> findByNameAndStatus(@Param("goodsName") String goodsName,
                                    @Param("status") Integer status);

    @Select("<script>" +
            "SELECT * FROM goods WHERE sku IN " +
            "<foreach collection='skus' item='sku' open='(' separator=',' close=')'>" +
            "#{sku}" +
            "</foreach>" +
            "</script>")
    List<Goods> findBySkuListAnnotation(@Param("skus") List<String> skus);
}
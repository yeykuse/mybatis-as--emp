package com.chapter03;

import com.entity.SupplierGoodsUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

public interface SimpleChainMapper {

    @Select("SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "WHERE s.supplier_name LIKE CONCAT('%', #{supplierName}, '%') " +
            "AND g.goods_name LIKE CONCAT('%', #{goodsName}, '%') " +
            "ORDER BY s.id, g.id, u.id")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierAndGoods(
            @Param("supplierName") String supplierName,
            @Param("goodsName") String goodsName);

    @Select("SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "WHERE s.id = #{supplierId} " +
            "AND u.username LIKE CONCAT('%', #{username}, '%') " +
            "AND ug.buy_date >= #{startDate} " +
            "AND ug.buy_date <= #{endDate} " +
            "ORDER BY ug.buy_date DESC")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierUserDate(
            @Param("supplierId") Integer supplierId,
            @Param("username") String username,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<SupplierGoodsUser> findBySupplierAndGoodsXml(
            @Param("supplierName") String supplierName,
            @Param("goodsName") String goodsName);

    List<SupplierGoodsUser> findBySupplierUserDateXml(
            @Param("supplierId") Integer supplierId,
            @Param("username") String username,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<SupplierGoodsUser> findBySupplierIdAndSku(
            @Param("supplierId") Integer supplierId,
            @Param("sku") String sku);

    @Select("SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "WHERE s.id = #{supplierId} " +
            "AND g.sku = #{sku} " +
            "ORDER BY u.id")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierIdAndSkuAnno(
            @Param("supplierId") Integer supplierId,
            @Param("sku") String sku);

    List<SupplierGoodsUser> findByUserAndGoodsPriceRange(
            @Param("username") String username,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    @Select("SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "WHERE u.username LIKE CONCAT('%', #{username}, '%') " +
            "AND g.price >= #{minPrice} " +
            "AND g.price <= #{maxPrice} " +
            "ORDER BY u.id, g.price")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findByUserAndGoodsPriceRangeAnno(
            @Param("username") String username,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    List<SupplierGoodsUser> findBySupplierGoodsAndDate(
            @Param("supplierId") Integer supplierId,
            @Param("goodsName") String goodsName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    @Select("SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "WHERE s.id = #{supplierId} " +
            "AND g.goods_name LIKE CONCAT('%', #{goodsName}, '%') " +
            "AND ug.buy_date >= #{startDate} " +
            "AND ug.buy_date <= #{endDate} " +
            "ORDER BY ug.buy_date DESC")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierGoodsAndDateAnno(
            @Param("supplierId") Integer supplierId,
            @Param("goodsName") String goodsName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    // ========== 动态SQL XML ==========

    List<SupplierGoodsUser> findBySupplierAndGoodsDynamic(
            @Param("supplierName") String supplierName,
            @Param("goodsName") String goodsName);

    List<SupplierGoodsUser> findBySupplierUserDateDynamic(
            @Param("supplierId") Integer supplierId,
            @Param("username") String username,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<SupplierGoodsUser> findBySupplierIdAndSkuDynamic(
            @Param("supplierId") Integer supplierId,
            @Param("sku") String sku);

    List<SupplierGoodsUser> findByUserAndGoodsPriceRangeDynamic(
            @Param("username") String username,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    List<SupplierGoodsUser> findBySupplierGoodsAndDateDynamic(
            @Param("supplierId") Integer supplierId,
            @Param("goodsName") String goodsName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    // ========== 动态SQL注解 ==========

    @Select("<script>" +
            "SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "<where>" +
            "<if test=\"supplierName != null and supplierName != ''\">" +
            "AND s.supplier_name LIKE CONCAT('%', #{supplierName}, '%')" +
            "</if>" +
            "<if test=\"goodsName != null and goodsName != ''\">" +
            "AND g.goods_name LIKE CONCAT('%', #{goodsName}, '%')" +
            "</if>" +
            "</where>" +
            "ORDER BY s.id, g.id, u.id" +
            "</script>")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierAndGoodsDynamicAnno(
            @Param("supplierName") String supplierName,
            @Param("goodsName") String goodsName);

    @Select("<script>" +
            "SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "<where>" +
            "<if test=\"supplierId != null\">" +
            "AND s.id = #{supplierId}" +
            "</if>" +
            "<if test=\"username != null and username != ''\">" +
            "AND u.username LIKE CONCAT('%', #{username}, '%')" +
            "</if>" +
            "<if test=\"startDate != null and startDate != ''\">" +
            "AND ug.buy_date >= #{startDate}" +
            "</if>" +
            "<if test=\"endDate != null and endDate != ''\">" +
            "AND ug.buy_date &lt;= #{endDate}" +
            "</if>" +
            "</where>" +
            "ORDER BY ug.buy_date DESC" +
            "</script>")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierUserDateDynamicAnno(
            @Param("supplierId") Integer supplierId,
            @Param("username") String username,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    @Select("<script>" +
            "SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "<where>" +
            "<if test=\"supplierId != null\">" +
            "AND s.id = #{supplierId}" +
            "</if>" +
            "<if test=\"sku != null and sku != ''\">" +
            "AND g.sku = #{sku}" +
            "</if>" +
            "</where>" +
            "ORDER BY u.id" +
            "</script>")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierIdAndSkuDynamicAnno(
            @Param("supplierId") Integer supplierId,
            @Param("sku") String sku);

    @Select("<script>" +
            "SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "<where>" +
            "<if test=\"username != null and username != ''\">" +
            "AND u.username LIKE CONCAT('%', #{username}, '%')" +
            "</if>" +
            "<if test=\"minPrice != null\">" +
            "AND g.price >= #{minPrice}" +
            "</if>" +
            "<if test=\"maxPrice != null\">" +
            "AND g.price &lt;= #{maxPrice}" +
            "</if>" +
            "</where>" +
            "ORDER BY u.id, g.price" +
            "</script>")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findByUserAndGoodsPriceRangeDynamicAnno(
            @Param("username") String username,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    @Select("<script>" +
            "SELECT s.supplier_name, s.phone AS supplier_phone, " +
            "g.goods_name, g.sku, g.specification, g.price AS goods_price, " +
            "sg.supply_price, " +
            "u.username, u.email, " +
            "ug.quantity AS buy_quantity, ug.buy_price, ug.buy_date " +
            "FROM supplier s " +
            "JOIN supplier_goods sg ON s.id = sg.supplier_id " +
            "JOIN goods g ON sg.goods_id = g.id " +
            "JOIN user_goods ug ON g.id = ug.goods_id " +
            "JOIN user u ON ug.user_id = u.id " +
            "<where>" +
            "<if test=\"supplierId != null\">" +
            "AND s.id = #{supplierId}" +
            "</if>" +
            "<if test=\"goodsName != null and goodsName != ''\">" +
            "AND g.goods_name LIKE CONCAT('%', #{goodsName}, '%')" +
            "</if>" +
            "<if test=\"startDate != null and startDate != ''\">" +
            "AND ug.buy_date >= #{startDate}" +
            "</if>" +
            "<if test=\"endDate != null and endDate != ''\">" +
            "AND ug.buy_date &lt;= #{endDate}" +
            "</if>" +
            "</where>" +
            "ORDER BY ug.buy_date DESC" +
            "</script>")
    @ResultMap("sguMap")
    List<SupplierGoodsUser> findBySupplierGoodsAndDateDynamicAnno(
            @Param("supplierId") Integer supplierId,
            @Param("goodsName") String goodsName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
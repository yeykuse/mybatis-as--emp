package com.chapter02;

import com.entity.SupplierProductCustomer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SupplyChainMapper {

    @Select("SELECT s.supplier_name, s.contact_name, " +
            "p.product_name, p.product_code, " +
            "g.goods_name, g.sku, g.specification, " +
            "c.customer_name, c.phone AS customer_phone, " +
            "si.quantity, si.unit_price, sa.sale_date " +
            "FROM supplier s " +
            "JOIN product p ON s.id = p.supplier_id " +
            "JOIN product_goods pg ON p.id = pg.product_id " +
            "JOIN goods g ON pg.goods_id = g.id " +
            "JOIN sale_item si ON g.id = si.goods_id " +
            "JOIN sale sa ON si.sale_id = sa.id " +
            "JOIN customer c ON sa.customer_id = c.id " +
            "WHERE s.supplier_name LIKE CONCAT('%', #{supplierName}, '%') " +
            "AND p.product_name LIKE CONCAT('%', #{productName}, '%') " +
            "ORDER BY s.id, p.id, c.id")
    List<SupplierProductCustomer> findBySupplierAndProduct(
            @Param("supplierName") String supplierName,
            @Param("productName") String productName);

    @Select("SELECT s.supplier_name, s.contact_name, " +
            "p.product_name, p.product_code, " +
            "g.goods_name, g.sku, g.specification, " +
            "c.customer_name, c.phone AS customer_phone, " +
            "si.quantity, si.unit_price, sa.sale_date " +
            "FROM supplier s " +
            "JOIN product p ON s.id = p.supplier_id " +
            "JOIN product_goods pg ON p.id = pg.product_id " +
            "JOIN goods g ON pg.goods_id = g.id " +
            "JOIN sale_item si ON g.id = si.goods_id " +
            "JOIN sale sa ON si.sale_id = sa.id " +
            "JOIN customer c ON sa.customer_id = c.id " +
            "WHERE s.id = #{supplierId} " +
            "AND c.customer_name LIKE CONCAT('%', #{customerName}, '%') " +
            "AND sa.sale_date >= #{startDate} " +
            "AND sa.sale_date <= #{endDate} " +
            "ORDER BY sa.sale_date DESC")
    List<SupplierProductCustomer> findBySupplierCustomerDate(
            @Param("supplierId") Integer supplierId,
            @Param("customerName") String customerName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<SupplierProductCustomer> findBySupplierIdAndProductCode(
            @Param("supplierId") Integer supplierId,
            @Param("productCode") String productCode);

    List<SupplierProductCustomer> findByCustomerAndGoodsPriceRange(
            @Param("customerName") String customerName,
            @Param("minPrice") java.math.BigDecimal minPrice,
            @Param("maxPrice") java.math.BigDecimal maxPrice);

    List<SupplierProductCustomer> findBySupplierProductAndDate(
            @Param("supplierId") Integer supplierId,
            @Param("productName") String productName,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
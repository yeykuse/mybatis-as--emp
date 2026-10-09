package com.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductWithGoods {
    private Integer id;
    private String productName;
    private String productCode;
    private String description;
    private BigDecimal productPrice;

    private Integer goodsId;
    private String goodsName;
    private String sku;
    private String specification;
    private Integer stock;
    private BigDecimal goodsPrice;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getProductPrice() { return productPrice; }
    public void setProductPrice(BigDecimal productPrice) { this.productPrice = productPrice; }

    public Integer getGoodsId() { return goodsId; }
    public void setGoodsId(Integer goodsId) { this.goodsId = goodsId; }

    public String getGoodsName() { return goodsName; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getSpecification() { return specification; }
    public void setSpecification(String specification) { this.specification = specification; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public BigDecimal getGoodsPrice() { return goodsPrice; }
    public void setGoodsPrice(BigDecimal goodsPrice) { this.goodsPrice = goodsPrice; }

    @Override
    public String toString() {
        return "ProductWithGoods{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productPrice=" + productPrice +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", sku='" + sku + '\'' +
                ", specification='" + specification + '\'' +
                ", stock=" + stock +
                ", goodsPrice=" + goodsPrice +
                '}';
    }
}
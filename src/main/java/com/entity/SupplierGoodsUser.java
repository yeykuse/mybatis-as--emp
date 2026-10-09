package com.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierGoodsUser {
    private String supplierName;
    private String supplierPhone;
    private String goodsName;
    private String sku;
    private String specification;
    private BigDecimal goodsPrice;
    private BigDecimal supplyPrice;
    private String username;
    private String email;
    private Integer buyQuantity;
    private BigDecimal buyPrice;
    private Date buyDate;

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getSupplierPhone() { return supplierPhone; }
    public void setSupplierPhone(String supplierPhone) { this.supplierPhone = supplierPhone; }

    public String getGoodsName() { return goodsName; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getSpecification() { return specification; }
    public void setSpecification(String specification) { this.specification = specification; }

    public BigDecimal getGoodsPrice() { return goodsPrice; }
    public void setGoodsPrice(BigDecimal goodsPrice) { this.goodsPrice = goodsPrice; }

    public BigDecimal getSupplyPrice() { return supplyPrice; }
    public void setSupplyPrice(BigDecimal supplyPrice) { this.supplyPrice = supplyPrice; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getBuyQuantity() { return buyQuantity; }
    public void setBuyQuantity(Integer buyQuantity) { this.buyQuantity = buyQuantity; }

    public BigDecimal getBuyPrice() { return buyPrice; }
    public void setBuyPrice(BigDecimal buyPrice) { this.buyPrice = buyPrice; }

    public Date getBuyDate() { return buyDate; }
    public void setBuyDate(Date buyDate) { this.buyDate = buyDate; }

    @Override
    public String toString() {
        return "\n  ┌ 供应商 ─────────────────────" +
                "\n  │ 名称: " + supplierName +
                "\n  │ 电话: " + supplierPhone +
                "\n  ├ 商品 ──────────────────────" +
                "\n  │ 名称: " + goodsName +
                "\n  │ SKU:  " + sku +
                "\n  │ 规格: " + specification +
                "\n  │ 售价: ¥" + goodsPrice +
                "\n  │ 供货价: ¥" + supplyPrice +
                "\n  ├ 用户 ──────────────────────" +
                "\n  │ 姓名: " + username +
                "\n  │ 邮箱: " + email +
                "\n  │ 购买数量: " + buyQuantity +
                "\n  │ 购买单价: ¥" + buyPrice +
                "\n  │ 购买日期: " + buyDate +
                "\n  └────────────────────────────\n";
    }
}
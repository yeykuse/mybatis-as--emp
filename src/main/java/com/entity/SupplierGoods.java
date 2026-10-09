package com.entity;

import java.util.Date;

public class SupplierGoods {
    private Integer id;
    private Integer supplierId;
    private Integer goodsId;
    private java.math.BigDecimal supplyPrice;
    private Date createdAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSupplierId() { return supplierId; }
    public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

    public Integer getGoodsId() { return goodsId; }
    public void setGoodsId(Integer goodsId) { this.goodsId = goodsId; }

    public java.math.BigDecimal getSupplyPrice() { return supplyPrice; }
    public void setSupplyPrice(java.math.BigDecimal supplyPrice) { this.supplyPrice = supplyPrice; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
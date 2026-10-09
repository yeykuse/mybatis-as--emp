package com.entity;

import java.math.BigDecimal;

public class SaleItem {
    private Integer id;
    private Integer saleId;
    private Integer goodsId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getSaleId() { return saleId; }
    public void setSaleId(Integer saleId) { this.saleId = saleId; }

    public Integer getGoodsId() { return goodsId; }
    public void setGoodsId(Integer goodsId) { this.goodsId = goodsId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    @Override
    public String toString() {
        return "SaleItem{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", goodsId=" + goodsId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
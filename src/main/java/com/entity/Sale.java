package com.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Sale {
    private Integer id;
    private Integer customerId;
    private Date saleDate;
    private BigDecimal totalAmount;
    private Date createdAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Date getSaleDate() { return saleDate; }
    public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", saleDate=" + saleDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
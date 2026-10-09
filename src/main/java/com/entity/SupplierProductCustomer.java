package com.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SupplierProductCustomer {
    private String supplierName;
    private String contactName;
    private String productName;
    private String productCode;
    private String goodsName;
    private String sku;
    private String specification;
    private String customerName;
    private String customerPhone;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Date saleDate;

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getGoodsName() { return goodsName; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getSpecification() { return specification; }
    public void setSpecification(String specification) { this.specification = specification; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public Date getSaleDate() { return saleDate; }
    public void setSaleDate(Date saleDate) { this.saleDate = saleDate; }

    @Override
    public String toString() {
        return "SupplierProductCustomer{" +
                "supplierName='" + supplierName + '\'' +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", sku='" + sku + '\'' +
                ", customerName='" + customerName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", saleDate=" + saleDate +
                '}';
    }
}
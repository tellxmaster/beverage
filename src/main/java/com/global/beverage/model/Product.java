package com.global.beverage.model;

import java.math.BigDecimal;

public class Product {
    private String productId;

    private String name;
    private BigDecimal unitCost;
    private BigDecimal markup;
    private BigDecimal promotion;
    private BigDecimal discountPerUnit;

    public Product(String productId, String name, BigDecimal unitCost, BigDecimal markup, BigDecimal promotion, BigDecimal discountPerUnit) {
        this.productId = productId;
        this.name = name;
        this.unitCost = unitCost;
        this.markup = markup;
        this.promotion = promotion;
        this.discountPerUnit = discountPerUnit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    public BigDecimal getPromotion() {
        return promotion;
    }

    public void setPromotion(BigDecimal promotion) {
        this.promotion = promotion;
    }

    public BigDecimal getDiscountPerUnit() {
        return discountPerUnit;
    }

    public void setDiscountPerUnit(BigDecimal discountPerUnit) {
        this.discountPerUnit = discountPerUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", unitCost=" + unitCost +
                ", markup=" + markup +
                ", promotion=" + promotion +
                ", discountPerUnit=" + discountPerUnit +
                '}';
    }
}

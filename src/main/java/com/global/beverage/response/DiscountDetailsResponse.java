package com.global.beverage.response;

import java.math.BigDecimal;

public class DiscountDetailsResponse {
    private BigDecimal totalAfterDiscounts;
    private BigDecimal basicDiscountApplied;
    private BigDecimal bulkDiscountApplied;

    public DiscountDetailsResponse(BigDecimal totalAfterDiscounts, BigDecimal basicDiscountApplied, BigDecimal bulkDiscountApplied) {
        this.totalAfterDiscounts = totalAfterDiscounts;
        this.basicDiscountApplied = basicDiscountApplied;
        this.bulkDiscountApplied = bulkDiscountApplied;
    }

    // Getters y Setters
    public BigDecimal getTotalAfterDiscounts() {
        return totalAfterDiscounts;
    }

    public BigDecimal getBasicDiscountApplied() {
        return basicDiscountApplied;
    }

    public BigDecimal getBulkDiscountApplied() {
        return bulkDiscountApplied;
    }
}

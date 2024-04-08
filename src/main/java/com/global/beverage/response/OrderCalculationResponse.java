package com.global.beverage.response;

import java.math.BigDecimal;
import java.util.Map;
public class OrderCalculationResponse {
    private BigDecimal totalBeforeDiscounts;
    private Map<String, BigDecimal> discountsApplied;

    public OrderCalculationResponse(BigDecimal totalBeforeDiscounts, Map<String, BigDecimal> discountsApplied) {
        this.totalBeforeDiscounts = totalBeforeDiscounts;
        this.discountsApplied = discountsApplied;
    }

    public BigDecimal getTotalBeforeDiscounts() {
        return totalBeforeDiscounts;
    }

    public void setTotalBeforeDiscounts(BigDecimal totalBeforeDiscounts) {
        this.totalBeforeDiscounts = totalBeforeDiscounts;
    }

    public Map<String, BigDecimal> getDiscountsApplied() {
        return discountsApplied;
    }

    public void setDiscountsApplied(Map<String, BigDecimal> discountsApplied) {
        this.discountsApplied = discountsApplied;
    }
}
